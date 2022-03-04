package com.strabl.sdk.domain.dao;

import com.strabl.sdk.domain.entity.Address;

import java.util.List;

public interface AddressDao {

    Address save(Address address);

    void deleteAddressById(Integer id);

    List<Address> getAddressById(Integer id, Integer user_id);
}
