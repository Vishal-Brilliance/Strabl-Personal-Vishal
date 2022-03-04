package com.strabl.service.user.api;

import com.strabl.sdk.common.dto.response.AddressDTO;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.repository.AddressRepository;
import com.strabl.service.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("v1/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/address")
    public ResponseDTO<AddressDTO> addaddress( @Valid @RequestBody Address address) {
        return ResponseDTO.success(addressService.addaddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<String> deleteAddressById(@PathVariable Integer id) {
        addressService.deleteAddressById(id);
        return ResponseDTO.success("Address Deleted successfully");
    }

    @GetMapping("/getaddress")
    public List<Address> getAddressById(@RequestParam Integer id, @RequestParam Integer user_id) {
        return addressService.getAddressById(id, user_id);
    }


}
