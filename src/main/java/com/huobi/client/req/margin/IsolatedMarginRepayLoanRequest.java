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
public class IsolatedMarginRepayLoanRequest {
  /**
   * 借币订单 ID，写在 url path 中
   */
  private Long orderId;
  /**
   * 归还币种数量
   */
  private BigDecimal amount;

}
