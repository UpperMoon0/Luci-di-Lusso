package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.*;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;
import vn.fpt.diamond_shop.service.IDiamondService;
import vn.fpt.diamond_shop.service.IJewelryService;
import vn.fpt.diamond_shop.service.IJewelrySizeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final IJewelryService jewelryService;
    private final IJewelrySizeService jewelrySizeService;
    private final IDiamondService diamondService;
    private final IDiamondCutRepository diamondCutRepository;
    private final IDiamondColorRepository diamondColorRepository;
    private final IDiamondClarityRepository diamondClarityRepository;
    private final IDiamondShapeRepository diamondShapeRepository;

    @Autowired
    public ProductController(IJewelryService jewelryService,
                             IJewelrySizeService jewelrySizeService,
                             IDiamondService diamondService,
                             IDiamondCutRepository diamondCutRepository,
                             IDiamondColorRepository diamondColorRepository,
                             IDiamondClarityRepository diamondClarityRepository,
                             IDiamondShapeRepository diamondShapeRepository) {
        this.jewelryService = jewelryService;
        this.jewelrySizeService = jewelrySizeService;
        this.diamondService = diamondService;
        this.diamondCutRepository = diamondCutRepository;
        this.diamondColorRepository = diamondColorRepository;
        this.diamondClarityRepository = diamondClarityRepository;
        this.diamondShapeRepository = diamondShapeRepository;
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

        JewelriesResponse response = new JewelriesResponse();
        for (Jewelry jewelry : jewelries) {
            int price = jewelryService.calculateJewelryPrice(jewelry);
            response.addJewelry(jewelry, price);
        }
        response.setMessage("Get all jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-jewelries")
    public ResponseEntity<JewelriesResponse> getJewelries(@RequestBody JewelriesRequest request) {
        List<EJewelryType> types = request.getTypes();
        Integer minPrice = request.getMinPrice();
        Integer maxPrice = request.getMaxPrice();
        String keyword = request.getKeyword();

        List<Jewelry> filteredJewelries = jewelryService.getJewelriesByFilter(types, minPrice, maxPrice, keyword);

        JewelriesResponse response = new JewelriesResponse();
        for (Jewelry jewelry : filteredJewelries) {
            int price = jewelryService.calculateJewelryPrice(jewelry);
            response.addJewelry(jewelry, price);
        }
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-jewelry-types")
    public ResponseEntity<Object> getAllTypes() {
        List<String> types = new ArrayList<>();
        for (EJewelryType e : EJewelryType.values()) {
            types.add(e.toString().substring(0,1).toUpperCase() + e.toString().substring(1).toLowerCase());
        }
        return ResponseEntity.ok(types);
    }

    @GetMapping("/get-all-diamonds")
    public ResponseEntity<DiamondsResponse> getAllDiamonds() {
        List<Diamond> diamonds = diamondService.getAllDiamonds();
        DiamondsResponse response = new DiamondsResponse(diamonds);
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

    @PostMapping("/save-diamond")
    public ResponseEntity<CommonResponse> saveDiamond(@RequestBody DiamondUpdateRequest body) {
        DiamondCut cut = diamondCutRepository.findByCut(EDiamondCut.fromValue(body.getCut()));
        DiamondColor color = diamondColorRepository.findByColor(EDiamondColor.fromValue(body.getColor()));
        DiamondClarity clarity = diamondClarityRepository.findByClarity(EDiamondClarity.fromValue(body.getClarity()));
        Float carat = body.getCarat();
        DiamondShape shape = diamondShapeRepository.findByShape(EDiamondShape.fromValue(body.getShape()));
        Diamond diamond = diamondService.getDiamondById(body.getId());
        CommonResponse response = new CommonResponse();

        if (cut == null || color == null || clarity == null || shape == null || diamond == null) {
            response.setMessage("Invalid diamond data");
            return ResponseEntity.badRequest().body(response);
        }

        diamond.setCut(cut);
        diamond.setColor(color);
        diamond.setClarity(clarity);
        diamond.setCarat(carat);
        diamond.setShape(shape);
        diamondService.saveDiamond(diamond);

        response.setMessage("Diamond saved successfully");
        return ResponseEntity.ok(response);
    }
}
