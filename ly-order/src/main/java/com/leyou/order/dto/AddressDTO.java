package com.leyou.order.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Felix
 * @Description:
 */
@Data
@Builder(toBuilder = true)
public class AddressDTO {
    private Long id;
    private String name;
    private String phone;
    private String state;
    private String city;
    private String district;
    private String address;
    private String zipCode;
    private Boolean isDefault;
}
