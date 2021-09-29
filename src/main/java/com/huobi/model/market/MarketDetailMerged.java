package com.huobi.model.market;

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
public class MarketDetailMerged {

  private Long id;
  /**
   * 以基础币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal amount;
  /**
   * 交易次数（以滚动24小时计）
   */
  private Long count;
  /**
   * 本阶段开盘价（以滚动24小时计）
   */
  private BigDecimal open;
  /**
   * 本阶段最新价（以滚动24小时计）
   */
  private BigDecimal close;
  /**
   * 本阶段最低价（以滚动24小时计）
   */
  private BigDecimal low;
  /**
   * 本阶段最高价（以滚动24小时计）
   */
  private BigDecimal high;
  /**
   * 以报价币种计量的交易量（以滚动24小时计）
   */
  private BigDecimal vol;
  /**
   * 当前的最高买价 [price, size]
   */
  @JSONField(deserialize = false)
  private PriceLevel bid;
  /**
   * 当前的最低卖价 [price, size]
   */
  @JSONField(deserialize = false)
  private PriceLevel ask;

}
