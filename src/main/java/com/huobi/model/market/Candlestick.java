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
public class Candlestick {
  /**
   * 调整为新加坡时间的时间戳，单位秒，并以此作为此K线柱的id
   */
  private Long id;
  /**
   * 以基础币种计量的交易量
   */
  private BigDecimal amount;
  /**
   * 交易次数
   */
  private BigDecimal count;
  /**
   * 本阶段开盘价
   */
  private BigDecimal open;
  /**
   * 本阶段最高价
   */
  private BigDecimal high;
  /**
   * 本阶段最低价
   */
  private BigDecimal low;
  /**
   * 本阶段收盘价
   */
  private BigDecimal close;
  /**
   * 以报价币种计量的交易量
   */
  private BigDecimal vol;

}
