package com.strabl.sdk.domain.repository;

import com.strabl.sdk.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository
    extends JpaRepository<Address, Integer>, JpaSpecificationExecutor<Address> {

    @Query(nativeQuery=true,value="select *  from address where user_id= ?2 && id=?1")
    List<Address> findById(Integer id, Integer user_id);

    void deleteById(Integer id);



}
