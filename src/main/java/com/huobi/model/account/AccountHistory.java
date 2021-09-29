package com.huobi.model.account;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistory {
  /**
   * 账户编号
   */
  private Long accountId;
  /**
   * 币种
   */
  private String currency;
  /**
   * 变动金额（入账为正 or 出账为负）
   */
  private BigDecimal transactAmt;
  /**
   * 变动类型
   */
  private String transactType;
  /**
   * 可用余额
   */
  private BigDecimal availBalance;
  /**
   * 账户余额
   */
  private BigDecimal acctBalance;
  /**
   * 交易时间（数据库记录时间）
   */
  private Long transactTime;
  /**
   * 数据库记录编号（全局唯一）
   */
  private Long recordId;
  /**
   * next-id	long	下页起始编号（仅在查询结果需要分页返回时包含此字段，见注2）
   */
  private Long nextId;

}
