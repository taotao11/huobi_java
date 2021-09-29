package com.huobi.client.req.trade;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.OrderSourceEnum;
import com.huobi.constant.enums.OrderTypeEnum;
import com.huobi.constant.enums.StopOrderOperatorEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOrderRequest {

  /**
   * 账户 ID，取值参考 GET /v1/account/accounts。
   * 现货交易使用 ‘spot’ 账户的 account-id；逐仓杠杆交易，请使用 ‘margin’ 账户的 account-id；
   * 全仓杠杆交易，请使用 ‘super-margin’ 账户的 account-id
   */
  private Long accountId;
  /**
   * 交易对,即btcusdt, ethbtc...（取值参考GET /v1/common/symbols）
   */
  private String symbol;
  /**
   * 订单类型，包括buy-market, sell-market, buy-limit, sell-limit, buy-ioc, sell-ioc, buy-limit-maker, sell-limit-maker（说明见下文）,
   * buy-stop-limit, sell-stop-limit, buy-limit-fok, sell-limit-fok, buy-stop-limit-fok, sell-stop-limit-fok
   */
  private OrderTypeEnum type;

  /**
   * 订单价格（对市价单无效）
   */
  private BigDecimal price;
  /**
   * 订单交易量（市价买单为订单交易额）
   */
  private BigDecimal amount;
  /**
   * 现货交易填写“spot-api”，逐仓杠杆交易填写“margin-api”，
   * 全仓杠杆交易填写“super-margin-api”, C2C杠杆交易填写"c2c-margin-api"
   */
  private String source;
  /**
   * 用户自编订单号（最大长度64个字符，须在8小时内保持唯一性）
   */
  private String clientOrderId;
  /**
   * 止盈止损订单触发价格
   */
  private BigDecimal stopPrice;
  /**
   * 止盈止损订单触发价运算符 gte – greater than and equal (>=), lte – less than and equal (<=)
   */
  private StopOrderOperatorEnum operator;

  private OrderSourceEnum orderSource;

  //------------------------------- Spot ---------------------------------------//

  public static CreateOrderRequest spotBuyLimit(Long spotAccountId, String symbol, BigDecimal price, BigDecimal amount) {
    return spotBuyLimit(spotAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest spotBuyLimit(Long spotAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(spotAccountId)
        .type(OrderTypeEnum.BUY_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.API)
        .build();
  }

  public static CreateOrderRequest spotSellLimit(Long spotAccountId,String symbol, BigDecimal price, BigDecimal amount) {
    return spotSellLimit(spotAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest spotSellLimit(Long spotAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(spotAccountId)
        .type(OrderTypeEnum.SELL_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.API)
        .build();
  }

  public static CreateOrderRequest spotBuyMarket(Long spotAccountId,String symbol, BigDecimal amount) {
    return spotBuyMarket(spotAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest spotBuyMarket(Long spotAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(spotAccountId)
        .type(OrderTypeEnum.BUY_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.API)
        .build();
  }

  public static CreateOrderRequest spotSellMarket(Long spotAccountId,String symbol, BigDecimal amount) {
    return spotSellMarket(spotAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest spotSellMarket(Long spotAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(spotAccountId)
        .type(OrderTypeEnum.SELL_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.API)
        .build();
  }

  //--------------------------------- Margin -----------------------------------------//

  public static CreateOrderRequest marginBuyLimit(Long marginAccountId,String symbol, BigDecimal price, BigDecimal amount) {
    return marginBuyLimit(marginAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest marginBuyLimit(Long marginAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(marginAccountId)
        .type(OrderTypeEnum.BUY_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.MARGIN_API)
        .build();
  }

  public static CreateOrderRequest marginSellLimit(Long marginAccountId,String symbol, BigDecimal price, BigDecimal amount) {
    return marginSellLimit(marginAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest marginSellLimit(Long marginAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(marginAccountId)
        .type(OrderTypeEnum.SELL_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.MARGIN_API)
        .build();
  }

  public static CreateOrderRequest marginBuyMarket(Long marginAccountId,String symbol, BigDecimal amount) {
    return marginBuyMarket(marginAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest marginBuyMarket(Long marginAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(marginAccountId)
        .type(OrderTypeEnum.BUY_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.MARGIN_API)
        .build();
  }

  public static CreateOrderRequest marginSellMarket(Long marginAccountId,String symbol, BigDecimal amount) {
    return marginSellMarket(marginAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest marginSellMarket(Long marginAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(marginAccountId)
        .type(OrderTypeEnum.SELL_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.MARGIN_API)
        .build();
  }

  //-------------------------------Super Margin---------------------------------------//

  public static CreateOrderRequest superMarginBuyLimit(Long superMarginAccountId,String symbol, BigDecimal price, BigDecimal amount) {
    return superMarginBuyLimit(superMarginAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest superMarginBuyLimit(Long superMarginAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(superMarginAccountId)
        .type(OrderTypeEnum.BUY_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.SUPER_MARGIN_API)
        .build();
  }

  public static CreateOrderRequest superMarginSellLimit(Long superMarginAccountId,String symbol, BigDecimal price, BigDecimal amount) {
    return superMarginSellLimit(superMarginAccountId,null, symbol, price, amount);
  }

  public static CreateOrderRequest superMarginSellLimit(Long superMarginAccountId,String clientOrderId, String symbol, BigDecimal price, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(superMarginAccountId)
        .type(OrderTypeEnum.SELL_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .orderSource(OrderSourceEnum.SUPER_MARGIN_API)
        .build();
  }

  public static CreateOrderRequest superMarginBuyMarket(Long superMarginAccountId,String symbol, BigDecimal amount) {
    return superMarginBuyMarket(superMarginAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest superMarginBuyMarket(Long superMarginAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(superMarginAccountId)
        .type(OrderTypeEnum.BUY_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.SUPER_MARGIN_API)
        .build();
  }

  public static CreateOrderRequest superMarginSellMarket(Long superMarginAccountId,String symbol, BigDecimal amount) {
    return superMarginSellMarket(superMarginAccountId,null, symbol, amount);
  }

  public static CreateOrderRequest superMarginSellMarket(Long superMarginAccountId,String clientOrderId, String symbol, BigDecimal amount) {
    return CreateOrderRequest.builder()
        .accountId(superMarginAccountId)
        .type(OrderTypeEnum.SELL_MARKET)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .amount(amount)
        .orderSource(OrderSourceEnum.SUPER_MARGIN_API)
        .build();
  }


  //-------------------------------Stop Loss---------------------------------------//
  public static CreateOrderRequest buyStopLoss(Long accountId, String symbol, BigDecimal price, BigDecimal amount,
      BigDecimal stopPrice, StopOrderOperatorEnum operator, OrderSourceEnum orderSource) {
    return buyStopLoss(null, accountId, symbol, price, amount, stopPrice, operator,orderSource);
  }

  public static CreateOrderRequest buyStopLoss(String clientOrderId, Long accountId, String symbol,
      BigDecimal price, BigDecimal amount, BigDecimal stopPrice, StopOrderOperatorEnum operator, OrderSourceEnum orderSource) {
    return CreateOrderRequest.builder()
        .accountId(accountId)
        .type(OrderTypeEnum.BUY_STOP_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .stopPrice(stopPrice)
        .operator(operator)
        .orderSource(orderSource)
        .build();
  }


  public static CreateOrderRequest sellStopLoss(Long accountId, String symbol, BigDecimal price, BigDecimal amount,
      BigDecimal stopPrice, StopOrderOperatorEnum operator, OrderSourceEnum orderSource) {
    return sellStopLoss(null, accountId, symbol, price, amount, stopPrice, operator,orderSource);
  }

  public static CreateOrderRequest sellStopLoss(String clientOrderId, Long accountId, String symbol,
      BigDecimal price, BigDecimal amount, BigDecimal stopPrice, StopOrderOperatorEnum operator, OrderSourceEnum orderSource) {
    return CreateOrderRequest.builder()
        .accountId(accountId)
        .type(OrderTypeEnum.SELL_STOP_LIMIT)
        .clientOrderId(clientOrderId)
        .symbol(symbol)
        .price(price)
        .amount(amount)
        .stopPrice(stopPrice)
        .operator(operator)
        .orderSource(orderSource)
        .build();
  }
}
