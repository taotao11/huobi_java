package com.huobi.client.req.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.DepthSizeEnum;
import com.huobi.constant.enums.DepthStepEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarketDepthRequest {
  /**
   * 交易对	btcusdt, ethbtc...（取值参考
   */
  private String symbol;
  /**
   * 返回深度的数量	5，10，20
   */
  private DepthSizeEnum depth;
  /**
   * 深度的价格聚合度，具体说明见下方	step0，step1，step2，step3，step4，step5
   */
  private DepthStepEnum step;

}
