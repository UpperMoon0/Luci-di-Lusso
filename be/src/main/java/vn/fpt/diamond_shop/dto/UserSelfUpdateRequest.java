package vn.fpt.diamond_shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/*
    Add Setter
 */
@Getter
@Setter
public class UserSelfUpdateRequest {
    private String name;

    @Email
    private String email;

    private String imageUrl;
}
