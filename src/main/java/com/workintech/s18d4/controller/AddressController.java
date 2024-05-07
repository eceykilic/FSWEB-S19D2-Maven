package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}