package com.pratik.payGateBlend.payment.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResPaymentDetail {
    private String pmtCategory;
    private String deptCode;
    private String svcCode;
    private String drn;
    private String identificationNo;

    @JsonAlias({"totalAmount", "totalAmt"})
    private Double totalAmt;
    private String grn;
    private String grnTime;
    private String grnStatus;
    private String grnStatusDesc;
    private String bank;
    private String bankcode;
    private String paymentMode;
    private String brn;
    private String brnTime;
    private List<AdditionalParamDtl> additionalParamDtls;
}
