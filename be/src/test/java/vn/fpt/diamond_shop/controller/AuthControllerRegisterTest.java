package vn.fpt.diamond_shop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpt.diamond_shop.AbstractTest;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Vo Nguyen Minh Nhat
 * This class is used to test the register feature.
 * Testers do not edit this class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerRegisterTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    public void setUp() {
        when(userRepository.existsByEmail("existing@test.com")).thenReturn(true);
        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(userRepository.existsByUsername("ExistingUser")).thenReturn(true);
        when(userRepository.existsByUsername("TestUser")).thenReturn(false);

        when(passwordEncoder.encode(any(CharSequence.class))).thenAnswer(invocation -> "encoded_" + invocation.getArgument(0));
    }

    @Test
    public void testRegisterEmailAlreadyInUse() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("existing@test.com");
        registerRequest.setUsername("TestUser");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setProvider("GITHUB");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isBadRequest());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterUsernameAlreadyInUse() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@test.com");
        registerRequest.setUsername("ExistingUser");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setProvider("LOCAL");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isBadRequest());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterEmailAndUsernameAlreadyInUse() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("existing@test.com");
        registerRequest.setUsername("ExistingUser");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setProvider("GOOGLE");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isBadRequest());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@test.com");
        registerRequest.setUsername("TestUser");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setProvider("LOCAL");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isOk());

        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(registerRequest.getEmail(), savedUser.getEmail());
        assertEquals(registerRequest.getUsername(), savedUser.getUsername());
        assertEquals(registerRequest.getProvider(), savedUser.getProvider().toString());
        assertEquals("encoded_" + registerRequest.getPassword(), savedUser.getPassword());
    }
}
