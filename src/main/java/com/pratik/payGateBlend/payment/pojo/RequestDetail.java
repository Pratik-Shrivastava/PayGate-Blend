package com.pratik.payGateBlend.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestDetail {

        private String action;
        private String depMob;
        private String dpr;
        private Double paymentAmt;
        private List<ReqPaymentDetail> paymentDtls;
        private String respUrl;
        private String src;

}
