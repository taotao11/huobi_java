package com.huobi.model.crossmargin;


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
public class GeneralRepayLoanResult {
    /**
     * 还币交易ID
     */
    private String repayId;
    /**
     * 还币交易时间（unix time in millisecond）
     */
    private long repayTime;
}
