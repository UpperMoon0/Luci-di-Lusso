package vn.fpt.diamond_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import vn.fpt.diamond_shop.response.BaseResponse;
import vn.fpt.diamond_shop.response.Meta;

public class BaseController {
    public ResponseEntity<Object> ok(Object payload, String requestId) {
        BaseResponse response = new BaseResponse(new Meta(requestId, 200, "success", HttpStatus.OK.toString()),payload);
        return ResponseEntity.ok(response);
    }
}
