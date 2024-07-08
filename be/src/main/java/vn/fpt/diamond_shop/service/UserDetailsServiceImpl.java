package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.IUserRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UserDetailsServiceImpl(IUserRepository userRepository,
                                  JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
    }

    public User getUserByToken(String jwtToken) throws InvalidJwtTokenException {
        boolean tokenValid = tokenProvider.validateToken(jwtToken);
        if (!tokenValid) {
            throw new InvalidJwtTokenException();
        }
        String username = tokenProvider.getUsernameFromJWT(jwtToken);
        return userRepository.findByUsername(username).orElse(null);
    }

    public void updateCustomerProfile(String jwtToken, String fullName, String address, String phone, String imageUrl, LocalDate dob) {
        User user = getUserByToken(jwtToken);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setImageUrl(imageUrl);
        user.setDob(dob);
        userRepository.save(user);
    }
}
