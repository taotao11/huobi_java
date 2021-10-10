package com.huobi.model.isolatedmargin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IsolatedMarginSymbolInfo {
  /**
   * 	交易代码
   */
  private String symbol;

  private List<IsolatedMarginCurrencyInfo> currencies;

}
