package com.huobi.client.req.crossmargin;


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
public class GeneralRepayLoanRequest {
    /**
     * 还币账户ID
     */
    private String accountId;
    /**
     * 还币币种
     */
    private String currency;
    /**
     * 还币金额
     */
    private String amount;
    /**
     * 原始借币交易ID
     */
    private String transactId;

}
