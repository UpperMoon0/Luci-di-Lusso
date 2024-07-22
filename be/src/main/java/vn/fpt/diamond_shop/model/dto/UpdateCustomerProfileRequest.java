package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdateCustomerProfileRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    private String imageUrl;
    @NotNull
    private LocalDate dob;
}
