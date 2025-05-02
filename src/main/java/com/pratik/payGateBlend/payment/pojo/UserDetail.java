package com.pratik.payGateBlend.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetail {
    private String name;
    private String userId;
    private String mobile;
    private Double amount;
    private String address;
    private String email;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String accountNo;
    private String ifsc;
    private String onBehalfOf;
    private Date periodFrom;
    private Date periodTo;
    private String remarks;
}
