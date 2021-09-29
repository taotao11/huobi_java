package com.huobi.client.req.wallet;

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
public class DepositAddressRequest {
  /**
   * btc, ltc, bch, eth, etc ...(取值参考GET /v1/common/currencys)
   */
  private String currency;

}
