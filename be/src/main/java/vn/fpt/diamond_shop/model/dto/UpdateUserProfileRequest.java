package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserProfileRequest {
    private String fullName;
    private String address;
    private String phone;
    private String imageUrl;
    private LocalDate dob;
}
