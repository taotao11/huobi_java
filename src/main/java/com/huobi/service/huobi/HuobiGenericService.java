package com.huobi.service.huobi;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.huobi.client.GenericClient;
import com.huobi.client.req.generic.CurrencyChainsRequest;
import com.huobi.constant.HuobiOptions;
import com.huobi.constant.Options;
import com.huobi.model.generic.CurrencyChain;
import com.huobi.model.generic.MarketStatus;
import com.huobi.model.generic.Symbol;
import com.huobi.service.huobi.connection.HuobiRestConnection;
import com.huobi.service.huobi.parser.generic.CurrencyChainParser;
import com.huobi.service.huobi.parser.generic.MarketStatusParser;
import com.huobi.service.huobi.parser.generic.SymbolParser;
import com.huobi.service.huobi.signature.UrlParamsBuilder;

public class HuobiGenericService implements GenericClient {

  public static final String GET_SYSTEM_STATUS_URL = "https://status.huobigroup.com/api/v2/summary.json";

  public static final String GET_MARKET_STATUS_PATH = "/v2/market-status";
  public static final String GET_SYMBOLS_PATH = "/v1/common/symbols";
  public static final String GET_CURRENCY_PATH = "/v1/common/currencys";
  public static final String GET_CURRENCY_CHAINS_PATH = "/v2/reference/currencies";
  public static final String GET_TIMESTAMP = "/v1/common/timestamp";

  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiGenericService(Options options) {
    this.options = options;
    restConnection = new HuobiRestConnection(options);
  }

  /**
   * 基础信息Rest接口提供了系统状态、市场状态、交易对信息、币种信息、币链信息、服务器时间戳等公共参考信息。
   *
   * 获取当前系统状态
   * 此接口返回当前的系统状态，包含当前系统维护计划和故障进度等。
   *
   * 如您需要通过邮件、短信、Webhook、RSS/Atom feed接收以上信息，可点击这里进入页面进行订阅。当前订阅依赖Google服务，订阅前请确保您可正常访问Google的服务，否则将订阅失败。
   * @return
   */
  @Override
  public String getSystemStatus() {
    String response = restConnection.executeGetString(GET_SYSTEM_STATUS_URL,UrlParamsBuilder.build());
    return response;
  }

  /**
   * 获取当前市场状态
   * 此节点返回当前最新市场状态。
   * 状态枚举值包括: 1 - 正常（可下单可撤单），2 - 挂起（不可下单不可撤单），3 - 仅撤单（不可下单可撤单）。
   * 挂起原因枚举值包括: 2 - 紧急维护，3 - 计划维护。
   * @return
   */
  @Override
  public MarketStatus getMarketStatus() {
    JSONObject jsonObject = restConnection.executeGet(GET_MARKET_STATUS_PATH, UrlParamsBuilder.build());
    JSONObject data = jsonObject.getJSONObject("data");
    return new MarketStatusParser().parse(data);
  }

  /**
   * 获取所有交易对
   * 此接口返回所有火币全球站支持的交易对。
   * @return
   */
  @Override
  public List<Symbol> getSymbols() {

    JSONObject jsonObject = restConnection.executeGet(GET_SYMBOLS_PATH, UrlParamsBuilder.build());
    JSONArray data = jsonObject.getJSONArray("data");
    return new SymbolParser().parseArray(data);
  }

  /**
   * 获取所有币种
   * 此接口返回所有火币全球站支持的币种。
   * @return
   */
  @Override
  public List<String> getCurrencys() {

    JSONObject jsonObject = restConnection.executeGet(GET_CURRENCY_PATH, UrlParamsBuilder.build());
    JSONArray data = jsonObject.getJSONArray("data");
    return data.toJavaList(String.class);

  }

  /**
   * APIv2 币链参考信息
   * 此节点用于查询各币种及其所在区块链的静态参考信息（公共数据）
   * @param request
   * @return
   */
  @Override
  public List<CurrencyChain> getCurrencyChains(CurrencyChainsRequest request) {

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("currency",request.getCurrency())
        .putToUrl("authorizedUser",request.isAuthorizedUser()+"");

    JSONObject jsonObject = restConnection.executeGet(GET_CURRENCY_CHAINS_PATH,builder);
    JSONArray data = jsonObject.getJSONArray("data");
    return new CurrencyChainParser().parseArray(data);
  }

  /**
   * 获取当前系统时间戳
   * 此接口返回当前的系统时间戳，即从 UTC 1970年1月1日0时0分0秒0毫秒到现在的总毫秒数。
   * @return 当前系统时间戳
   */
  @Override
  public Long getTimestamp() {
    JSONObject jsonObject = restConnection.executeGet(GET_TIMESTAMP, UrlParamsBuilder.build());
    return jsonObject.getLong("data");
  }

}
