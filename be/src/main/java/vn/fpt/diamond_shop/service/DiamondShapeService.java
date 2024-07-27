package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.DiamondShapeRequest;
import vn.fpt.diamond_shop.model.entity.DiamondShape;
import vn.fpt.diamond_shop.repository.IDiamondShapeRepository;

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
    public void save(DiamondShapeRequest request) {
        long id = request.getId();

        DiamondShape diamondShape;
        if (id == 0) {
            diamondShape = new DiamondShape();
        } else {
            diamondShape = diamondShapeRepository.findById(id).orElse(null);
            if (diamondShape == null) {
                throw new RuntimeException("Diamond Shape not found");
            }
        }
        diamondShape.setShape(request.getShape());
        diamondShape.setPriceMultiplier(request.getPriceMultiplier());
        diamondShapeRepository.save(diamondShape);
    }

    @Override
    public void delete(long id) {
        DiamondShape diamondShape = diamondShapeRepository.findById(id).orElse(null);
        if (diamondShape == null) {
            throw new RuntimeException("Diamond Shape not found");
        }
        diamondShape.setStatus("DISABLED");
        diamondShapeRepository.save(diamondShape);
    }
}
