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
public class WithdrawChainQuota {
  /**
   * 链名称
   */
  private String chain;
  /**
   * 单次最大提币金额
   */
  private String maxWithdrawAmt;
  /**
   * 当日提币额度
   */
  private String withdrawQuotaPerDay;
  /**
   * 当日提币剩余额度
   */
  private String remainWithdrawQuotaPerDay;
  /**
   * 每年最大提款金额
   */
  private String withdrawQuotaPerYear;
  /**
   * 年内剩余提款额度
   */
  private String remainWithdrawQuotaPerYear;
  /**
   * 最大提款总额
   */
  private String withdrawQuotaTotal;
  /**
   * 剩余总提款额度
   */
  private String remainWithdrawQuotaTotal;
}
