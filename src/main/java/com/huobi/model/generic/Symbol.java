package com.huobi.model.generic;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Symbol {
  /**
   * 交易对中的基础币种
   */
  private String symbol;
  /**
   * 交易对中的报价币种
   */
  @JSONField(name = "base-currency")
  private String baseCurrency;
  /**
   * 交易对中的报价币种
   */
  @JSONField(name = "quote-currency")
  private String quoteCurrency;
  /**
   * 交易对报价的精度（小数点后位数），限价买入与限价卖出价格使用
   */
  @JSONField(name = "price-precision")
  private int pricePrecision;
  /**
   * 交易对基础币种计数精度（小数点后位数），限价买入、限价卖出、市价卖出数量使用
   */
  @JSONField(name = "amount-precision")
  private int amountPrecision;
  /**
   * 交易区，可能值: [main，innovation]
   */
  @JSONField(name = "symbol-partition")
  private String symbolPartition;
  /**
   * 交易对交易金额的精度（小数点后位数），市价买入金额使用
   */
  @JSONField(name = "value-precision")
  private Integer valuePrecision;
  /**
   * 交易对限价单最小下单量 ，以基础币种为单位（即将废弃）
   */
  @JSONField(name = "min-order-amt")
  private BigDecimal minOrderAmt;
  /**
   * 交易对限价单最大下单量 ，以基础币种为单位（即将废弃）
   */
  @JSONField(name = "max-order-amt")
  private BigDecimal maxOrderAmt;
  /**
   * 交易对限价单和市价买单最小下单金额 ，以计价币种为单位
   */
  @JSONField(name = "min-order-value")
  private BigDecimal minOrderValue;
  /**
   * 交易对杠杆最大倍数(仅对逐仓杠杆交易对、全仓杠杆交易对、杠杆ETP交易对有效）
   */
  @JSONField(name = "leverage-ratio")
  private Integer leverageRatio;
  /**
   * 交易对状态；可能值: [online，offline,suspend] online - 已上线；offline - 交易对已下线，不可交易；suspend -- 交易暂停；pre-online - 即将上线
   */
  private String state;
  /**
   * 交易对限价单最小下单量 ，以基础币种为单位（NEW）
   */
  @JSONField(name = "limit-order-min-order-amt")
  private BigDecimal limitOrderMinOrderAmt;
  /**
   * 交易对限价单最大下单量 ，以基础币种为单位（NEW）
   */
  @JSONField(name = "limit-order-max-order-amt")
  private BigDecimal limitOrderMaxOrderAmt;
  /**
   * 交易对市价卖单最小下单量，以基础币种为单位（NEW）
   */
  @JSONField(name = "sell-market-min-order-amt")
  private BigDecimal sellMarketMinOrderAmt;
  /**
   * 交易对市价卖单最大下单量，以基础币种为单位（NEW）
   */
  @JSONField(name = "sell-market-max-order-amt")
  private BigDecimal sellMarketMaxOrderAmt;
  /**
   * 交易对市价买单最大下单金额，以计价币种为单位（NEW）
   */
  @JSONField(name = "buy-market-max-order-value")
  private BigDecimal buyMarketMaxOrderValue;
  /**
   * 交易对限价单和市价买单最大下单金额 ，以折算后的USDT为单位（NEW）
   */
  @JSONField(name = "max-order-value")
  private BigDecimal maxOrderValue;
  /**
   * 标的交易代码 (仅对杠杆ETP交易对有效)
   */
  private String underlying;
  /**
   * 持仓管理费费率 (仅对杠杆ETP交易对有效)
   */
  @JSONField(name = "mgmt-fee-rate")
  private BigDecimal mgmtFeeRate;
  /**
   * 持仓管理费收取时间 (24小时制，GMT+8，格式：HH:MM:SS，仅对杠杆ETP交易对有效)
   */
  @JSONField(name = "charge-time")
  private String chargeTime;
  /**
   * 每日调仓时间 (24小时制，GMT+8，格式：HH:MM:SS，仅对杠杆ETP交易对有效)
   */
  @JSONField(name = "rebal-time")
  private String rebalTime;
  /**
   * 临时调仓阈值 (以实际杠杆率计，仅对杠杆ETP交易对有效)
   */
  @JSONField(name = "rebal-threshold")
  private BigDecimal rebalThreshold;
  /**
   * 初始净值（仅对杠杆ETP交易对有效）
   */
  @JSONField(name = "init-nav")
  private BigDecimal initNav;

}
