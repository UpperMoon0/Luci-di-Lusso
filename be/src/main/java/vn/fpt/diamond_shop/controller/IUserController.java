package vn.fpt.diamond_shop.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpt.diamond_shop.model.dto.UserUpdateRequest;
import vn.fpt.diamond_shop.model.entity.User;

import javax.validation.Valid;

@RequestMapping("/shop/user")
public interface IUserController {
    User updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest);
}
