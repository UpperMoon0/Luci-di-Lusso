package vn.fpt.diamond_shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponse extends CommonResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
