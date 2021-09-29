package com.huobi.client.req.account;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.huobi.constant.enums.AccountFuturesTransferTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountFuturesTransferRequest {
  /**
   * 币种, e.g. btc
   */
  private String currency;
  /**
   * 划转数量
   */
  private BigDecimal amount;
  /**
   * 划转类型	从合约账户到现货账户：“futures-to-pro”，
   * 从现货账户到合约账户： “pro-to-futures”
   */
  private AccountFuturesTransferTypeEnum type;
}
