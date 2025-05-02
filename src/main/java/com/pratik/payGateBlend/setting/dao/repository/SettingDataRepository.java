package com.pratik.payGateBlend.setting.dao.repository;

import com.pratik.payGateBlend.payment.dao.controller.PaymentDAOController;
import com.pratik.payGateBlend.setting.mapper.SettingDataMapper;
import com.pratik.payGateBlend.setting.model.SettingData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class SettingDataRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PaymentDAOController paymentDAOController;

    public Long saveSettingData(SettingData data) {
        String sql = """
                        INSERT INTO setting (
                            payment_gateway_id, project_id, config_data
                        ) VALUES (
                            :paymentGatewayId, :projectId, CAST(:configData AS json)
                        )
                    """;

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("paymentGatewayId", data.getPaymentGatewayId());
            params.addValue("projectId", data.getProjectId());

            String configJson = this
                                    .paymentDAOController
                                    .getJsonStringFromObject(
                                            data.getConfigData()
                                    );

            params.addValue("configData", configJson); // assuming it's already a JSON string or will be serialized

            KeyHolder keyHolder = new GeneratedKeyHolder();
            int cnt = namedParameterJdbcTemplate.update(sql, params, keyHolder);
            Long id = null;
            if (cnt > 0) {
                Map<String, Object> keys = keyHolder.getKeys();
                assert keys != null;
                Object id_obj = keys.get("id");
                id = Long.parseLong(id_obj.toString());
            }

            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SettingData getSettingDataById(Long id) {
        String sql = """
        SELECT *
        FROM setting
        WHERE id = :id
    """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, parameters, new SettingDataMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SettingData getSettingDataByProjectId(String projectId) {
        String sql = """
        SELECT *
        FROM setting
        WHERE project_id = :projectId
    """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("projectId", projectId);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, parameters, new SettingDataMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SettingData> getSettingDataList() {
        String sql = """
        SELECT *
        FROM setting
        ORDER BY id
    """;

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            return namedParameterJdbcTemplate.query(sql, parameters, new SettingDataMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
