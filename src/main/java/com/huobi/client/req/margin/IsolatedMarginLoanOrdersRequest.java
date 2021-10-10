package com.huobi.client.req.margin;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.LoanOrderStateEnum;
import com.huobi.constant.enums.QueryDirectionEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsolatedMarginLoanOrdersRequest {
  /**
   * 交易对
   */
  private String symbol;
  /**
   * 查询开始日期, 日期格式yyyy-mm-dd
   */
  private Date startDate;
  /**
   * 查询结束日期, 日期格式yyyy-mm-dd
   */
  private Date endDate;
  /**
   * 状态列表，可以支持多个状态，用逗号分隔
   */
  private List<LoanOrderStateEnum> states;
  /**
   * 查询起始 ID
   */
  private Long from;
  /**
   * 查询方向		prev 向前，时间（或 ID）正序；next 向后，时间（或 ID）倒序）
   */
  private QueryDirectionEnum direction;
  /**
   * 查询记录大小	100	[1, 100]
   */
  private Integer size;
  /**
   * 子用户编号（母用户查询子用户借币订单时，此字段必填）	如不填，缺省查询当前用户借币订单
   */
  private Long subUid;

  public String getStatesString(){
    String stateString = null;
    if (this.getStates() != null && this.getStates().size() > 0) {
      StringBuffer statesBuffer = new StringBuffer();
      this.getStates().forEach(orderType -> {
        statesBuffer.append(orderType.getCode()).append(",");
      });
      stateString = statesBuffer.substring(0, statesBuffer.length() - 1);
    }
    return stateString;
  }

}
