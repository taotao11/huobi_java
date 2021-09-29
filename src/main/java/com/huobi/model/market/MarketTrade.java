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
public class MarketTrade {
  /**
   * 唯一交易id（将被废弃）
   */
  private String id;
  /**
   * 唯一成交ID（NEW）
   */
  private Long tradeId;
  /**
   * 以报价币种为单位的成交价格
   */
  private BigDecimal price;
  /**
   * 以基础币种为单位的交易量
   */
  private BigDecimal amount;
  /**
   * 交易方向：“buy” 或 “sell”, “buy” 即买，“sell” 即卖
   */
  private String direction;
  /**
   * 最新成交时间
   */
  private Long ts;

}
