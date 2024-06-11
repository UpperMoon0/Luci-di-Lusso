package com.anhntv.ecom.services.auth;

import com.anhntv.ecom.constants.UserRole;
import com.anhntv.ecom.dto.SignupRequest;
import com.anhntv.ecom.dto.UserDTO;
import com.anhntv.ecom.entities.User;
import com.anhntv.ecom.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO createUser(SignupRequest signupRequest) {
            User user = new User();

            user.setEmail(signupRequest.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
            user.setName(signupRequest.getName());
            user.setRole(UserRole.CUSTOMER);
            User createdUser = userRepository.save(user);

            UserDTO dto = new UserDTO();
            dto.setId(createdUser.getId());

            return dto;
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createManagerAccount() {
        User managerAccount = userRepository.findByRole(UserRole.MANAGER);
        if (null ==managerAccount) {
            User user = new User();
            user.setEmail("manager@test.com");
            user.setName("Manager");
            user.setRole(UserRole.MANAGER);
            user.setPassword(bCryptPasswordEncoder.encode("manager"));
            userRepository.save(user);
        }
    }
}
