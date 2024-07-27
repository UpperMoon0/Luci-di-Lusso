package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.DiamondClarityRequest;
import vn.fpt.diamond_shop.model.entity.DiamondClarity;
import vn.fpt.diamond_shop.repository.IDiamondClarityRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiamondClarityService implements IDiamondClarityService {
    private final IDiamondClarityRepository diamondClarityRepository;

    @Override
    public List<DiamondClarity> findAll() {
        return diamondClarityRepository.findAll();
    }

    @Override
    public void save(DiamondClarityRequest request) {
        long id = request.getId();

        DiamondClarity diamondClarity;
        if (id == 0) {
            diamondClarity = new DiamondClarity();
        } else {
            diamondClarity = diamondClarityRepository.findById(id).orElse(null);
            if (diamondClarity == null) {
                throw new RuntimeException("Diamond Clarity not found");
            }
        }
        diamondClarity.setClarity(request.getClarity());
        diamondClarity.setPrice(request.getPrice());
        diamondClarityRepository.save(diamondClarity);
    }

    @Override
    public void delete(long id) {
        DiamondClarity diamondClarity = diamondClarityRepository.findById(id).orElse(null);
        if (diamondClarity == null) {
            throw new RuntimeException("Diamond Clarity not found");
        }
        diamondClarity.setStatus("DISABLED");
        diamondClarityRepository.save(diamondClarity);
    }
}
