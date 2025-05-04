package com.pratik.payGateBlend.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqDoubleVerification {
    private String dpr;
    private String src;
    private String action;
    private String paymentDate;
}
