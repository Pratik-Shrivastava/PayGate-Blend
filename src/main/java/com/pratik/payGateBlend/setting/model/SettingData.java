package com.pratik.payGateBlend.setting.model;

import com.pratik.payGateBlend.setting.pojo.FieldValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SettingData {

    private Long id;
    private Long paymentGatewayId;
    private String projectId;
    private List<FieldValue> configData;

}
