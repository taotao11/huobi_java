package com.huobi.service.huobi;

import com.alibaba.fastjson.JSONObject;

import com.huobi.client.AlgoClient;
import com.huobi.client.req.algo.CancelAlgoOrderRequest;
import com.huobi.client.req.algo.CreateAlgoOrderRequest;
import com.huobi.client.req.algo.GetHistoryAlgoOrdersRequest;
import com.huobi.client.req.algo.GetOpenAlgoOrdersRequest;
import com.huobi.constant.Options;
import com.huobi.constant.enums.algo.AlgoOrderTypeEnum;
import com.huobi.model.algo.AlgoOrder;
import com.huobi.model.algo.CancelAlgoOrderResult;
import com.huobi.model.algo.CreateAlgoOrderResult;
import com.huobi.model.algo.GetHistoryAlgoOrdersResult;
import com.huobi.model.algo.GetOpenAlgoOrdersResult;
import com.huobi.service.huobi.connection.HuobiRestConnection;
import com.huobi.service.huobi.parser.algo.AlgoOrderParser;
import com.huobi.service.huobi.parser.algo.CancelAlgoOrderResultParser;
import com.huobi.service.huobi.parser.algo.CreateAlgoOrderResultParser;
import com.huobi.service.huobi.parser.algo.GetHistoryAlgoOrdersResultParser;
import com.huobi.service.huobi.parser.algo.GetOpenAlgoOrdersResultParser;
import com.huobi.service.huobi.signature.UrlParamsBuilder;
import com.huobi.utils.InputChecker;


public class HuobiAlgoService implements AlgoClient {

  private static final String GET_ALGO_ORDERS_SPECIFIC_PATH = "/v2/algo-orders/specific";
  private static final String GET_OPEN_ALGO_ORDERS_PATH = "/v2/algo-orders/opening";
  private static final String GET_HISTORY_ALGO_ORDERS_PATH = "/v2/algo-orders/history";
  private static final String CREATE_ALGO_ORDER_PATH = "/v2/algo-orders";
  private static final String CANCEL_ALGO_ORDER_PATH = "/v2/algo-orders/cancellation";


  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiAlgoService(Options options) {
    this.options = options;
    this.restConnection = new HuobiRestConnection(options);
  }

  /**
   * 策略委托下单
   * POST /v2/algo-orders
   * API Key 权限：交易
   * 限频值（NEW）：20次/2秒
   * 仅可通过此节点下单策略委托，不可通过现货/杠杆交易相关接口下单策略委托，支持未触发OPEN订单最大数量为100。
   * @param request
   * @return clientOrderId	string	TRUE	用户自编订单号
   */
  @Override
  public CreateAlgoOrderResult createAlgoOrder(CreateAlgoOrderRequest request) {

    InputChecker checker = InputChecker.checker()
        .shouldNotNull(request.getAccountId(), "accountId")
        .shouldNotNull(request.getSymbol(), "symbol")
        .shouldNotNull(request.getOrderSide(), "orderSide")
        .shouldNotNull(request.getOrderType(), "orderType")
        .shouldNotNull(request.getClientOrderId(), "clientOrderId")
        .shouldNotNull(request.getStopPrice(), "stopPrice");

    if (request.getOrderType() == AlgoOrderTypeEnum.LIMIT) {
      checker
          .shouldNotNull(request.getOrderPrice(), "orderPrice")
          .shouldNotNull(request.getOrderSize(), "orderSize");
    } else {
      checker.shouldNotNull(request.getOrderValue(), "orderValue");
    }

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("accountId", request.getAccountId())
        .putToPost("symbol", request.getSymbol())
        .putToPost("orderPrice", request.getOrderPrice())
        .putToPost("orderSide", request.getOrderSide().getSide())
        .putToPost("orderSize", request.getOrderSize())
        .putToPost("orderValue", request.getOrderValue())
        .putToPost("timeInForce", request.getTimeInForce() == null ? null : request.getTimeInForce().getTimeInForce())
        .putToPost("orderType", request.getOrderType().getType())
        .putToPost("clientOrderId", request.getClientOrderId())
        .putToPost("stopPrice", request.getStopPrice());

    JSONObject jsonObject = restConnection.executePostWithSignature(CREATE_ALGO_ORDER_PATH, builder);
    return new CreateAlgoOrderResultParser().parse(jsonObject.getJSONObject("data"));
  }

  /**
   * 策略委托（触发前）撤单
   * POST /v2/algo-orders/cancellation
   * API Key 权限：交易
   * 限频值（NEW）：20次/2秒
   * 单次请求最多批量撤销50张订单
   * 如需撤销已成功触发的订单，须通过现货/杠杆交易相关接口完成
   * 如需撤销未触发的订单，仅可通过此节点撤销，不可通过现货/杠杆交易相关接口撤销
   * @param request 用户自编订单号（可多填，以数组的形式传输）
   * @return
   */
  @Override
  public CancelAlgoOrderResult cancelAlgoOrder(CancelAlgoOrderRequest request) {

    InputChecker.checker().checkList(request.getClientOrderIds(), 1, 50, "clientOrderIds");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToPost("clientOrderIds", request.getClientOrderIds());

    JSONObject jsonObject = restConnection.executePostWithSignature(CANCEL_ALGO_ORDER_PATH, builder);
    JSONObject data = jsonObject.getJSONObject("data");
    return new CancelAlgoOrderResultParser().parse(data);
  }

  /**
   * 查询未触发OPEN策略委托
   * GET /v2/algo-orders/opening
   * API Key 权限：读取
   * 限频值（NEW）：20次/2秒
   * 以orderOrigTime检索
   * 未触发OPEN订单，指的是已成功下单，但尚未触发，订单状态orderStatus为created的订单
   * 未触发OPEN订单，可通过此节点查询，但不可通过现货/杠杆交易相关接口查询
   * @param request
   * @return
   */
  @Override
  public GetOpenAlgoOrdersResult getOpenAlgoOrders(GetOpenAlgoOrdersRequest request) {

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("accountId", request.getAccountId())
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("orderSide", request.getOrderSide() == null ? null : request.getOrderSide().getSide())
        .putToUrl("orderType", request.getOrderType() == null ? null : request.getOrderType().getType())
        .putToUrl("sort", request.getSort() == null ? null : request.getSort().getSort())
        .putToUrl("limit", request.getLimit())
        .putToUrl("fromId", request.getFromId());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_OPEN_ALGO_ORDERS_PATH, builder);
    return new GetOpenAlgoOrdersResultParser().parse(jsonObject);
  }

  /**
   * 查询策略委托历史
   * GET /v2/algo-orders/history
   * API Key 权限：读取
   * 限频值（NEW）：20次/2秒
   * 以orderOrigTime检索
   * 历史终态订单包括，触发前被主动撤销的策略委托（orderStatus=canceled），触发失败的策略委托（orderStatus=rejected），触发成功的策略委托（orderStatus=triggered）。
   * 如需查询已成功触发订单的后续状态，须通过现货/杠杆交易相关接口完成
   * 触发前被主动撤销的策略委托及触发失败的策略委托，可通过此节点查询，但不可通过现货/杠杆交易相关接口查询
   * @param request
   * @return
   */
  @Override
  public GetHistoryAlgoOrdersResult getHistoryAlgoOrders(GetHistoryAlgoOrdersRequest request) {

    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol")
        .shouldNotNull(request.getOrderStatus(), "orderStatus")
    ;

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("accountId", request.getAccountId())
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("orderSide", request.getOrderSide() == null ? null : request.getOrderSide().getSide())
        .putToUrl("orderType", request.getOrderType() == null ? null : request.getOrderType().getType())
        .putToUrl("orderStatus", request.getOrderStatus() == null ? null : request.getOrderStatus().getStatus())
        .putToUrl("startTime", request.getStartTime())
        .putToUrl("endTime", request.getEndTime())
        .putToUrl("sort", request.getSort() == null ? null : request.getSort().getSort())
        .putToUrl("limit", request.getLimit())
        .putToUrl("fromId", request.getFromId());

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_HISTORY_ALGO_ORDERS_PATH, builder);
    return new GetHistoryAlgoOrdersResultParser().parse(jsonObject);
  }

  /**
   *
   * @param clientOrderId clientOrderId
   * @return
   */
  @Override
  public AlgoOrder getAlgoOrdersSpecific(String clientOrderId) {

    InputChecker.checker().shouldNotNull(clientOrderId, "clientOrderId");

    UrlParamsBuilder builder = UrlParamsBuilder.build()
        .putToUrl("clientOrderId", clientOrderId);

    JSONObject jsonObject = restConnection.executeGetWithSignature(GET_ALGO_ORDERS_SPECIFIC_PATH, builder);
    return new AlgoOrderParser().parse(jsonObject.getJSONObject("data"));
  }
}
