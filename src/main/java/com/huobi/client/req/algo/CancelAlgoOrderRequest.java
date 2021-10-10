package com.huobi.client.req.algo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelAlgoOrderRequest {
  /**
   * 用户自编订单号（可多填，以数组的形式传输）
   */
  private List<String> clientOrderIds;

}
