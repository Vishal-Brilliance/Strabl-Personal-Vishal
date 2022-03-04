package com.strabl.service.user.bdd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserAddress {

    private String status;
    private String addressdescriptrion;
    private String addresstitle;
    private String type;

    public UserAddress(String addressdescriptrion, String addresstitle, String type) {

    }

    public UserAddress(String status) {

    }

    public List<String> userAddressDeatils(){
        List<String> details = new ArrayList<>();
        details.add(addressdescriptrion);
        details.add(addresstitle);
        details.add(type);
        return details;
    }

    public List<String> statusDetails(){
        List<String> statusinfo = new ArrayList<>();
        statusinfo.add(status);
        return statusinfo;
    }

}
