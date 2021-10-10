package com.huobi.client.req.algo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.huobi.constant.enums.algo.AlgoOrderSideEnum;
import com.huobi.constant.enums.algo.AlgoOrderTimeInForceEnum;
import com.huobi.constant.enums.algo.AlgoOrderTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlgoOrderRequest {
  /**
   * 用户自编订单号（最长64位）
   */
  private String clientOrderId;
  /**
   * 账户编号
   * 当前仅支持spot账户ID、margin账户ID、super-margin账户ID，
   * 暂不支持c2c-margin账户ID
   */
  private Long accountId;
  /**
   * 	交易代码
   */
  private String symbol;

  /**
   * 订单方向 buy,sell
   */
  private AlgoOrderSideEnum orderSide;
  /**
   * 订单类型	limit,market
   */
  private AlgoOrderTypeEnum orderType;
  /**
   * gtc for orderType=limit; ioc for orderType=market	订单有效期
   * gtc(对orderType=market无效),boc(对orderType=market无效),
   * ioc,fok(对orderType=market无效)
   */
  private AlgoOrderTimeInForceEnum timeInForce;
  /**
   * 订单价格（对市价单无效）
   */
  private BigDecimal orderPrice;
  /**
   * 订单数量（对市价买单无效）
   */
  private BigDecimal orderSize;
  /**
   * 订单金额（仅对市价买单有效）
   */
  private BigDecimal orderValue;
  /**
   * 触发价
   */
  private BigDecimal stopPrice;
  /**
   * 回调幅度（仅对追踪委托有效）	[0.001-0.050]
   */
  private BigDecimal trailingRate;

}
