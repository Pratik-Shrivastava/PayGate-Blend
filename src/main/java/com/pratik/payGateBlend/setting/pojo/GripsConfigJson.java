package com.pratik.payGateBlend.setting.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GripsConfigJson {
    private String action;
    private List<ConfigPaymentDetails> paymentDtls;
    private String src;
    private Double totalAmt;
}
