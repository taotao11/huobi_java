package com.huobi.model.wallet;

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
public class DepositAddress {
  /**
   * 币种
   */
  private String currency;
  /**
   * 充币地址
   */
  private String address;
  /**
   * 充币地址标签
   */
  private String addressTag;
  /**
   * 链名称
   */
  private String chain;

}
