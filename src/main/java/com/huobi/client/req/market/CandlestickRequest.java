package com.huobi.client.req.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huobi.constant.enums.CandlestickIntervalEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandlestickRequest {
  /**
   * 交易对	btcusdt, ethbtc等（如需获取杠杆ETP净值K线，净值symbol = 杠杆ETP交易对symbol + 后缀‘nav’，例如：btc3lusdtnav）
   */
  private String symbol;
  /**
   * 返回数据时间粒度，也就是每根蜡烛的时间区间	1min, 5min, 15min, 30min, 60min, 4hour, 1day, 1mon, 1week, 1year
   */
  private CandlestickIntervalEnum interval;
  /**
   * 返回 K 线数据条数	[1-2000]
   */
  private Integer size;

}
