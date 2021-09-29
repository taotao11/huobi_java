package com.huobi.model.market;

import java.math.BigDecimal;

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
public class MarketTicker {
  /**
   * symbol	string	交易对，例如btcusdt, ethbtc
   */
  private String symbol;
  /**
   * 开盘价（以新加坡时间自然日计）
   */
  private BigDecimal open;
  /**
   * 最新价（以新加坡时间自然日计）
   */
  private BigDecimal close;
  /**
   * 最低价（以新加坡时间自然日计）
   */
  private BigDecimal low;
  /**
   * 最高价（以新加坡时间自然日计）
   */
  private BigDecimal high;
  /**
   * 以基础币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal amount;
  /**
   * 交易笔数（以滚动24小时计）
   */
  private Long count;
  /**
   * 以报价币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal vol;
  /**
   * 买一价
   */
  private BigDecimal bid;
  /**
   * 买一量
   */
  private BigDecimal bidSize;
  /**
   * 卖一价
   */
  private BigDecimal ask;
  /**
   * 卖一量
   */
  private BigDecimal askSize;

}
