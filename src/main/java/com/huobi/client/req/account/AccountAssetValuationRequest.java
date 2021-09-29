package com.huobi.client.req.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.huobi.constant.enums.AccountTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAssetValuationRequest {
  /**
   * 账户类型	NA
   * spot：现货账户， margin：逐仓杠杆账户，otc：OTC 账户，super-margin：全仓杠杆账户
   */
  AccountTypeEnum accountType;
  /**
   * 资产估值法币，即资产按哪个法币为单位进行估值。	BTC
   * 可选法币有：BTC、CNY、USD、JPY、KRW、GBP、TRY、EUR、RUB、VND、HKD、TWD、MYR、SGD、AED、SAR （大小写敏感）
   */
  String valuationCurrency;
  /**
   * 子用户的 UID，若不填，则返回API key所属用户的账户资产估值
   */
  Long subUid;

}
