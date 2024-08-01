package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.SaveDiamondShapeRequest;
import vn.fpt.diamond_shop.model.entity.DiamondShape;
import vn.fpt.diamond_shop.repository.IDiamondShapeRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiamondShapeService implements IDiamondShapeService {
    private final IDiamondShapeRepository diamondShapeRepository;

    @Override
    public List<DiamondShape> findAll() {
        return diamondShapeRepository.findAll();
    }

    @Override
    public void save(SaveDiamondShapeRequest request) {
        long id = request.getId();

        DiamondShape diamondShape;
        if (id == 0) {
            diamondShape = new DiamondShape();
        } else {
            diamondShape = diamondShapeRepository.findById(id).orElse(null);
            if (diamondShape == null) {
                throw new RuntimeException("Diamond shape not found");
            }
        }
        diamondShape.setShape(request.getShape());
        diamondShape.setPriceMultiplier(request.getPriceMultiplier());
        try {
            diamondShapeRepository.save(diamondShape);
        } catch (Exception e) {
            throw new RuntimeException("Diamond shape already exists");
        }
    }

    @Override
    public void toggleStatus(long id) {
        DiamondShape diamondShape = diamondShapeRepository.findById(id).orElse(null);
        if (diamondShape == null) {
            throw new RuntimeException("Diamond Shape not found");
        }

        if (diamondShape.getStatus().equals("ACTIVE")) {
            diamondShape.setStatus("INACTIVE");
        } else {
            diamondShape.setStatus("ACTIVE");
        }

        diamondShapeRepository.save(diamondShape);
    }
}
