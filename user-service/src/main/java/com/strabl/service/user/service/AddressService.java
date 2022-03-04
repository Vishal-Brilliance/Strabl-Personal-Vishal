package com.strabl.service.user.service;

import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.domain.entity.Address;

import java.util.List;

public interface AddressService {
    AddressDTO addaddress(Address address);

    void deleteAddressById(Integer id);

    List<Address> getAddressById(Integer id, Integer user_id);
}
