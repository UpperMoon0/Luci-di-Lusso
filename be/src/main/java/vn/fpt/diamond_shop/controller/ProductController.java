package vn.fpt.diamond_shop.controller;

import org.apache.tomcat.jni.Local;
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
import java.time.LocalDateTime;
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
                             IDiamondShapeRepository diamondShapeRepository,
                             ) {
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

        JewelriesResponse response = new JewelriesResponse(jewelries, jewelryService);
        response.setMessage("Get all jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-jewelries")
    public ResponseEntity<JewelriesResponse> getJewelries(@RequestBody @Valid ListJewelriesRequest request) {
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

        CommonResponse response = new CommonResponse();
        response.setMessage("Jewelry added successfully");
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

    @PostMapping("/update-diamond")
    public ResponseEntity<CommonResponse> updateDiamond(@RequestBody DiamondUpdateRequest body) {
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

    @PostMapping("/add-diamond")
    public ResponseEntity<CommonResponse> addDiamond() {
        Diamond diamond = new Diamond();
        diamond.setCut(diamondCutRepository.findByCut(EDiamondCut.EXCELLENT));
        diamond.setColor(diamondColorRepository.findByColor(EDiamondColor.D));
        diamond.setClarity(diamondClarityRepository.findByClarity(EDiamondClarity.IF));
        diamond.setCarat(1.0f);
        diamond.setShape(diamondShapeRepository.findByShape(EDiamondShape.ROUND));
        diamond.setCreateAt(LocalDateTime.now());
        diamond.setQuantity(100);

        diamondService.saveDiamond(diamond);

        CommonResponse response = new CommonResponse();
        response.setMessage("Diamond added successfully");
        return ResponseEntity.ok(response);
    }
}
