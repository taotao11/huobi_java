package com.huobi.client.req.account;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointRequest {
  /**
   * 子用户UID（仅对母用户查询子用户点卡余额场景有效）
   */
  Long subUid;

}
