package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        address.setId(id);
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}