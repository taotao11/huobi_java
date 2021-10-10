package com.huobi.model.algo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelAlgoOrderResult {
  /**
   * 已接受订单clientOrderId列表
   */
  private List<String> accepted;
  /**
   * 已拒绝订单clientOrderId列表
   */
  private List<String> rejected;

}
