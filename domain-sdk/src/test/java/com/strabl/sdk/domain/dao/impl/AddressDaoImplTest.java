package com.strabl.sdk.domain.dao.impl;

import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddressDaoImplTest {

    @InjectMocks
    private AddressDaoImpl addressDaoService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void addAddressTest(){
        Address address = new Address();
        addressDaoService.save(address);
        verify(addressRepository).save(address);
    }

    @Test
    public void deleteAddressTest(){
        addressRepository.deleteById(16);
        verify(addressRepository).deleteById(16);
    }

    @Test
    public void getAddressTest(){
        Integer id= 18;
        addressRepository.findById(id);
        verify(addressRepository).findById(id);

    }

}
