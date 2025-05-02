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
public class EncryptedResponse {

    private String encData;

    @JsonAlias({"cs", "checksum"})
    private String cs;

    @JsonAlias({"src", "Src"})
    private String Src;
}
