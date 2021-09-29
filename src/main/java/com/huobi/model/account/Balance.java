package com.huobi.model.account;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The balance of specified account.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Balance {
  /**
   * 币种
   */
  private String currency;
  /**
   * trade: 交易余额，frozen: 冻结余额,
   * loan: 待还借贷本金, interest: 待还借贷利息,
   * lock: 锁仓, bank: 储蓄
   */
  private String type;
  /**
   * 余额
   */
  private BigDecimal balance;

}
