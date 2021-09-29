package com.huobi.service.huobi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huobi.client.AccountClient;
import com.huobi.client.req.account.AccountAssetValuationRequest;
import com.huobi.client.req.account.AccountBalanceRequest;
import com.huobi.client.req.account.AccountFuturesTransferRequest;
import com.huobi.client.req.account.AccountHistoryRequest;
import com.huobi.client.req.account.AccountLedgerRequest;
import com.huobi.client.req.account.AccountTransferRequest;
import com.huobi.client.req.account.PointRequest;
import com.huobi.client.req.account.PointTransferRequest;
import com.huobi.client.req.account.SubAccountUpdateRequest;
import com.huobi.constant.Options;
import com.huobi.constant.WebSocketConstants;
import com.huobi.constant.enums.AccountTypeEnum;
import com.huobi.model.account.Account;
import com.huobi.model.account.AccountAssetValuationResult;
import com.huobi.model.account.AccountBalance;
import com.huobi.model.account.AccountFuturesTransferResult;
import com.huobi.model.account.AccountHistory;
import com.huobi.model.account.AccountLedgerResult;
import com.huobi.model.account.AccountTransferResult;
import com.huobi.model.account.AccountUpdateEvent;
import com.huobi.model.account.Point;
import com.huobi.model.account.PointTransferResult;
import com.huobi.service.huobi.connection.HuobiRestConnection;
import com.huobi.service.huobi.connection.HuobiWebSocketConnection;
import com.huobi.service.huobi.parser.account.AccountAssetValuationResultParser;
import com.huobi.service.huobi.parser.account.AccountBalanceParser;
import com.huobi.service.huobi.parser.account.AccountFuturesTransferResultParser;
import com.huobi.service.huobi.parser.account.AccountHistoryParser;
import com.huobi.service.huobi.parser.account.AccountLedgerParser;
import com.huobi.service.huobi.parser.account.AccountParser;
import com.huobi.service.huobi.parser.account.AccountTransferResultParser;
import com.huobi.service.huobi.parser.account.AccountUpdateEventParser;
import com.huobi.service.huobi.parser.account.PointParser;
import com.huobi.service.huobi.parser.account.PointTransferResultParser;
import com.huobi.service.huobi.signature.UrlParamsBuilder;
import com.huobi.utils.InputChecker;
import com.huobi.utils.ResponseCallback;

/**
 * 账户类接口实现
 * 账户相关接口提供了账户、余额、历史、点卡等查询以及资产划转等功能
 */
public class HuobiAccountService implements AccountClient {

  public static final String GET_ACCOUNTS_PATH = "/v1/account/accounts";
  public static final String GET_ACCOUNT_BALANCE_PATH = "/v1/account/accounts/{account-id}/balance";
  public static final String GET_ACCOUNT_HISTORY_PATH = "/v1/account/history";
  public static final String GET_ACCOUNT_LEDGER_PATH = "/v2/account/ledger";
  public static final String ACCOUNT_TRANSFER_PATH = "/v1/account/transfer";
  public static final String ACCOUNT_FUTURES_TRANSFER_PATH = "/v1/futures/transfer";
  public static final String POINT_ACCOUNT_PATH = "/v2/point/account";
  public static final String POINT_TRANSFER_PATH = "/v2/point/transfer";
  public static final String ACCOUNT_ASSET_VALUATION_PATH = "/v2/account/asset-valuation";
  /**
   * 获取平台资产总估值
   */
  public static final String ACCOUNT_VALUATION_PATH = "/v2/account/valuation";



  public static final String SUB_ACCOUNT_UPDATE_TOPIC = "accounts.update#${mode}";


  private Map<AccountTypeEnum, Account> accountMap = new ConcurrentHashMap<>();
  private Map<String, Account> marginAccountMap = new ConcurrentHashMap<>();

  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiAccountService(Options options) {
    this.options = options;
    this.restConnection = new HuobiRestConnection(options);
  }

  /**
   * 账户信息
   * @return
   */
  @Override
  public List<Account> getAccounts() {

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_ACCOUNTS_PATH, UrlParamsBuilder.build());
    JSONArray data = jsonObject.getJSONArray("data");
    List<Account> accountList = new AccountParser().parseArray(data);
    return accountList;
  }

  /**
   * 账户余额
   * API Key 权限：读取
   * 限频值（NEW）：100次/2s
   *
   * 查询指定账户的余额，支持以下账户：
   *
   * spot：现货账户， margin：逐仓杠杆账户，otc：OTC 账户，point：点卡账户，
   * super-margin：全仓杠杆账户, investment: C2C杠杆借出账户, borrow: C2C杠杆借入账户
   * @param request
   * @return
   */
  @Override
  public AccountBalance getAccountBalance(AccountBalanceRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getAccountId(), "account-id");

    String path = GET_ACCOUNT_BALANCE_PATH.replace("{account-id}", request.getAccountId() + "");
    JSONObject jsonObject = restConnection.executeGetWithSignature(path, UrlParamsBuilder.build());
    JSONObject data = jsonObject.getJSONObject("data");
    return new AccountBalanceParser().parse(data);
  }

  /**
   * 账户流水
   * API Key 权限：读取
   * 限频值（NEW）：5次/2s
   *
   * 该节点基于用户账户ID返回账户流水。
   * @param request
   * @return
   */
  public List<AccountHistory> getAccountHistory(AccountHistoryRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getAccountId(), "account-id");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("account-id", request.getAccountId())
        .putToUrl("currency", request.getCurrency())
        .putToUrl("transact-types", request.getTypesString())
        .putToUrl("start-time", request.getStartTime())
        .putToUrl("end-time", request.getEndTime())
        .putToUrl("sort", request.getSort() == null ? null : request.getSort().getSort())
        .putToUrl("size", request.getSize());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_ACCOUNT_HISTORY_PATH, builder);
    JSONArray jsonArray = jsonObject.getJSONArray("data");
    AccountHistoryParser parser = new AccountHistoryParser();
    List<AccountHistory> list = new ArrayList<>(jsonArray.size());
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonItem = jsonArray.getJSONObject(i);
      list.add(parser.parse(jsonItem));
    }
    list.get(list.size()-1).setNextId(jsonObject.getLong("next-id"));
    return list;
  }

  /**
   * 财务流水
   * API Key 权限：读取
   *
   * 该节点基于用户账户ID返回财务流水。
   * 一期上线暂时仅支持划转流水的查询（“transactType” = “transfer”）。
   * 通过“startTime”/“endTime”框定的查询窗口最大为10天，意即，通过单次查询可检索的范围最大为10天。
   * 该查询窗口可在最近180天范围内平移，意即，通过多次平移窗口查询，最多可检索到过往180天的记录。
   * @param request
   * @return
   */
  public AccountLedgerResult getAccountLedger(AccountLedgerRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getAccountId(), "accountId");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("accountId", request.getAccountId())
        .putToUrl("currency", request.getCurrency())
        .putToUrl("transactTypes", request.getTypesString())
        .putToUrl("startTime", request.getStartTime())
        .putToUrl("endTime", request.getEndTime())
        .putToUrl("sort", request.getSort() == null ? null : request.getSort().getSort())
        .putToUrl("limit", request.getLimit())
        .putToUrl("fromId", request.getFromId());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_ACCOUNT_LEDGER_PATH, builder);
    Long nextId = jsonObject.getLong("nextId");
    JSONArray jsonArray = jsonObject.getJSONArray("data");
    return AccountLedgerResult.builder()
        .nextId(nextId)
        .ledgerList(new AccountLedgerParser().parseArray(jsonArray))
        .build();
  }

  public AccountTransferResult accountTransfer(AccountTransferRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getFromUser(), "from-user")
        .shouldNotNull(request.getFromAccount(), "from-account")
        .shouldNotNull(request.getFromAccountType(), "from-account-type")
        .shouldNotNull(request.getToUser(), "to-user")
        .shouldNotNull(request.getToAccount(), "to-account")
        .shouldNotNull(request.getToAccountType(), "to-account-type")
        .shouldNotNull(request.getCurrency(), "currency")
        .shouldNotNull(request.getAmount(), "amount");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("from-user", request.getFromUser())
        .putToPost("from-account", request.getFromAccount())
        .putToPost("from-account-type", request.getFromAccountType().getAccountType())
        .putToPost("to-user", request.getToUser())
        .putToPost("to-account", request.getToAccount())
        .putToPost("to-account-type", request.getToAccountType().getAccountType())
        .putToPost("currency", request.getCurrency())
        .putToPost("amount", request.getAmount());

    JSONObject jsonObject = restConnection.executePostWithSignature(ACCOUNT_TRANSFER_PATH, builder);
    return new AccountTransferResultParser().parse(jsonObject.getJSONObject("data"));
  }

  /**
   * 币币现货账户与合约账户划转
   * API Key 权限：交易
   *
   * 此接口用户币币现货账户与合约账户之间的资产划转。
   *
   * 从现货现货账户转至合约账户，类型为pro-to-futures; 从合约账户转至现货账户，类型为futures-to-pro
   * @param request
   * @return
   */
  public AccountFuturesTransferResult accountFuturesTransfer(AccountFuturesTransferRequest request) {
    InputChecker.checker()
        .shouldNotNull(request.getCurrency(),"currency")
        .shouldNotNull(request.getAmount(),"amount")
        .shouldNotNull(request.getType(),"type");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("currency", request.getCurrency())
        .putToPost("amount", request.getAmount())
        .putToPost("type", request.getType().getType());

    JSONObject jsonObject = restConnection.executePostWithSignature(ACCOUNT_FUTURES_TRANSFER_PATH, builder);

    return new AccountFuturesTransferResultParser().parse(jsonObject);
  }

  /**
   * 点卡余额查询
   * 此节点既可查询到“不限时”点卡的余额，也可查询到“限时”点卡的余额、分组ID、及各组有效期。
   * 此节点仅可查询到限时/不限时点卡的余额，不可查询到其它币种余额。
   * 母用户可查询母用户或子用户点卡余额。
   * 点卡兑换仅可通过官网页面或APP完成。
   * @param request
   * @return
   */
  @Override
  public Point getPoint(PointRequest request) {

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("subUid", request.getSubUid());

    JSONObject jsonObject = restConnection.executeGetWithSignature(POINT_ACCOUNT_PATH, builder);
    return new PointParser().parse(jsonObject);
  }

  /**
   * 点卡划转
   * 此节点既可划转“不限时”点卡，也可划转“限时”点卡。
   * 此节点仅支持不限时/限时点卡的划转，不支持其它币种的划转。
   * 此节点仅支持母子用户点卡（point）账户间划转。
   * 如果登录用户为母用户，该节点支持双向划转，即母向子划转和子向母划转。
   * 如果登录用户为子用户，该节点仅支持单向划转，即子向母划转。
   * 母用户将限时点卡从子用户转回时应先行查询子用户点卡的groupId。
   *
   * API Key 权限：交易
   * 限频值：2次/秒
   * 子用户可调用
   * @param request
   * @return
   */
  @Override
  public PointTransferResult pointTransfer(PointTransferRequest request) {
    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("fromUid", request.getFromUid())
        .putToPost("toUid", request.getToUid())
        .putToPost("groupId", request.getGroupId())
        .putToPost("amount", request.getAmount())
      ;
    JSONObject jsonObject = restConnection.executePostWithSignature(POINT_TRANSFER_PATH, builder);
    return new PointTransferResultParser().parse(jsonObject);
  }

  /**
   * 获取指定账户资产估值（现货、杠杆、OTC）
   * API Key 权限：读取
   *
   * 限频值（NEW）：100次/2s
   *
   * 按照BTC或法币计价单位，获取指定账户的总资产估值。
   * @param request
   * @return
   */
  @Override
  public AccountAssetValuationResult accountAssetValuation(AccountAssetValuationRequest request) {
    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("accountType", request.getAccountType().getCode())
        .putToUrl("valuationCurrency", request.getValuationCurrency())
        .putToUrl("subUid", request.getSubUid())
      ;
    JSONObject jsonObject = restConnection.executeGetWithSignature(ACCOUNT_ASSET_VALUATION_PATH, builder);
    return new AccountAssetValuationResultParser().parse(jsonObject);
  }

  public void subAccountsUpdate(SubAccountUpdateRequest request, ResponseCallback<AccountUpdateEvent> callback) {
    InputChecker.checker()
        .shouldNotNull(request.getAccountUpdateMode(), "account update model");

    JSONObject command = new JSONObject();
    command.put("action", WebSocketConstants.ACTION_SUB);
    command.put("cid", System.currentTimeMillis() + "");
    command.put("ch", SUB_ACCOUNT_UPDATE_TOPIC.replace("${mode}", request.getAccountUpdateMode().getCode()));
    command.put("model", request.getAccountUpdateMode().getCode());

    List<String> commandList = new ArrayList<>();
    commandList.add(command.toJSONString());

    HuobiWebSocketConnection.createAssetV2Connection(options, commandList, new AccountUpdateEventParser(), callback, false);
  }

}
