package com.leyou.item.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SpuDetailDTO {
    private String group;
    private List<Param> params;
}
