package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.address.City;
import com.vlad.tms.diploma.model.entity.RoleUser;
import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CityService cityService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user, City city, Address address) {
        User userFromBD = userRepository.findByUsername(user.getUsername());
        if (userFromBD != null) {
            return false;
        }
        address.setCity(city);
        address.setCountry(countryService.addCountryBLR());
        addressService.saveAddress(address);

        user.setAddress(address);
        user.setActive(false);
        user.setRoleUsers(Collections.singleton(RoleUser.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));   //зашифровали пароль пользователя

        userRepository.save(user);

        sendMessag(user);
        return true;
    }

    public void updateProfile(User user, User userNew) {
            user.setEmail(userNew.getEmail());
            user.setUsername(userNew.getUsername());
            user.setPhoneNumber(userNew.getPhoneNumber());
            user.setFirstName(userNew.getFirstName());
            user.setLastName(userNew.getLastName());
            user.getAddress().setCity(userNew.getAddress().getCity());
            user.getAddress().setCountry(countryService.addCountryBLR());
            user.getAddress().setStreet(userNew.getAddress().getStreet());
            user.getAddress().setNumberHouse(userNew.getAddress().getNumberHouse());

            userRepository.save(user);
    }

    private void sendMessag(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to my site. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {   //если пользователь не будет найден по коду, то активация не удалась
            return false;
        }

        user.setActivationCode(null);  //означает, что пользователь подвердил активацию
        user.setActive(true);
        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

