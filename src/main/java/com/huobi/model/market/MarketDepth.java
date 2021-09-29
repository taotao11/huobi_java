package com.huobi.model.market;

import java.util.List;

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
public class MarketDepth {
  /**
   * 	内部字段
   */
  private long version;
  /**
   * 调整为新加坡时间的时间戳，单位毫秒
   */
  private Long ts;
  /**
   * 当前的所有买单 [price, size]
   */
  private List<PriceLevel> bids;
  /**
   * 当前的所有卖单 [price, size]
   */
  private List<PriceLevel> asks;

}
