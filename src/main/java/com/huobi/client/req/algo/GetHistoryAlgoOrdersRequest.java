package com.huobi.client.req.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.huobi.constant.enums.QuerySortEnum;
import com.huobi.constant.enums.algo.AlgoOrderSideEnum;
import com.huobi.constant.enums.algo.AlgoOrderStatusEnum;
import com.huobi.constant.enums.algo.AlgoOrderTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetHistoryAlgoOrdersRequest {
  /**
   * 账户编号
   */
  private Long accountId;
  /**
   * 交易代码
   */
  private String symbol;
  /**
   * 订单方向	buy,sell
   */
  private AlgoOrderSideEnum orderSide;
  /**
   * 订单类型	limit,market
   */
  private AlgoOrderTypeEnum orderType;
  /**
   * 订单状态	canceled,rejected,triggered
   */
  private AlgoOrderStatusEnum orderStatus;
  /**
   * 远点时间
   */
  private Long startTime;
  /**
   * 近点时间
   */
  private Long endTime;
  /**
   * 检索方向	asc - 由远及近, desc - 由近及远
   */
  private QuerySortEnum sort;
  /**
   * 	单页最大返回条目数量	[1-500]
   */
  private Integer limit;
  /**
   * 起始编号（仅在下页查询时有效）
   */
  private Long fromId;


}
