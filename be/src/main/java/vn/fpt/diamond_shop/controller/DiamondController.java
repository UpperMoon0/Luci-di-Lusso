package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.service.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/diamond")
@RestController
public class DiamondController {

    private final IDiamondColorService diamondColorService;
    private final IDiamondCutService diamondCutService;
    private final IDiamondClarityService diamondClarityService;
    private final IDiamondShapeService diamondShapeService;
    private final IDiamondService diamondService;

    @GetMapping("/get-diamond")
    public ResponseEntity<Diamond> getDiamond(@RequestParam long id) {
        Diamond diamond = diamondService.findById(id);
        return ResponseEntity.ok(diamond);
    }

    @GetMapping("/get-all-diamond-properties")
    public ResponseEntity<DiamondPropertiesResponse> getAllDiamondProperties() {
        List<DiamondCut> cuts = diamondCutService.findAll();
        List<DiamondClarity> clarities = diamondClarityService.findAll();
        List<DiamondColor> colors = diamondColorService.findAll();
        List<DiamondShape> shapes = diamondShapeService.findAll();

        DiamondPropertiesResponse response = new DiamondPropertiesResponse(cuts, clarities, colors, shapes);
        response.setMessage("Get all diamond properties successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-diamonds")
    public ResponseEntity<List<Diamond>> getAllDiamonds() {
        List<Diamond> diamonds = diamondService.getAllDiamonds();
        return ResponseEntity.ok(diamonds);
    }

    @PostMapping("/save-diamond-color")
    public ResponseEntity<CommonResponse> addDiamondColor(@Valid @RequestBody SaveDiamondColorRequest request) {
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

    @PostMapping("/toggle-diamond-color-status")
    public ResponseEntity<CommonResponse> deleteDiamondColor(@RequestParam long id) {
        try {
            diamondColorService.toggleStatus(id);
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
    public ResponseEntity<CommonResponse> addDiamondCut(@Valid @RequestBody SaveDiamondCutRequest request) {
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

    @PostMapping("/toggle-diamond-cut-status")
    public ResponseEntity<CommonResponse> deleteDiamondCut(@RequestParam long id) {
        try {
            diamondCutService.toggleStatus(id);
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
    public ResponseEntity<CommonResponse> addDiamondClarity(@Valid @RequestBody SaveDiamondClarityRequest request) {
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

    @PostMapping("/toggle-diamond-clarity-status")
    public ResponseEntity<CommonResponse> deleteDiamondClarity(@RequestParam long id) {
        try {
            diamondClarityService.toggleStatus(id);
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
    public ResponseEntity<CommonResponse> addDiamondShape(@Valid @RequestBody SaveDiamondShapeRequest request) {
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

    @PostMapping("/toggle-diamond-shape-status")
    public ResponseEntity<CommonResponse> deleteDiamondShape(@RequestParam long id) {
        try {
            diamondShapeService.toggleStatus(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond shape deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/toggle-diamond-status")
    public ResponseEntity<CommonResponse> deleteDiamond(@RequestParam long id) {
        try {
            diamondService.toggleStatus(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/save-diamond")
    public ResponseEntity<CommonResponse> updateDiamond(@Valid @RequestBody SaveDiamondRequest request) {
        try {
            diamondService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond updated successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
