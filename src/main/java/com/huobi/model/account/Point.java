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
public class Point {
  /**
   * 账户ID
   */
  Long accountId;
  /**
   * 账户状态（working 正常, lock 锁定, fl-sys 系统自动爆仓,
   * fl-mgt 手动爆仓, fl-end 爆仓结束, fl-negative 穿仓）
   */
  String accountStatus;
  /**
   * 账户余额
   */
  String acctBalance;
  /**
   * 点卡分组ID列表
   */
  List<Group> groupIds;
}
