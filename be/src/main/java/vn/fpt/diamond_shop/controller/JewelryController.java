package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            List<JewelrySize> sizes = jewelrySizeService.getByJewelryType(type);

            JewelryResponse response = new JewelryResponse(jewelry, sizes);
            response.setMessage("Jewelry retrieved successfully");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/get-jewelry-list")
    public ResponseEntity<JewelryListResponse> getJewelryList(@RequestBody JewelryListRequest request) {
        List<String> types = request.getTypes();
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
    public ResponseEntity<CommonResponse> saveJewelry(@Valid @RequestBody SaveJewelryRequest body) {
        try {
            jewelryService.save(body);
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
    public ResponseEntity<List<JewelryType>> getAllJewelryTypes() {
        List<JewelryType> types = jewelryTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @PostMapping("/save-jewelry-type")
    public ResponseEntity<CommonResponse> saveJewelryType(@Valid @RequestBody SaveJewelryTypeRequest request) {
        try {
            jewelryTypeService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry type saved successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/toggle-jewelry-type-status")
    public ResponseEntity<CommonResponse> toggleJewelryTypeStatus(@RequestParam Long id) {
        try {
            jewelryTypeService.toggleStatus(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry type status toggled successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all-jewelry-sizes")
    public ResponseEntity<List<JewelrySize>> getAllJewelrySizes() {
        List<JewelrySize> sizes = jewelrySizeService.findAll();
        return ResponseEntity.ok(sizes);
    }

    @PostMapping("/save-jewelry-size")
    public ResponseEntity<CommonResponse> saveJewelrySize(@Valid @RequestBody SaveJewelrySizeRequest request) {
        try {
            jewelrySizeService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry size saved successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/toggle-jewelry-size-status")
    public ResponseEntity<CommonResponse> toggleJewelrySizeStatus(@RequestParam Long id) {
        try {
            jewelrySizeService.toggleStatus(id);
            CommonResponse response = new CommonResponse();
            response.setMessage("Jewelry size status toggled successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
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
