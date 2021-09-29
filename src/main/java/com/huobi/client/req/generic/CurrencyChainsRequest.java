package com.huobi.client.req.generic;

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
public class CurrencyChainsRequest {
  /**
   * 币种	btc, ltc, bch, eth, etc ...(取值参考GET /v1/common/currencys)
   */
  private String currency;
  /**
   * true or false (如不填，缺省为true)
   */
  @Builder.Default
  private boolean authorizedUser = true;

}
