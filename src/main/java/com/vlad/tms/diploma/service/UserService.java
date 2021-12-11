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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
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

    public boolean updateProfile(User user, User userNew, Address address, String cityName) {
        User userFromBD = userRepository.findByUsername(userNew.getUsername());

        if (user.getUsername().equals(userNew.getUsername()) || userFromBD == null) {

            user.setUsername(userNew.getUsername());
            user.setPhoneNumber(userNew.getPhoneNumber());
            user.setFirstName(userNew.getFirstName());
            user.setLastName(userNew.getLastName());
            user.getAddress().setCity(cityService.getCity(cityName));
            user.getAddress().setCountry(countryService.addCountryBLR());
            user.getAddress().setStreet(address.getStreet());
            user.getAddress().setNumberHouse(address.getNumberHouse());

            userRepository.save(user);

            return true;
        } else {
            return false;
        }
    }

    private void sendMessag(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Пройдите по ссылке, чтобы подтвердить регистрацию: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Код активации", message);
        }
    }

    public void sendMessageRestorePass(User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Пройдите по ссылке, чтобы изменить пароль: http://localhost:8080/restore/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Восстановление пароля", message);
        }
    }


    public void sendMessageRestoreEmail(User user) {
        user.setActivationCode(UUID.randomUUID().toString());
        save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            " Чтобы изменить e-mail вам необходимо пройти по ссылке: http://localhost:8080/restoreEmail/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Обновление E-mail", message);
        }
    }

    public User findByCode(String code){
        return userRepository.findByActivationCode(code);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
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

    public User findEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

