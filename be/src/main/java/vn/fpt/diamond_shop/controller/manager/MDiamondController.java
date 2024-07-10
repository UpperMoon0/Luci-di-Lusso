package vn.fpt.diamond_shop.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.DiamondRequest;
import vn.fpt.diamond_shop.service.manager.diamond.MDiamondService;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class MDiamondController {
    private final MDiamondService diamondService;

    @PostMapping("/diamond")
    public ResponseEntity<DiamondRequest> addDiamond(@ModelAttribute DiamondRequest diamondRequest) throws Exception {
        DiamondRequest diamondRequest1 = diamondService.addDiamond(diamondRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(diamondRequest1);
    }

    @GetMapping("/diamonds")
    public ResponseEntity<List<DiamondRequest>> getAllDiamonds() {
        List<DiamondRequest> diamondRequests = diamondService.getAllDiamonds();
        return ResponseEntity.ok(diamondRequests);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<DiamondRequest>> getAllDiamondsById(@PathVariable Long id) {
        List<DiamondRequest> diamondRequests = diamondService.getAllDiamondsById(id);
        return ResponseEntity.ok(diamondRequests);
    }

    @DeleteMapping("/diamond/{diamondId}")
    public ResponseEntity<Void> deleteDiamond(@PathVariable Long diamondId) {
        boolean deleted = diamondService.deleteDiamond(diamondId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
