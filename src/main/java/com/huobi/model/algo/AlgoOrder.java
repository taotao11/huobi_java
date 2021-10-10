package com.huobi.model.algo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgoOrder {
  /**
   * 账户编号
   */
  private Long accountId;
  /**
   * 订单来源
   */
  private String source;
  /**
   * 用户自编订单号
   */
  private String clientOrderId;
  /**
   * 订单编号（仅对orderStatus=triggered有效）
   */
  private Long orderId;
  /**
   * 交易代码
   */
  private String symbol;
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
   * 订单有效期
   */
  private String timeInForce;
  /**
   * 订单类型
   */
  private String orderType;
  /**
   * 触发价
   */
  private BigDecimal stopPrice;
  /**
   * 回调幅度（仅对追踪委托有效）
   */
  private BigDecimal trailingRate;
  /**
   * 订单创建时间
   */
  private Long orderOrigTime;
  /**
   * 订单最近更新时间
   */
  private Long lastActTime;
  /**
   * 订单触发时间（仅对orderStatus=triggered有效）
   */
  private Long orderCreateTime;
  /**
   * 订单状态（triggered,canceled,rejected）
   */
  private String orderStatus;
  /**
   * 订单被拒状态码（仅对orderStatus=rejected有效）
   */
  private Integer errCode;
  /**
   * 订单被拒错误消息（仅对orderStatus=rejected有效）
   */
  private String errMessage;

}
