package com.huobi.client.req.margin;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.MarginTransferDirectionEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsolatedMarginTransferRequest {

  private MarginTransferDirectionEnum direction;
  /**
   * 交易对, e.g. btcusdt, ethbtc
   */
  private String symbol;
  /**
   * 币种
   */
  private String currency;
  /**
   * 划转数量
   */
  private BigDecimal amount;

}
