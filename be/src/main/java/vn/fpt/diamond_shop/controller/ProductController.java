package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.service.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final IJewelryService jewelryService;
    private final IJewelrySizeService jewelrySizeService;
    private final IJewelryTypeService jewelryTypeService;
    private final IDiamondService diamondService;
    private final IDiamondCutService diamondCutService;
    private final IDiamondClarityService diamondClarityService;
    private final IDiamondColorService diamondColorService;
    private final IDiamondShapeService diamondShapeService;

    @GetMapping("/get-jewelry")
    public ResponseEntity<JewelryResponse> getJewelry(@RequestParam Long id) {
        Jewelry jewelry = jewelryService.getJewelryById(id);
        if (jewelry != null) {
            JewelryType type = jewelry.getType();
            List<JewelrySize> sizes = jewelrySizeService.getJewelrySizesByJewelryType(type);

            JewelryResponse response = new JewelryResponse(jewelry, sizes, jewelryService);
            response.setMessage("Jewelry retrieved successfully");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/get-jewelry-list")
    public ResponseEntity<JewelryListResponse> getJewelryList(@RequestBody JewelryListRequest request) {
        List<EJewelryType> types = request.getTypes();
        Integer minPrice = request.getMinPrice();
        Integer maxPrice = request.getMaxPrice();
        String keyword = request.getKeyword();

        List<Jewelry> filteredJewelries = jewelryService.getJewelriesByFilter(types, minPrice, maxPrice, keyword);

        JewelryListResponse response = new JewelryListResponse(filteredJewelries, jewelryService);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-jewelries")
    public ResponseEntity<List<Jewelry>> getAllJewelries() {
        List<Jewelry> jewelries = jewelryService.getAllJewelries();
        return ResponseEntity.ok(jewelries);
    }

    @PostMapping("/add-jewelry")
    public ResponseEntity<CommonResponse> addJewelry() {
        try {
            jewelryService.createNewJewelry();
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry added successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/update-jewelry")
    public ResponseEntity<CommonResponse> updateJewelry(@RequestBody JewelryUpdateRequest body) {
        try {
            jewelryService.updateJewelry(body);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/delete-jewelry")
    public ResponseEntity<CommonResponse> deleteJewelry(@RequestParam Long id) {
        jewelryService.deleteJewelryById(id);

        CommonResponse response = new CommonResponse();
        response.setMessage("Jewelry deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-jewelry-types")
    public ResponseEntity<JewelryTypesResponse> getAllTypes() {
        List<JewelryType> types = jewelryTypeService.getAll();
        JewelryTypesResponse response = new JewelryTypesResponse();
        response.setTypes(types);
        response.setMessage("Get all jewelry types successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-diamonds")
    public ResponseEntity<List<Diamond>> getAllDiamonds() {
        List<Diamond> diamonds = diamondService.getAllDiamonds();
        return ResponseEntity.ok(diamonds);
    }

    @DeleteMapping("/delete-diamond")
    public ResponseEntity<CommonResponse> deleteDiamond(@RequestParam long id) {
        diamondService.deleteDiamondById(id);
        CommonResponse response = new CommonResponse();
        response.setMessage("Diamond deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-diamond")
    public ResponseEntity<CommonResponse> updateDiamond(@RequestBody DiamondUpdateRequest request) {
        try {
            diamondService.updateDiamond(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond updated successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/add-diamond")
    public ResponseEntity<CommonResponse> addDiamond() {
        try {
            diamondService.createNewDiamond();
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        CommonResponse response = new CommonResponse();
        response.setMessage("Diamond added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-diamond")
    public ResponseEntity<Diamond> getDiamond(@RequestParam Long id) {
        Diamond diamond = diamondService.getDiamondById(id);
        if (diamond != null) {
            return ResponseEntity.ok(diamond);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
}
