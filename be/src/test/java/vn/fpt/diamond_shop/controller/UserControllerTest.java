package vn.fpt.diamond_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpt.diamond_shop.dto.UserSelfUpdateRequest;
import vn.fpt.diamond_shop.dto.UserUpdateRequest;
import vn.fpt.diamond_shop.entity.User;
import vn.fpt.diamond_shop.repository.UserRepository;
import vn.fpt.diamond_shop.security.EAuthProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    User temporalUser = new User();

    @BeforeEach
    public void setUp() {
        //create a temporal user for test update feature
        temporalUser.setId(1L);
        temporalUser.setName("User");
        temporalUser.setEmail("user@test.com");
        temporalUser.setImageUrl("https://example.com/test.jpg");
        temporalUser.setEmailVerified(true);
        temporalUser.setPassword("123123");
        temporalUser.setProvider(EAuthProvider.GOOGLE);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(temporalUser));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateExistedUser() throws Exception {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(1L);
        userUpdateRequest.setName("Test User");
        userUpdateRequest.setEmail("existing@test.com");
        userUpdateRequest.setImageUrl("https://example.com/image.jpg");

        mockMvc.perform(put("/shop/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userUpdateRequest)))
                .andExpect(status().isOk());

        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(userUpdateRequest.getId(), savedUser.getId());
        assertEquals(userUpdateRequest.getName(), savedUser.getName());
        assertEquals(userUpdateRequest.getEmail(), savedUser.getEmail());
        assertEquals(userUpdateRequest.getImageUrl(), savedUser.getImageUrl());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateNonExistingUser() throws Exception{
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(2L);
        userUpdateRequest.setName("Test User");
        userUpdateRequest.setEmail("existing@test.com");
        userUpdateRequest.setImageUrl("https://example.com/image.jpg");

        mockMvc.perform(put("/shop/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userUpdateRequest)))
                .andExpect(status().is4xxClientError());

        verify(userRepository, never()).save(any(User.class));
    }

    /**
     * Convert an object to a JSON string.
     *
     * @param obj the object to convert
     * @return the JSON string representation of the object
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}