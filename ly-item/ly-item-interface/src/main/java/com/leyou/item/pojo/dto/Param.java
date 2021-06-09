package com.leyou.item.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Param {
    private String k;

    private Boolean searchable;

    private Boolean global;

    private String v;

    private String unit;

    private Double numerical;

    private List<String> options;
}
