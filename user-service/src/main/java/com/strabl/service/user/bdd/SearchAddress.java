package com.strabl.service.user.bdd;

public class SearchAddress {

    public String displayAddress(UserAddress userAddress){
        if(userAddress.userAddressDeatils().contains(userAddress.getAddresstitle() + userAddress.getAddressdescriptrion() + userAddress.getType())){
            return userAddress.getAddresstitle() + userAddress.getAddressdescriptrion() + userAddress.getType();
        }
        return null;
    }

    public String dispalyStatus(UserAddress addressStatus){
        if(addressStatus.statusDetails().contains(addressStatus.getStatus())){
            return addressStatus.getStatus();
        }
        return null;
    }
}
