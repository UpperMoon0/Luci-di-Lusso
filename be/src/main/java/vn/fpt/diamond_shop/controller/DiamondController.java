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
    private final IDiamondCutService diamondCutService;
    private final IDiamondClarityService diamondClarityService;
    private final IDiamondShapeService diamondShapeService;

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

    @DeleteMapping("/delete-diamond-color")
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

    @PostMapping("/save-diamond-cut")
    public ResponseEntity<CommonResponse> addDiamondCut(@Valid @RequestBody DiamondCutRequest request) {
        try {
            diamondCutService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond cut added successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-diamond-cut")
    public ResponseEntity<CommonResponse> deleteDiamondCut(@RequestParam long id) {
        try {
            diamondCutService.delete(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond cut deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/save-diamond-clarity")
    public ResponseEntity<CommonResponse> addDiamondClarity(@Valid @RequestBody DiamondClarityRequest request) {
        try {
            diamondClarityService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond clarity added successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-diamond-clarity")
    public ResponseEntity<CommonResponse> deleteDiamondClarity(@RequestParam long id) {
        try {
            diamondClarityService.delete(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond clarity deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/save-diamond-shape")
    public ResponseEntity<CommonResponse> addDiamondShape(@Valid @RequestBody DiamondShapeRequest request) {
        try {
            diamondShapeService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond shape added successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-diamond-shape")
    public ResponseEntity<CommonResponse> deleteDiamondShape(@RequestParam long id) {
        try {
            diamondShapeService.delete(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond shape deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
