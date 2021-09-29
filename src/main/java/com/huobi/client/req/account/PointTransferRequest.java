package com.huobi.client.req.account;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointTransferRequest {
  /**
   * 转出方UID
   */
  private Long fromUid;
  /**
   * 转入方UID
   */
  private Long toUid;
  /**
   * 点卡分组ID
   */
  private Long groupId;
  /**
   * 划转数量（最高精度: 8位小数）
   */
  private BigDecimal amount;

}
