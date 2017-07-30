package com.triador.springboot.service;

import com.triador.springboot.model.Address;
import com.triador.springboot.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        return addressRepository.findOne(id);
    }

    @Override
    public void updateAddress(Address address) {
        saveAddress(address);
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.delete(id);
    }
}
