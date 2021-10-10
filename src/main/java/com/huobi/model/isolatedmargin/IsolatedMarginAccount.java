package com.huobi.model.isolatedmargin;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.model.account.Balance;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsolatedMarginAccount {

  private Long id;

  private String symbol;
  /**
   * 爆仓价
   */
  private BigDecimal flPrice;

  private String flType;
  /**
   * 风险率
   */
  private BigDecimal riskRate;

  private String type;
  /**
   * 账户状态，working 正常,fl-sys 系统自动爆仓,fl-mgt 手动爆仓,fl-end 爆仓结束
   */
  private String state;
  /**
   * 借币账户详情列表
   */
  private List<Balance> balanceList;

}
