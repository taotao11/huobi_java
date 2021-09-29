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
public class MarketDetail {
  /**
   * 响应id
   */
  private Long id;
  /**
   * 本阶段开盘价（以滚动24小时计）
   */
  private BigDecimal open;
  /**
   * 本阶段收盘价（以滚动24小时计）
   */
  private BigDecimal close;
  /**
   *本阶段最低价（以滚动24小时计）
   */
  private BigDecimal low;
  /**
   *本阶段最高价（以滚动24小时计）
   */
  private BigDecimal high;
  /**
   * 交易次数（以滚动24小时计）
   */
  private Long count;
  /**
   * 以报价币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal vol;
  /**
   * 以基础币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal amount;
  /**
   * 内部数据
   */
  private Long version;


}
