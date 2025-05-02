package com.pratik.payGateBlend.payment.model;

import com.pratik.payGateBlend.payment.pojo.PaymentRequest;
import com.pratik.payGateBlend.payment.pojo.ReqPaymentDetail;
import com.pratik.payGateBlend.payment.pojo.RequestDetail;
import com.pratik.payGateBlend.payment.pojo.SourceDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentHistoryData {
    private Long id;
    private Long paymentGatewayId;
    private Long settingId;
    private String transactionId;
    private String userId;
    private String depositorName;
    private String depositorEmail;
    private String depositorNumber;
    private String depositorAddress;
    private String action;
    private Double paymentAmount;
    private String paymentCategory;
    private String paymentMode;
    private String remarks;
    private String status;
    private Map<String, Object> additionalInfo;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public PaymentHistoryData(PaymentRequest paymentRequest) {
        Map<String, Object> info = new HashMap<>();

        if (paymentRequest.getReq() != null) {
            RequestDetail req = paymentRequest.getReq();
            this.action = req.getAction();
            this.paymentAmount = req.getPaymentAmt();

            // Additional fields to be added to additionalInfo
            info.put("identification_no", req.getPaymentDtls().get(0).getIdentificationNo());
            info.put("src_code", req.getSrc());

            if (req.getPaymentDtls() != null && !req.getPaymentDtls().isEmpty()) {
                ReqPaymentDetail pd = req.getPaymentDtls().get(0);

                this.depositorEmail = pd.getDepEmail();
                this.depositorAddress = pd.getDepAddress();
                this.paymentCategory = pd.getPmtCategory();
                this.remarks = pd.getRemarks();
                this.depositorName = pd.getDepName();
                this.depositorNumber = req.getDepMob();
                this.paymentAmount = req.getPaymentAmt();
                this.transactionId = req.getDpr();

                // Add to additionalInfo
                info.put("drn", pd.getDrn());
                info.put("deptCode", pd.getDeptCode());
                info.put("depType", pd.getDepType());
                info.put("onBehalfOf", pd.getOnBehalfOf());
                info.put("svcCode", pd.getSvcCode());
                info.put("additionalParamDtls", pd.getAdditionalParamDtls());
                info.put("subServiceHoaDetails", pd.getSubServiceHoaDetails());
//                info.put("identificationNo", pd.getIdentificationNo());

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    if (pd.getPeriodFrom() != null) {
                        LocalDate from = LocalDate.parse(pd.getPeriodFrom(), formatter);
                        info.put("periodFrom", Date.valueOf(from));
                    }
                    if (pd.getPeriodTo() != null) {
                        LocalDate to = LocalDate.parse(pd.getPeriodTo(), formatter);
                        info.put("periodTo", Date.valueOf(to));
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Consider logging
                }
            }
        }

        if (paymentRequest.getSrc() != null) {
            SourceDetail src = paymentRequest.getSrc();
            this.userId = src.getUserId();
            info.put("srcId", src.getSrcId());
            info.put("ip", src.getIp());
            info.put("mac", src.getMac());
            info.put("sysTimeStamp", src.getSysTimeStamp());
        }

        this.additionalInfo = info;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }


}
