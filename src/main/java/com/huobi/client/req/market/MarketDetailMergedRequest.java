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
public class MarketDetailMergedRequest {
  /**
   * 交易对	btcusdt, ethbtc...（取值参考GET /v1/common/symbols
   */
  private String symbol;

}
