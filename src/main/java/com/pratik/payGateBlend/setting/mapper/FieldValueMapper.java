package com.pratik.payGateBlend.setting.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratik.payGateBlend.common.utils.RowMapperUtil;
import com.pratik.payGateBlend.setting.pojo.FieldValue;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldValueMapper implements RowMapper<FieldValue> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FieldValue mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {

        FieldValue fieldValue = new FieldValue();

        fieldValue.setField(RowMapperUtil.getString(rs, "field"));
        fieldValue.setValue(RowMapperUtil.getString(rs, "value"));
        fieldValue.setValues(RowMapperUtil.getListOfStrings(rs, "values"));
        return fieldValue;

    }
}
