package com.huobi.service.huobi;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huobi.client.IsolatedMarginClient;
import com.huobi.client.req.margin.IsolatedMarginAccountRequest;
import com.huobi.client.req.margin.IsolatedMarginApplyLoanRequest;
import com.huobi.client.req.margin.IsolatedMarginLoanInfoRequest;
import com.huobi.client.req.margin.IsolatedMarginLoanOrdersRequest;
import com.huobi.client.req.margin.IsolatedMarginRepayLoanRequest;
import com.huobi.client.req.margin.IsolatedMarginTransferRequest;
import com.huobi.constant.Constants;
import com.huobi.constant.HuobiOptions;
import com.huobi.constant.Options;
import com.huobi.constant.enums.MarginTransferDirectionEnum;
import com.huobi.model.isolatedmargin.IsolatedMarginAccount;
import com.huobi.model.isolatedmargin.IsolatedMarginLoadOrder;
import com.huobi.model.isolatedmargin.IsolatedMarginSymbolInfo;
import com.huobi.service.huobi.connection.HuobiRestConnection;
import com.huobi.service.huobi.parser.isolatedmargin.IsolatedMarginAccountParser;
import com.huobi.service.huobi.parser.isolatedmargin.IsolatedMarginLoadOrderParser;
import com.huobi.service.huobi.parser.isolatedmargin.IsolatedMarginSymbolInfoParser;
import com.huobi.service.huobi.signature.UrlParamsBuilder;
import com.huobi.utils.InputChecker;

public class HuobiIsolatedMarginService implements IsolatedMarginClient {

  public static final String TRANSFER_TO_MARGIN_PATH = "/v1/dw/transfer-in/margin";
  public static final String TRANSFER_TO_SPOT_PATH = "/v1/dw/transfer-out/margin";
  public static final String GET_BALANCE_PATH = "/v1/margin/accounts/balance";
  public static final String GET_LOAN_ORDER_PATH = "/v1/margin/loan-orders";
  public static final String GET_LOAN_INFO_PATH = "/v1/margin/loan-info";

  public static final String APPLY_LOAN_PATH = "/v1/margin/orders";
  public static final String REPAY_LOAN_PATH = "/v1/margin/orders/{order-id}/repay";


  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiIsolatedMarginService(Options options) {
    this.options = options;
    this.restConnection = new HuobiRestConnection(options);
  }

  /**
   * 资产划转（逐仓）
   * API Key 权限：交易
   * 限频值（NEW）：2次/2s
   *
   * 此接口用于现货账户与逐仓杠杆账户的资产互转。
   *
   * 从现货账户划转至逐仓杠杆账户 transfer-in，从逐仓杠杆账户划转至现货账户 transfer-out
   *
   * HTTP 请求
   * POST /v1/dw/transfer-in/margin
   *
   * POST /v1/dw/transfer-out/margin
   * @param request
   * @return Transfer id
   */
  @Override
  public Long transfer(IsolatedMarginTransferRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getDirection(), "direction")
        .checkSymbol(request.getSymbol())
        .checkCurrency(request.getCurrency())
        .shouldNotNull(request.getAmount(), "amount");

    String path = null;
    if (request.getDirection() == MarginTransferDirectionEnum.SPOT_TO_MARGIN) {
      path = TRANSFER_TO_MARGIN_PATH;
    } else {
      path = TRANSFER_TO_SPOT_PATH;
    }

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("currency", request.getCurrency())
        .putToPost("symbol", request.getSymbol())
        .putToPost("amount", request.getAmount());

    JSONObject jsonObject = restConnection.executePostWithSignature(path, builder);
    return jsonObject.getLong("data");
  }

  /**
   *
   * @param request
   * @return 订单id
   */
  @Override
  public Long applyLoan(IsolatedMarginApplyLoanRequest request) {

    InputChecker.checker()
        .checkSymbol(request.getSymbol())
        .checkCurrency(request.getCurrency())
        .shouldNotNull(request.getAmount(), "amount");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("currency", request.getCurrency())
        .putToPost("symbol", request.getSymbol())
        .putToPost("amount", request.getAmount());

    JSONObject jsonObject = restConnection.executePostWithSignature(APPLY_LOAN_PATH, builder);
    return jsonObject.getLong("data");
  }

  /**
   * 归还借币（逐仓）
   * API Key 权限：交易
   * 限频值（NEW）：2次/2s
   *
   * 此接口用于归还借币.
   * @param request
   * @return Margin order id
   */
  @Override
  public Long repayLoan(IsolatedMarginRepayLoanRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getOrderId(), "order-id")
        .shouldNotNull(request.getAmount(), "amount");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("amount", request.getAmount());

    String path = REPAY_LOAN_PATH.replace("{order-id}", request.getOrderId().toString());
    JSONObject jsonObject = restConnection.executePostWithSignature(path, builder);
    return jsonObject.getLong("data");
  }

  /**
   * 查询借币订单（逐仓）
   * API Key 权限：读取
   * 限频值（NEW）：100次/2s
   *
   * 此接口基于指定搜索条件返回借币订单。
   * @param request
   * @return
   */
  @Override
  public List<IsolatedMarginLoadOrder> getLoanOrders(IsolatedMarginLoanOrdersRequest request) {

    InputChecker.checker()
        .checkSymbol(request.getSymbol());

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("start-date", request.getStartDate(), "yyyy-MM-dd")
        .putToUrl("end-date", request.getEndDate(), "yyyy-MM-dd")
        .putToUrl("states", request.getStatesString())
        .putToUrl("from", request.getFrom())
        .putToUrl("size", request.getSize())
        .putToUrl("direct", request.getDirection() == null ? null : request.getDirection().getCode());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_LOAN_ORDER_PATH, builder);
    JSONArray data = jsonObject.getJSONArray("data");
    return new IsolatedMarginLoadOrderParser().parseArray(data);
  }

  /**
   * 借币账户详情（逐仓）
   * API Key 权限：读取
   * 限频值（NEW）：100次/2s
   *
   * 此接口返回借币账户详情。
   * @param request
   * @return
   */
  @Override
  public List<IsolatedMarginAccount> getLoanBalance(IsolatedMarginAccountRequest request) {

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("sub-uid", request.getSubUid());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_BALANCE_PATH, builder);
    JSONArray data = jsonObject.getJSONArray("data");
    return new IsolatedMarginAccountParser().parseArray(data);
  }

  /**
   * 查询借币币息率及额度（逐仓）
   * API Key 权限：读取
   * 限频值（NEW）：20次/2s
   *
   * 此接口返回用户级别的借币币息率及借币额度。
   * @param request
   * @return
   */
  @Override
  public List<IsolatedMarginSymbolInfo> getLoanInfo(IsolatedMarginLoanInfoRequest request) {
    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("symbols", request.getSymbols());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_LOAN_INFO_PATH, builder);
    JSONArray data = jsonObject.getJSONArray("data");
    return new IsolatedMarginSymbolInfoParser().parseArray(data);
  }




}
