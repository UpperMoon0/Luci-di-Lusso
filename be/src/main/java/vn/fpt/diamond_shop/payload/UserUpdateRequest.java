package vn.fpt.diamond_shop.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
public class UserUpdateRequest {
    private String name;

    @Email
    private String email;

    private String imageUrl;
}