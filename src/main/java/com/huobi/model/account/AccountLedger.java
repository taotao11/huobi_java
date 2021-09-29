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
public class AccountLedger {

	/**
	 * Account ID
	 * 账户编号
	 */
	private Long accountId;

	/**
	 * Currency
	 * 币种 （缺省值所有币种）
	 */
	private String currency;

	/**
	 * Amount change (positive value if income, negative value if outcome)
	 * 变动金额（入账为正 or 出账为负）
	 */
	private BigDecimal transactAmt;

	/**
	 * Amount change types
	 * 变动类型	transfer（划转）
	 */
	private String transactType;
	/**
	 * 划转类型（仅对transactType=transfer有效）
	 * otc-to-pro（otc到现货）, pro-to-otc（现货到otc）,
	 * futures-to-pro（交割合约到现货）, pro-to-futures（现货到交割合约）,
	 * dm-swap-to-pro（币本位永续合约到现货）, dm-pro-to-swap（现货到币本位永续合约）,
	 * margin-transfer-in（转入到逐仓杠杆）, margin-transfer-out（从逐仓杠杆转出）,
	 * pro-to-super-margin（现货到全仓杠杆）, super-margin-to-pro（全仓杠杆到现货）,
	 * master-transfer-in（转入到母用户）, master-transfer-out（从母用户转出）,
	 * sub-transfer-in（转入到子用户）, sub-transfer-out（从子用户转出）
	 */
	private String transferType;
	/**
	 * transaction Id
	 * 交易流水号
	 */
	private Long transactId;

	/**
	 * Acccount transferred from
	 * 付款方账户ID
	 */
	private Long transferer;

	/**
	 * Acccount transferred to
	 * 收款方账户ID
	 */
	private Long transferee;

	/**
	 * Transaction time (database time)
	 */
	private Long transactTime;

}
