package vn.fpt.diamond_shop.controller;

import org.springframework.web.bind.annotation.RequestBody;
import vn.fpt.diamond_shop.model.dto.UserUpdateRequest;
import vn.fpt.diamond_shop.model.entity.User;

import javax.validation.Valid;

public interface IUserController {
    User updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest);
}
