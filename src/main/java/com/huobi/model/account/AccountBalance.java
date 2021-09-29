package com.huobi.model.account;

import java.util.List;

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
public class AccountBalance {
  /**
   * 	账户 ID
   */
  private Long id;
  /**
   * 账户状态	working：正常 lock：账户被锁定
   */
  private String type;
  /**
   * 账户类型	spot：现货账户, margin：逐仓杠杆账户, otc：OTC 账户, point：点卡账户,
   * super-margin：全仓杠杆账户, investment: C2C杠杆借出账户,
   * borrow: C2C杠杆借入账户，矿池账户: minepool, ETF账户: etf, 抵押借贷账户: crypto-loans
   */
  private String state;

  private List<Balance> list;

  private String subType;

}
