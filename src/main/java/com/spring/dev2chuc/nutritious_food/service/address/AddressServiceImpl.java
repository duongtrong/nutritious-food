package com.spring.dev2chuc.nutritious_food.service.address;

import com.spring.dev2chuc.nutritious_food.model.Address;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.AddressRequest;
import com.spring.dev2chuc.nutritious_food.repository.AddressRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<Address> getAllByUser(User user) {
        return addressRepository.findAllByUserAndStatus(user, Status.ACTIVE.getValue());
    }

    @Override
    public Address store(User user, AddressRequest addressRequest) {
        Address address = new Address(addressRequest.getTitle(), user);
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, AddressRequest addressRequest) {
        Address address = addressRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (address == null) return null;
        address.setTitle(addressRequest.getTitle());
        return addressRepository.save(address);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
    }

    @Override
    public boolean belongToUser(Long id, User user) {
        Address address = addressRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        return address != null && address.getUser() == user;
    }

    @Override
    public boolean delete(Long id) {
        Address address = addressRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (address == null) return false;
        address.setStatus(Status.DEACTIVE.getValue());
        addressRepository.save(address);
        return true;
    }
}
