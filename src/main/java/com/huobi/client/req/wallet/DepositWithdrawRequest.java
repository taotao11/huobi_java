package com.huobi.client.req.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.DepositWithdrawTypeEnum;
import com.huobi.constant.enums.QueryDirectionEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepositWithdrawRequest {
  /**
   * 充值或提币		deposit 或 withdraw,子用户仅可用deposit
   */
  private DepositWithdrawTypeEnum type;
  /**
   * 币种		btc, ltc, bch, eth, etc ...(取值参考
   */
  private String currency;
  /**
   * 查询起始 ID	缺省时，默认值direct相关。当direct为‘prev’时，from 为1 ，
   * 从旧到新升序返回；当direct为’next‘时，from为最新的一条记录的ID，从新到旧降序返回
   */
  private Long from;
  /**
   * 查询记录大小
   */
  private Integer size;
  /**
   * 默认为“prev” （升序）	“prev” （升序）or “next” （降序）
   */
  private QueryDirectionEnum direction;

}
