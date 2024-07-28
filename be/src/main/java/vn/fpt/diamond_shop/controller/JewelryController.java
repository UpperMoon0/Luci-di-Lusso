package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.service.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/jewelry")
@RestController
public class JewelryController {

    private final IJewelryService jewelryService;
    private final IJewelrySizeService jewelrySizeService;
    private final IJewelryTypeService jewelryTypeService;
    private final IJewelryCollectionService jewelryCollectionService;

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
        List<Jewelry> activeJewelries = filteredJewelries.stream()
                .filter(j -> "ACTIVE".equals(j.getStatus()) && "ACTIVE".equals(j.getDiamond().getStatus()))
                .toList();

        JewelryListResponse response = new JewelryListResponse(activeJewelries, jewelryService);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-jewelries")
    public ResponseEntity<List<Jewelry>> getAllJewelries() {
        List<Jewelry> jewelries = jewelryService.getAllJewelries();

        return ResponseEntity.ok(jewelries);
    }

    @PostMapping("/save-jewelry")
    public ResponseEntity<CommonResponse> updateJewelry(@Valid @RequestBody JewelryUpdateRequest body) {
        try {
            jewelryService.saveJewelry(body);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry updated successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/toggle-jewelry-status")
    public ResponseEntity<CommonResponse> deleteJewelry(@RequestParam Long id) {
        try {
            jewelryService.toggleStatus(id);

            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/get-all-jewelry-types")
    public ResponseEntity<JewelryTypesResponse> getAllTypes() {
        List<JewelryType> types = jewelryTypeService.getAll();
        JewelryTypesResponse response = new JewelryTypesResponse();
        response.setTypes(types);
        response.setMessage("Get all jewelry types successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-collections")
    public ResponseEntity<List<JewelryCollection>> getAllCollections() {
        List<JewelryCollection> collections = jewelryCollectionService.getAllCollections();
        return ResponseEntity.ok(collections);
    }

    @GetMapping("/get-collection")
    public ResponseEntity<JewelryCollection> getCollection(@RequestParam Long id) {
        JewelryCollection collection = jewelryCollectionService.getCollectionById(id);
        if (collection != null) {
            return ResponseEntity.ok(collection);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
