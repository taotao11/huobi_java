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
public class GetHistoryAlgoOrdersResult {

  private List<AlgoOrder> list;
  /**
   * 下页起始编号（仅在查询结果需要分页返回时传此字段）
   */
  private Long nextId;

}
