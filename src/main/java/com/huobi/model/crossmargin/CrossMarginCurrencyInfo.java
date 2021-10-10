package com.huobi.model.crossmargin;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrossMarginCurrencyInfo {
  /**
   * 币种
   */
  private String currency;
  /**
   * 基础日币息率
   */
  private BigDecimal interestRate;
  /**
   * 最小允许借币金额
   */
  private BigDecimal minLoanAmt;
  /**
   * 最大允许借币金额
   */
  private BigDecimal maxLoanAmt;
  /**
   * 最大可借金额
   */
  private BigDecimal loanableAmt;
  /**
   * 抵扣后的实际币息率，如不适用抵扣或未启用抵扣则返回基础日币息率
   */
  private BigDecimal actualRate;

}
