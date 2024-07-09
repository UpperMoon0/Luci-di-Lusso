package vn.fpt.diamond_shop.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends CommonResponse {
    private String accessToken;
    private String role;
}
