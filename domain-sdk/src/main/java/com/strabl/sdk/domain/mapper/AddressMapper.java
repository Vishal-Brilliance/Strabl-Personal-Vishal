package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.domain.entity.Address;

public class AddressMapper {

  public static AddressDTO toAddressDTO(Address address) {
    return AddressDTO.builder()
            .uuid(address.getUuid())
            .city(address.getCity())
            .area(address.getArea())
            .building(address.getBuilding())
            .flatNumber(address.getFlatNumber())
            .streetName(address.getStreetName())
            .user_id(address.getUser().getId())
            .status(address.isStatus())
            .build();
  }
}
