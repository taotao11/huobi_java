package com.huobi.model.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.OrderStateEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CancelFailedResult {
  /**
   * 	订单编号（如用户创建订单时包含order-id，返回中也须包含此字段）
   */
  private Long orderId;
  /**
   * 订单被拒错误信息（仅对被拒订单有效）
   */
  private String errMsg;
  /**
   * 订单被拒错误码（仅对被拒订单有效）
   */
  private String errCode;
  /**
   * 当前订单状态（若有）
   */
  private Integer orderState;

}
