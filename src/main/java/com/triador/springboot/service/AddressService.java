package com.triador.springboot.service;

import com.triador.springboot.model.Address;

public interface AddressService {
    Address findById(Long id);

    void updateAddress(Address address);

    void saveAddress(Address address);

    void deleteAddressById(Long id);
}
