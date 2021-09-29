package com.huobi.client.req.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawAddressRequest {
  /**
   * 币种
   */
  private String currency;
  /**
   * 链名称
   */
  private String chain;
  /**
   * 地址备注
   */
  private String note;
  /**
   * 单页最大返回条目数量
   */
  private Integer limit;
  /**
   * 起始编号（提币地址ID，仅在下页查询时有效，详细见备注）
   */
  private Long fromId;

}
