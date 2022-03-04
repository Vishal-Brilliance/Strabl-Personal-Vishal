package com.strabl.service.user.service.impl;

import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.domain.dao.AddressDao;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.mapper.AddressMapper;
import com.strabl.sdk.domain.repository.AddressRepository;
import com.strabl.service.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final AddressRepository addressRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDTO addaddress(Address address) {

        Address savedAddress = addressDao.save(address);
        return AddressMapper.toAddressDTO(savedAddress);
    }

    @Override
    public void deleteAddressById(Integer id) {
        addressDao.deleteAddressById(id);
    }

    @Override
    public List<Address> getAddressById(Integer id, Integer user_id) {

        return addressDao.getAddressById(id,user_id);
    }
}
