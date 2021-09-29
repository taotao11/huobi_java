package com.huobi.client.req.market;

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
public class MarketHistoryTradeRequest {
  /**
   * btcusdt, ethbtc...（取值参考GET /v1/common/symbols）
   */
  private String symbol;
  /**
   * 返回的交易记录数量，最大值2000
   */
  private Integer size;

}
