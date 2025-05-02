package com.pratik.payGateBlend.payment.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdditionalParamDtl {
    @JsonAlias({"paramVal", "paramval"})
    private String paramVal;
    @JsonAlias({"paramName", "paramname"})
    private String paramName;
}
