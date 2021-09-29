package com.huobi.client.req.account;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import com.huobi.constant.enums.AccountLedgerTransactTypeEnum;
import com.huobi.constant.enums.QuerySortEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLedgerRequest {
  /**
   * 账户编号
   */
  private Long accountId;
  /**
   * 币种 （缺省值所有币种）
   */
  private String currency;
  /**
   * 变动类型，可多填 （缺省值all） 枚举值： transfer
   */
  private List<AccountLedgerTransactTypeEnum> types;
  /**
   * 远点时间（取值范围及缺省值见注1）
   */
  private Long startTime;
  /**
   * 近点时间（取值范围及缺省值见注2）
   */
  private Long endTime;
  /**
   * 检索方向（asc 由远及近, desc 由近及远，缺省值desc）
   */
  private QuerySortEnum sort;
  /**
   * 单页最大返回条目数量 [1-500] （缺省值100）
   */
  private Integer limit;
  /**
   * 起始编号（仅在下页查询时有效，见注3）
   */
  private Long fromId;

  public String getTypesString(){
    String typeString = null;
    if (this.getTypes() != null && this.getTypes().size() > 0) {
      typeString = StringUtils.join(types,",");
    }
    return typeString;
  }

}
