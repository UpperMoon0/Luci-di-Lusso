package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.service.IDiamondService;
import vn.fpt.diamond_shop.service.IJewelryService;
import vn.fpt.diamond_shop.service.IJewelrySizeService;
import vn.fpt.diamond_shop.service.JewelryTypeService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final IJewelryService jewelryService;
    private final IJewelrySizeService jewelrySizeService;
    private final IDiamondService diamondService;
    private final JewelryTypeService jewelryTypeService;

    @Autowired
    public ProductController(IJewelryService jewelryService,
                             IJewelrySizeService jewelrySizeService,
                             IDiamondService diamondService,
                             JewelryTypeService jewelryTypeService) {
        this.jewelryService = jewelryService;
        this.jewelrySizeService = jewelrySizeService;
        this.diamondService = diamondService;
        this.jewelryTypeService = jewelryTypeService;
    }

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

    @GetMapping("/get-all-jewelries")
    public ResponseEntity<JewelriesResponse> getAllJewelries() {
        List<Jewelry> jewelries = jewelryService.getAllJewelries();

        JewelriesResponse response = new JewelriesResponse(jewelries, jewelryService);
        response.setMessage("Get all jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-jewelries")
    public ResponseEntity<JewelriesResponse> getJewelries(@RequestBody @Valid JewelriesListRequest request) {
        List<EJewelryType> types = request.getTypes();
        Double minPrice = request.getMinPrice();
        Double maxPrice = request.getMaxPrice();

        List<Jewelry> filteredJewelries = jewelryService.getJewelriesByFilter(types, minPrice, maxPrice);

        JewelriesResponse response = new JewelriesResponse(filteredJewelries, jewelryService);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
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

    @PutMapping("/update-jewelry")
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
    public ResponseEntity<DiamondsResponse> getAllDiamonds() {
        List<Diamond> diamonds = diamondService.getAllDiamonds();
        DiamondsResponse response = new DiamondsResponse();
        response.setDiamonds(diamonds);
        response.setMessage("Get all diamonds successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-diamond")
    public ResponseEntity<CommonResponse> deleteDiamond(@RequestParam Integer id) {
        diamondService.deleteDiamondById(id);
        CommonResponse response = new CommonResponse();
        response.setMessage("Diamond deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-diamond")
    public ResponseEntity<CommonResponse> updateDiamond(@RequestBody DiamondUpdateRequest body) {
        try {
            diamondService.updateDiamond(body);
            CommonResponse response = new CommonResponse();
            response.setMessage("Diamond updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
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
}
