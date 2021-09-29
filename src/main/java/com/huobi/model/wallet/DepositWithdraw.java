package com.huobi.model.wallet;

import java.math.BigDecimal;

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
public class DepositWithdraw {
  /**
   * 充币或者提币订单id，翻页查询时from参数取自此值
   */
  private Long id;
  /**
   * 类型	'deposit', 'withdraw', 子用户仅有deposit
   */
  private String type;
  /**
   * 币种
   */
  private String currency;
  /**
   * 交易哈希。如果是“快速提币”，则提币不通过区块链，该值为空。
   */
  private String txHash;
  /**
   * 链名称
   */
  private String chain;
  /**
   * 个数
   */
  private BigDecimal amount;
  /**
   * 目的地址
   */
  private String address;
  /**
   * 地址标签
   */
  private String addressTag;
  /**
   * 手续费
   */
  private BigDecimal fee;
  /**
   * 状态
   */
  private String state;
  /**
   * 提币失败错误码，仅type为”withdraw“，且state为”reject“、”wallet-reject“和”failed“时有
   */
  private String errorCode;
  /**
   * 提币失败错误描述，仅type为”withdraw“，且state为”reject“、”wallet-reject“和”failed“时有。
   */
  private String errorMessage;
  /**
   * 发起时间
   */
  private Long createdAt;
  /**
   * 最后更新时间
   */
  private Long updatedAt;

}
