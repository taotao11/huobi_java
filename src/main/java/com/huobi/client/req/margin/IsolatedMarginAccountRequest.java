package com.huobi.client.req.margin;

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
public class IsolatedMarginAccountRequest {
  /**
   * 交易对，作为get参数
   * 如果不传则不返回可转余额(transfer-out-available)和可借余额(loan-available)
   */
  private String symbol;
  /**
   * 子用户编号（母用户查询子用户借币详情时，此字段必填）	如不填，缺省查询当前用户借币详情
   */
  private Long subUid;

}
