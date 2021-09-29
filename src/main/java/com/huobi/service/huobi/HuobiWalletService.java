package com.huobi.service.huobi;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huobi.client.WalletClient;
import com.huobi.client.req.wallet.CreateWithdrawRequest;
import com.huobi.client.req.wallet.DepositAddressRequest;
import com.huobi.client.req.wallet.DepositWithdrawRequest;
import com.huobi.client.req.wallet.WithdrawAddressRequest;
import com.huobi.client.req.wallet.WithdrawQuotaRequest;
import com.huobi.constant.Constants;
import com.huobi.constant.HuobiOptions;
import com.huobi.constant.Options;
import com.huobi.constant.enums.DepositWithdrawTypeEnum;
import com.huobi.model.wallet.DepositAddress;
import com.huobi.model.wallet.DepositWithdraw;
import com.huobi.model.wallet.WithdrawAddressResult;
import com.huobi.model.wallet.WithdrawQuota;
import com.huobi.service.huobi.connection.HuobiRestConnection;
import com.huobi.service.huobi.parser.wallet.DepositAddressParser;
import com.huobi.service.huobi.parser.wallet.DepositWithdrawParser;
import com.huobi.service.huobi.parser.wallet.WithdrawAddressParser;
import com.huobi.service.huobi.parser.wallet.WithdrawQuotaParser;
import com.huobi.service.huobi.signature.UrlParamsBuilder;
import com.huobi.utils.InputChecker;

public class HuobiWalletService implements WalletClient {


  public static final String GET_DEPOSIT_ADDRESS_PATH = "/v2/account/deposit/address";
  public static final String GET_WITHDRAW_ADDRESS_PATH = "/v2/account/withdraw/address";
  public static final String GET_WITHDRAW_QUOTA_PATH = "/v2/account/withdraw/quota";
  public static final String CREATE_WITHDRAW_PATH = "/v1/dw/withdraw/api/create";
  public static final String CANCEL_WITHDRAW_PATH = "/v1/dw/withdraw-virtual/{withdraw-id}/cancel";
  public static final String DEPOSIT_WITHDRAW_PATH = "/v1/query/deposit-withdraw";

  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiWalletService(Options options) {
    this.options = options;
    this.restConnection = new HuobiRestConnection(options);
  }

  /**
   * 充币地址查询
   * 此节点用于查询特定币种（IOTA除外）在其所在区块链中的充币地址，母子用户均可用
   *
   * API Key 权限：读取
   * 限频值（NEW）：20次/2s
   * @param request
   * @return
   */
  @Override
  public List<DepositAddress> getDepositAddress(DepositAddressRequest request) {

    // 验证参数
    InputChecker.checker()
        .shouldNotNull(request.getCurrency(), "currency");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("currency", request.getCurrency());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_DEPOSIT_ADDRESS_PATH, builder);
    JSONArray array = jsonObject.getJSONArray("data");
    return new DepositAddressParser().parseArray(array);
  }

  /**
   * 提币额度查询
   * 此节点用于查询各币种提币额度，限母用户可用
   *
   * API Key 权限：读取
   * 限频值（NEW）：20次/2s
   * @param request
   * @return
   */
  @Override
  public WithdrawQuota getWithdrawQuota(WithdrawQuotaRequest request) {
    // 验证参数
    InputChecker.checker()
        .shouldNotNull(request.getCurrency(), "currency");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("currency", request.getCurrency());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_WITHDRAW_QUOTA_PATH, builder);
    JSONObject data = jsonObject.getJSONObject("data");
    return new WithdrawQuotaParser().parse(data);
  }

  /**
   * 提币地址查询
   * API Key 权限：读取
   *
   * 该节点用于查询API key可用的提币地址，限母用户可用。
   * @param request
   * @return
   */
  public WithdrawAddressResult getWithdrawAddress(WithdrawAddressRequest request) {

    // 验证参数
    InputChecker.checker()
        .shouldNotNull(request.getCurrency(), "currency");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("currency", request.getCurrency())
        .putToUrl("chain", request.getChain())
        .putToUrl("note", request.getNote())
        .putToUrl("limit", request.getLimit())
        .putToUrl("fromId", request.getFromId());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_WITHDRAW_ADDRESS_PATH, builder);
    JSONArray array = jsonObject.getJSONArray("data");

    return WithdrawAddressResult.builder()
        .nextId(jsonObject.getLong("next-id"))
        .withdrawAddressList(new WithdrawAddressParser().parseArray(array))
        .build();
  }

  /**
   * 虚拟币提币
   * 此节点用于将现货账户的数字币提取到区块链地址（已存在于提币地址列表）而不需要多重（短信、邮件）验证，限母用户可用
   *
   * API Key 权限：提币
   * 限频值（NEW）：20次/2s
   * @param request
   * @return 提币 ID
   */
  @Override
  public Long createWithdraw(CreateWithdrawRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getAddress(), "address")
        .shouldNotNull(request.getAmount(), "amount")
        .shouldNotNull(request.getCurrency(), "currency")
        .shouldNotNull(request.getFee(), "fee");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("address", request.getAddress())
        .putToPost("amount", request.getAmount())
        .putToPost("currency", request.getCurrency())
        .putToPost("fee", request.getFee())
        .putToPost("addr-tag", request.getAddrTag())
        .putToPost("chain", request.getChain());

    JSONObject jsonObject = restConnection.executePostWithSignature(CREATE_WITHDRAW_PATH, builder);
    return jsonObject.getLong("data");
  }

  /**
   * 取消提币
   * 此节点用于取消已提交的提币请求，限母用户可用
   *
   * API Key 权限：提币
   * 限频值（NEW）：20次/2s
   * @param withdrawId 提币 ID，填在 path 中
   * @return 提币 ID	（取消成功返回提币ID，取消失败返回错误码和原因）
   */
  @Override
  public Long cancelWithdraw(Long withdrawId) {

    InputChecker.checker()
        .shouldNotNull(withdrawId, "withdraw-id");

    String path = CANCEL_WITHDRAW_PATH.replace("{withdraw-id}", withdrawId + "");

    JSONObject jsonObject = restConnection.executePostWithSignature(path, UrlParamsBuilder.build());
    return jsonObject.getLong("data");
  }

  /**
   * 充提记录
   * 此节点用于查询充提记录，母子用户均可用
   *
   * API Key 权限：读取
   * 限频值（NEW）：20次/2s
   * @param request
   * @return
   */
  @Override
  public List<DepositWithdraw> getDepositWithdraw(DepositWithdrawRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getType(), "type");
    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("type", request.getType().getType())
        .putToUrl("currency", request.getCurrency())
        .putToUrl("from", request.getFrom())
        .putToUrl("size", request.getSize())
        .putToUrl("direct", request.getDirection() == null ? null : request.getDirection().getCode());

    JSONObject jsonObject = restConnection.executeGetWithSignature(DEPOSIT_WITHDRAW_PATH, builder);
    JSONArray data = jsonObject.getJSONArray("data");
    return new DepositWithdrawParser().parseArray(data);
  }


}
