package com.pratik.payGateBlend.payment.dao.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratik.payGateBlend.payment.mapper.PaymentHistoryDataMapper;
import com.pratik.payGateBlend.payment.model.PaymentHistoryData;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class PaymentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();



    public Long savePaymentHistoryData(@NotNull PaymentHistoryData paymentData) {
        String sql = """
                    INSERT INTO payment_history (
                        payment_gateway_id, setting_id, transaction_id, user_id,
                        depositor_name, depositor_email, depositor_number, depositor_address,
                        action, payment_amount, payment_category, payment_mode,
                        remarks, status, additional_info,
                        created_at, updated_at
                    ) VALUES (
                        :paymentGatewayId, :settingId, :transactionId, :userId,
                        :depositorName, :depositorEmail, :depositorNumber, :depositorAddress,
                        :action, :paymentAmount, :paymentCategory, :paymentMode,
                        :remarks, :status, :additionalInfo,
                        :createdAt, :updatedAt
                    )
                """;

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("paymentGatewayId", paymentData.getPaymentGatewayId());
            params.addValue("settingId", paymentData.getSettingId());
            params.addValue("transactionId", paymentData.getTransactionId());
            params.addValue("userId", paymentData.getUserId());

            params.addValue("depositorName", paymentData.getDepositorName());
            params.addValue("depositorEmail", paymentData.getDepositorEmail());
            params.addValue("depositorNumber", paymentData.getDepositorNumber());
            params.addValue("depositorAddress", paymentData.getDepositorAddress());

            params.addValue("action", paymentData.getAction());
            params.addValue("paymentAmount", paymentData.getPaymentAmount());
            params.addValue("paymentCategory", paymentData.getPaymentCategory());
            params.addValue("paymentMode", paymentData.getPaymentMode()); // if null, will be saved as null

            params.addValue("remarks", paymentData.getRemarks());
            params.addValue("status", paymentData.getStatus());

            String additionalInfoJson = objectMapper.writeValueAsString(paymentData.getAdditionalInfo());
            params.addValue("additionalInfo", additionalInfoJson, Types.OTHER);

            params.addValue("createdAt", paymentData.getCreatedAt());
            params.addValue("updatedAt", paymentData.getUpdatedAt());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            int cnt = namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] {"id"});

            Long id = null;
            if (cnt > 0) {
                Number idObj = keyHolder.getKey();
                if (idObj != null) {
                    id = idObj.longValue();
                }
            }

            return id;
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with logger
            return null;
        }
    }


    public boolean updatePaymentHistoryData(
            String status,
            PaymentHistoryData paymentHistoryData
    ) {
        String sql = """
                    UPDATE payment_history SET
                        status = :status,
                        payment_mode = :paymentMode,
                        additional_info = :additionalInfo,
                        updated_at = CURRENT_TIMESTAMP
                    WHERE id = :id
                """;

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", paymentHistoryData.getId());
            params.addValue("status",status);
            params.addValue("paymentMode", paymentHistoryData.getPaymentMode());

            String additionalInfoJson = objectMapper.writeValueAsString(paymentHistoryData.getAdditionalInfo());
            params.addValue("additionalInfo", additionalInfoJson, Types.OTHER);


            int cnt = namedParameterJdbcTemplate.update(sql, params);
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PaymentHistoryData getPaymentHistoryDataByDrn(String drn) {
        String sql = """
				SELECT *
				FROM payment_detail
				WHERE drn = :drn
				""";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("drn", drn);

        try {
            return namedParameterJdbcTemplate
                    .queryForObject(sql, parameters, new PaymentHistoryDataMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public PaymentHistoryData getPaymentHistoryDataByDpr(String dpr) {
        String sql = """
				SELECT *
				FROM payment_history
				WHERE transaction_id = :dpr
				""";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("dpr", dpr);

        try {
            return namedParameterJdbcTemplate
                    .queryForObject(sql, parameters, new PaymentHistoryDataMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }




}
