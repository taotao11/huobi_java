package com.huobi.client.req.margin;

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
public class IsolatedMarginApplyLoanRequest {

  private String symbol;
  /**
   * 	币种
   */
  private String currency;
  /**
   * 借币数量（精度：3位小数）
   */
  private BigDecimal amount;

}
