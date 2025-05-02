package com.pratik.payGateBlend.setting.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class FieldValue {

    private String field;
    private boolean keyValue;
    private String value;
    private List<String> values;
    private List<Map<String, String>> map;

    @JsonCreator
    public FieldValue(
            @JsonProperty("field") String field,
            @JsonProperty("is_key_value") boolean keyValue,
            @JsonProperty("value") String value,
            @JsonProperty("values") List<String> values,
            @JsonProperty("map")List<Map<String, String>> map
            ) {
        this.field = field;
        this.keyValue = keyValue;
        this.value = value;
        this.values = values;
        this.map = map;
    }

    public FieldValue(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public FieldValue(String field, List<String> values) {
        this.field = field;
        this.values = values;
    }
}
