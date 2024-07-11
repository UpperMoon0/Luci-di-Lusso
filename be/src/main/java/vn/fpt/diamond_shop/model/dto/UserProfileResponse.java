package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import vn.fpt.diamond_shop.model.entity.User;

import java.time.LocalDate;

@Getter
public class UserProfileResponse extends CommonResponse {
    private final String fullName;
    private final String address;
    private final String phone;
    private final String imageUrl;
    private final LocalDate dob;

    public UserProfileResponse(User user) {
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.imageUrl = user.getImageUrl();
        this.dob = user.getDob();
    }
}
