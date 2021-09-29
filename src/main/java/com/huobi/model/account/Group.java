package com.huobi.model.account;

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
public class Group {
  /**
   * 点卡分组ID
   */
  Long groupId;
  /**
   * 点卡到期日（unix time in millisecond）
   */
  Long expiryDate;
  /**
   * 剩余数量
   */
  String remainAmt;

}
