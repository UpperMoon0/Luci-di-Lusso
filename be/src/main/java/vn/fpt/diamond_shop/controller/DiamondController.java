package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.service.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/diamond")
@RestController
public class DiamondController {

    private final IDiamondColorService diamondColorService;

    @PostMapping("/save-diamond-color")
    public ResponseEntity<CommonResponse> addDiamondColor(@Valid @RequestBody DiamondColorRequest request) {
        try {
            diamondColorService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond color added successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/delete-diamond-color")
    public ResponseEntity<CommonResponse> deleteDiamondColor(@RequestParam long id) {
        try {
            diamondColorService.delete(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond color deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
