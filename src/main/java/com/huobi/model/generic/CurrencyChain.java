package com.huobi.model.generic;

import java.util.List;

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
public class CurrencyChain {
  /**
   * 币种
   */
  private String currency;
  /**
   * 币种状态	normal,delisted
   */
  private String instStatus;

  private List<Chain> chains;

}
