package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class LoginResponse extends CommonResponse {
    @Setter
    private String accessToken;
    private final String tokenType = "Bearer";
}
