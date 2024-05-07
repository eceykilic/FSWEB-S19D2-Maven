package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddressById(Long id);
    Address addAddress(Address address);
    Address updateAddress(Long id, Address address);
    void deleteAddress(Long id);
}