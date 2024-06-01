package vn.fpt.diamond_shop.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserUpdateRequest {
    @NotBlank
    private Long id;

    private String name;

    @Email
    private String email;

    private String imageUrl;
}