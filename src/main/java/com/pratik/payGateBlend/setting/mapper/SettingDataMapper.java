package com.pratik.payGateBlend.setting.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratik.payGateBlend.common.utils.RowMapperUtil;
import com.pratik.payGateBlend.setting.model.SettingData;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingDataMapper implements RowMapper<SettingData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SettingData mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {

        SettingData settingData = new SettingData();

        settingData.setId(RowMapperUtil.getLong(rs, "id"));
        settingData.setPaymentGatewayId(RowMapperUtil.getLong(rs, "payment_gateway_id"));
        settingData.setProjectId(RowMapperUtil.getString(rs, "project_id"));
        settingData.setConfigData(RowMapperUtil.getListOfFieldValues(rs, "config_data"));
        return settingData;

    }
}
