package com.huobi.client.req.wallet;

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
public class CreateWithdrawRequest {
  /**
   * 提币地址
   */
  private String address;
  /**
   * 提币数量
   */
  private BigDecimal amount;
  /**
   * 转账手续费
   */
  private BigDecimal fee;
  /**
   * 资产类型
   */
  private String currency;
  /**
   * 取值参考GET /v2/reference/currencies,例如提USDT至OMNI时须设置此参数为"usdt"，
   * 提USDT至TRX时须设置此参数为"trc20usdt"，其他币种提币无须设置此参数
   */
  private String chain;
  /**
   * 虚拟币共享地址tag，适用于xrp，xem，bts，steem，eos，xmr
   */
  private String addrTag;

}
