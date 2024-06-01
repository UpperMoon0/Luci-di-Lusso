package vn.fpt.diamond_shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


/*
    Add Setter and change the annotation NotBlank into NotNull on id since NotNull is more appropriated with numeric identifier
 */
@Getter
@Setter
public class UserUpdateRequest {
    @NotNull
    private Long id;

    private String name;

    @Email
    private String email;

    private String imageUrl;
}