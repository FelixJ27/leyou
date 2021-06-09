package com.leyou.item.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
@Data
public class SpecialSpecSerialize {
    @JsonProperty("4")
    private List<String> color;
    @JsonProperty("12")
    private List<String> memory;
    @JsonProperty("13")
    private List<String> storage;
}
