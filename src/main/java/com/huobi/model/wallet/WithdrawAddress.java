package com.huobi.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawAddress {
  /**
   * 币种		btc, ltc, bch, eth, etc ...(取值参考
   */
  private String currency;
  /**
   * 链名称
   */
  private String chain;
  /**
   * 地址备注
   */
  private String note;
  /**
   * 地址标签，如有
   */
  private String addressTag;
  /**
   * 地址
   */
  private String address;

}
