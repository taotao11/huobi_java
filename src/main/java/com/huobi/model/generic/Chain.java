package com.huobi.model.generic;

import java.math.BigDecimal;

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
public class Chain {
  /**
   *链名称
   */
  private String chain;
  /**
   * 底层链名称
   */
  private String baseChain;
  /**
   * 底层链协议
   */
  private String baseChainProtocol;
  /**
   * 是否动态手续费（仅对固定类型有效，withdrawFeeType=fixed）
   */
  private Boolean isDynamic;
  /**
   * 安全上账所需确认次数（达到确认次数后允许提币）
   */
  private int numOfConfirmations;
  /**
   * 快速上账所需确认次数（达到确认次数后允许交易但不允许提币）
   */
  private int numOfFastConfirmations;
  /**
   * 充币状态
   */
  private String depositStatus;
  /**
   * 单次最小充币金额
   */
  private BigDecimal minDepositAmt;
  /**
   * 提币状态
   */
  private String withdrawStatus;
  /**
   * 单次最小提币金额
   */
  private BigDecimal minWithdrawAmt;
  /**
   * 单次最大提币金额
   */
  private BigDecimal maxWithdrawAmt;
  /**
   * 当日提币额度（新加坡时区）
   */
  private BigDecimal withdrawQuotaPerDay;
  /**
   * 当年提币额度
   */
  private BigDecimal withdrawQuotaPerYear;
  /**
   * 总提币额度
   */
  private BigDecimal withdrawQuotaTotal;
  /**
   * 提币精度
   */
  private int withdrawPrecision;
  /**
   * 提币手续费类型（特定币种在特定链上的提币手续费类型唯一）
   */
  private  String withdrawFeeType;
  /**
   * 单次提币手续费（仅对固定类型有效，withdrawFeeType=fixed）
   */
  private BigDecimal transactFeeWithdraw;
  /**
   * 最小单次提币手续费（仅对区间类型和有下限的比例类型有效，withdrawFeeType=circulated or ratio）
   */
  private BigDecimal minTransactFeeWithdraw;
  /**
   * 最大单次提币手续费（仅对区间类型和有上限的比例类型有效，withdrawFeeType=circulated or ratio）
   */
  private BigDecimal maxTransactFeeWithdraw;
  /**
   * 单次提币手续费率（仅对比例类型有效，withdrawFeeType=ratio）
   */
  private BigDecimal transactFeeRateWithdraw;

}
