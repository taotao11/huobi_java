package com.huobi.client.req.margin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IsolatedMarginLoanInfoRequest {
  /**
   * 交易代码 (可多选，以逗号分隔)
   */
  private String symbols;

}
