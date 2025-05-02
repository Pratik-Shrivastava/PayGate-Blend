package com.pratik.payGateBlend.setting.dao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pratik.payGateBlend.setting.dao.repository.SettingDataRepository;
import com.pratik.payGateBlend.setting.model.SettingData;
import com.pratik.payGateBlend.setting.pojo.FieldValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
@Controller
public class SettingDAOController {
    private final SettingDataRepository settingDataRepository;

    public Long saveSettingData(SettingData data) throws JsonProcessingException {
        return this.settingDataRepository.saveSettingData(data);
    }

    public SettingData getSettingDataById(Long id) {
        return this.settingDataRepository.getSettingDataById(id);
    }
    public SettingData getSettingDataByProjectId(String projectId) {
        return this.settingDataRepository.getSettingDataByProjectId(projectId);
    }
    
    public List<SettingData> getSettingDataList() {
        return this.settingDataRepository.getSettingDataList();
    }

    public Map<String, String> extractUserInputFields(List<FieldValue> fieldValueList) {
        Map<String, String> userInputMap = new LinkedHashMap<>();

        for (FieldValue fieldValue : fieldValueList) {
            if (!fieldValue.isKeyValue()) {
                // Direct USER_INPUT fields
                if ("USER_INPUT".equalsIgnoreCase(fieldValue.getValue())) {
                    userInputMap.put(fieldValue.getField(), "");
                }
            } else if (fieldValue.getMap() != null) {
                // Key-Value type fields
                for (Map<String, String> mapEntry : fieldValue.getMap()) {
                    for (Map.Entry<String, String> entry : mapEntry.entrySet()) {
                        String key = entry.getKey();
                        String val = entry.getValue();
                        if ("USER_INPUT".equalsIgnoreCase(val)) {
                            userInputMap.put(mapEntry.get(key.equalsIgnoreCase("paramVal") ? "paramName" : "hoa"), "");
                        }
                    }
                }
            }
        }

        return userInputMap;
    }



}
