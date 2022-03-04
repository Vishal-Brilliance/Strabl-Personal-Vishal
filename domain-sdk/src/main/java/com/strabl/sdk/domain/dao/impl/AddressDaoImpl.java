package com.strabl.sdk.domain.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.strabl.sdk.domain.dao.AddressDao;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.repository.AddressRepository;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {

	private final AddressRepository addressRepository;
	private final EntityManager entityManager;

	@Override
	public Address save(Address address) {
		address.setCreatedAt(Instant.now());
		address.setUpdatedAt(Instant.now());

		Address savedAddress = addressRepository.save(address);
		entityManager.refresh(savedAddress);
		return savedAddress;
	}

	@Override
	public void deleteAddressById(Integer id) {
		addressRepository.deleteById(id);
	}

	@Override
	public List<Address> getAddressById(Integer id, Integer user_id) {
		return addressRepository.findById(id,user_id);
	}


}
