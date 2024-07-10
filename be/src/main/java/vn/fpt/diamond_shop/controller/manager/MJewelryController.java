package vn.fpt.diamond_shop.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.JewelryRequest;
import vn.fpt.diamond_shop.service.manager.jewelry.MJewelryService;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class MJewelryController {
    private final MJewelryService jewelryService;

    @PostMapping("/jewelry")
    public ResponseEntity<JewelryRequest> addJewelry(@ModelAttribute JewelryRequest jewelryRequest) throws Exception {
        JewelryRequest jewelryRequest1 = jewelryService.addJewelry(jewelryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jewelryRequest1);
    }

    @GetMapping("/jewelries")
    public ResponseEntity<List<JewelryRequest>> getAllJewelries() {
        List<JewelryRequest> jewelryRequests = jewelryService.getAllJewelries();
        return ResponseEntity.ok(jewelryRequests);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<JewelryRequest>> getAllJewelriesByName(@PathVariable String name) {
        List<JewelryRequest> jewelryRequests = jewelryService.getAllJewelriesByName(name);
        return ResponseEntity.ok(jewelryRequests);
    }

    @GetMapping("/jewelry/types")
    public ResponseEntity<List<JewelryRequest>> getAllJewelryTypes() {
        List<JewelryRequest> jewelryTypes = jewelryService.getAllJewelryTypes();
        return ResponseEntity.ok(jewelryTypes);
    }

    @DeleteMapping("/jewelry/{jewelryId}")
    public ResponseEntity<Void> deleteJewelry(@PathVariable Long jewelryId) {
        boolean deleted = jewelryService.deleteJewelry(jewelryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
