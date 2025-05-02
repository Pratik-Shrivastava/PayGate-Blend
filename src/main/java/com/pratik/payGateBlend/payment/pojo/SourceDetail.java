package com.pratik.payGateBlend.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SourceDetail {

        private String ip;
        private String mac;
        private String srcId;
        private String sysTimeStamp;
        private String userId;

}
