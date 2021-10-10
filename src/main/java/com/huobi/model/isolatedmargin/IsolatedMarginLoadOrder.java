package com.huobi.model.isolatedmargin;

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
public class IsolatedMarginLoadOrder {
  /**
   * 订单号
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 账户ID
   */
  private Long accountId;
  /**
   * 交易对
   */
  private String symbol;
  /**
   * 币种
   */
  private String currency;
  /**
   * 已支付原币金额（用于还息）
   */
  private BigDecimal paidCoin;
  /**
   * 已支付点卡金额（用于还息）
   */
  private BigDecimal paidPoint;
  /**
   * 抵扣金额（用于还息）
   */
  private BigDecimal deductAmount;
  /**
   * 抵扣率（用于还息）
   */
  private BigDecimal deductRate;
  /**
   * 抵扣币种（用于还息）
   */
  private String deductCurrency;
  /**
   * 借币本金总额
   */
  private BigDecimal loanAmount;
  /**
   * 未还本金
   */
  private BigDecimal loanBalance;
  /**
   * 币息率
   */
  private BigDecimal interestRate;
  /**
   * 币息总额
   */
  private BigDecimal interestAmount;
  /**
   * 未还币息
   */
  private BigDecimal interestBalance;
  /**
   * 订单状态
   */
  private String state;
  /**
   * 借币发起时间
   */
  private Long createdAt;
  /**
   * 更新时间
   */
  private Long updatedAt;
  /**
   * 最近一次计息时间
   */
  private Long accruedAt;

}
