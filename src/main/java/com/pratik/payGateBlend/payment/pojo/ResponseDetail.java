package com.pratik.payGateBlend.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDetail {
    private String dpr;
    private String src;
    private String action;
    private String gpr;
    private Double paymentAmt;
    private String paymentStatus;
    private String paymentStatusDesc;
    private List<ResPaymentDetail> paymentDtls;

}
