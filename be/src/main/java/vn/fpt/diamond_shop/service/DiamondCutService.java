package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.SaveDiamondCutRequest;
import vn.fpt.diamond_shop.model.entity.DiamondCut;
import vn.fpt.diamond_shop.repository.IDiamondCutRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiamondCutService implements IDiamondCutService {
    private final IDiamondCutRepository diamondCutRepository;

    @Override
    public List<DiamondCut> findAll() {
        return diamondCutRepository.findAll();
    }

    @Override
    public void save(SaveDiamondCutRequest request) {
        long id = request.getId();

        DiamondCut diamondCut;
        if (id == 0) {
            diamondCut = new DiamondCut();
        } else {
            diamondCut = diamondCutRepository.findById(id).orElse(null);
            if (diamondCut == null) {
                throw new RuntimeException("Diamond cut not found");
            }
        }
        diamondCut.setCut(request.getCut());
        diamondCut.setPrice(request.getPrice());
        try {
            diamondCutRepository.save(diamondCut);
        } catch (Exception e) {
            throw new RuntimeException("Diamond cut already exists");
        }
    }

    @Override
    public void toggleStatus(long id) {
        DiamondCut diamondCut = diamondCutRepository.findById(id).orElse(null);
        if (diamondCut == null) {
            throw new RuntimeException("Diamond Cut not found");
        }

        if (diamondCut.getStatus().equals("ACTIVE")) {
            diamondCut.setStatus("INACTIVE");
        } else {
            diamondCut.setStatus("ACTIVE");
        }

        diamondCutRepository.save(diamondCut);
    }
}
