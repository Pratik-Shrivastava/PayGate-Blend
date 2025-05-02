package com.pratik.payGateBlend.payment.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratik.payGateBlend.common.utils.RowMapperUtil;
import com.pratik.payGateBlend.payment.model.PaymentHistoryData;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
public class PaymentHistoryDataMapper implements RowMapper<PaymentHistoryData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PaymentHistoryData mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {

        PaymentHistoryData paymentData = new PaymentHistoryData();

        paymentData.setId(RowMapperUtil.getLong(rs, "id"));
        paymentData.setPaymentGatewayId(RowMapperUtil.getLong(rs, "payment_gateway_id"));
        paymentData.setSettingId(RowMapperUtil.getLong(rs, "setting_id"));
        paymentData.setTransactionId(RowMapperUtil.getString(rs, "transaction_id"));
        paymentData.setUserId(RowMapperUtil.getString(rs, "user_id"));

        paymentData.setDepositorName(RowMapperUtil.getString(rs, "depositor_name"));
        paymentData.setDepositorEmail(RowMapperUtil.getString(rs, "depositor_email"));
        paymentData.setDepositorNumber(RowMapperUtil.getString(rs, "depositor_number"));
        paymentData.setDepositorAddress(RowMapperUtil.getString(rs, "depositor_address"));

        paymentData.setAction(RowMapperUtil.getString(rs, "action"));
        paymentData.setPaymentAmount(RowMapperUtil.getDouble(rs, "payment_amount"));
        paymentData.setPaymentCategory(RowMapperUtil.getString(rs, "payment_category"));
        paymentData.setPaymentMode(RowMapperUtil.getString(rs, "payment_mode"));
        paymentData.setRemarks(RowMapperUtil.getString(rs, "remarks"));
        paymentData.setStatus(RowMapperUtil.getString(rs, "status"));

        paymentData.setCreatedAt(RowMapperUtil.getTimestamp(rs, "created_at"));
        paymentData.setUpdatedAt(RowMapperUtil.getTimestamp(rs, "updated_at"));

        try {
            String additionalInfoJson = RowMapperUtil.getString(rs, "additional_info");
            if (additionalInfoJson != null) {
                Map<String, Object> additionalInfo = objectMapper.readValue(additionalInfoJson, new TypeReference<>() {});
                paymentData.setAdditionalInfo(additionalInfo);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Or use logger
        }

        return paymentData;

    }
}
