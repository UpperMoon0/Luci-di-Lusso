package vn.fpt.diamond_shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.EAuthProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class RegisterRequest {
    @Pattern(regexp = "^[a-zA-Z0-9]*$",
            message = "Username can only contain letters and numbers")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one number, one lowercase letter, one uppercase letter, one special character, and have a minimum length of 8 characters")
    private String password;

    @Email
    private String email;

    @NotBlank
    private String fullName;

    @NotNull
    private EAuthProvider provider;
}
