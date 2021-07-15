package com.leyou.order.client;

import com.leyou.order.dto.AddressDTO;

import javax.print.attribute.standard.Finishings;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Felix
 * @Description:
 */
public abstract class AddressClient {
    public static final List<AddressDTO> addressList = new ArrayList<AddressDTO>() {
        {
            AddressDTO address = AddressDTO.builder()
                    .id(1L)
                    .address("新买花园1号楼2单元401")
                    .state("山东省")
                    .city("青岛市")
                    .district("李沧区")
                    .zipCode("266100")
                    .name("张三")
                    .phone("17610000000")
                    .isDefault(false).build();
            add(address);
            AddressDTO address2 = AddressDTO.builder()
                    .id(2L)
                    .address("新买花园1号楼2单元401")
                    .state("北京市")
                    .city("北京市")
                    .district("海淀区")
                    .zipCode("100000")
                    .name("张三")
                    .phone("17610000000")
                    .isDefault(false).build();
            add(address2);

        }
    };

    public static AddressDTO findById(Long id) {
        for (AddressDTO addressDTO : addressList) {
            if (addressDTO.getId() == id) return addressDTO;
        }
        return null;
    }
}
