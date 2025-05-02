package com.pratik.payGateBlend.setting.pojo;

import com.pratik.payGateBlend.payment.pojo.AdditionalParamDtl;
import com.pratik.payGateBlend.payment.pojo.SubServiceHoaDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConfigPaymentDetails {
    private List<AdditionalParamDtl> additionalParamDtls;
//    private String depAddress;
//    private String depEmail;
//    private String depName;
    private String deptCode;
    private String depType;
//    private String drn;
//    private String identificationNo;
//    private String onBehalfOf;
//    private String periodFrom;
//    private String periodTo;
    private String pmtCategory;
//    private String remarks;
    private List<SubServiceHoaDetail> subServiceHoaDetails;
    private String svcCode;
}
