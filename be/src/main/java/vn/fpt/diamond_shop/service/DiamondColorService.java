package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.DiamondColorRequest;
import vn.fpt.diamond_shop.model.entity.DiamondColor;
import vn.fpt.diamond_shop.repository.IDiamondColorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiamondColorService implements IDiamondColorService {
    private final IDiamondColorRepository diamondColorRepository;

    @Override
    public List<DiamondColor> findAll() {
        return diamondColorRepository.findAll();
    }

    @Override
    public void save(DiamondColorRequest request) {
        long id = request.getId();

        DiamondColor diamondColor;
        if (id == 0) {
            diamondColor = new DiamondColor();
        } else {
            diamondColor = diamondColorRepository.findById(id).orElse(null);
            if (diamondColor == null) {
                throw new RuntimeException("Diamond Color not found");
            }
        }
        diamondColor.setColor(request.getColor());
        diamondColor.setPrice(request.getPrice());
        diamondColorRepository.save(diamondColor);
    }

    @Override
    public void toggleStatus(long id) {
        DiamondColor diamondColor = diamondColorRepository.findById(id).orElse(null);
        if (diamondColor == null) {
            throw new RuntimeException("Diamond Color not found");
        }

        if (diamondColor.getStatus().equals("ACTIVE")) {
            diamondColor.setStatus("INACTIVE");
        } else {
            diamondColor.setStatus("ACTIVE");
        }

        diamondColorRepository.save(diamondColor);
    }
}
