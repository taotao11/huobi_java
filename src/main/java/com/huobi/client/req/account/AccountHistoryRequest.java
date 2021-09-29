package com.huobi.client.req.account;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import com.huobi.constant.enums.QuerySortEnum;
import com.huobi.constant.enums.AccountHistoryTransactTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryRequest {
  /**
   * 账户编号,取值参考 GET /v1/account/accounts
   */
  private Long accountId;
  /**
   * 	币种,即btc, ltc, bch, eth, etc ...(取值参考GET /v1/common/currencys)
   */
  private String currency;
  /**
   * 变动类型，可多选，以逗号分隔，包含动账类型列表详见注3	all
   * trade (交易),etf（ETF申购）, transact-fee（交易手续费）, fee-deduction（手续费抵扣）, transfer（划转）, credit（借币）,
   * liquidation（清仓）, interest（币息）, deposit（充币），withdraw（提币）, withdraw-fee（提币手续费）, exchange（兑换）,
   * other-types（其他）,rebate（交易返佣）
   */
  private List<AccountHistoryTransactTypeEnum> types;
  /**
   * 远点时间 unix time in millisecond. 以transact-time为key进行检索. 查询窗口最大为1小时. 窗口平移范围为最近30天.
   */
  private Long startTime;
  /**
   * 近点时间unix time in millisecond. 以transact-time为key进行检索. 查询窗口最大为1小时. 窗口平移范围为最近30天.
   */
  private Long endTime;
  /**
   * 检索方向	asc	asc or desc
   */
  private QuerySortEnum sort;
  /**
   * 最大条目数量	100
   */
  private Integer size;

  public String getTypesString(){
    String typeString = null;
    if (this.getTypes() != null && this.getTypes().size() > 0) {
      typeString = StringUtils.join(types,",");
    }
    return typeString;
  }

}
