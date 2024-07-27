package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.DiamondCutRequest;
import vn.fpt.diamond_shop.model.entity.DiamondCut;
import vn.fpt.diamond_shop.repository.IDiamondCutRepository;

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
    public void save(DiamondCutRequest request) {
        long id = request.getId();

        DiamondCut diamondCut;
        if (id == 0) {
            diamondCut = new DiamondCut();
        } else {
            diamondCut = diamondCutRepository.findById(id).orElse(null);
            if (diamondCut == null) {
                throw new RuntimeException("Diamond Cut not found");
            }
        }
        diamondCut.setCut(request.getCut());
        diamondCut.setPrice(request.getPrice());
        diamondCutRepository.save(diamondCut);
    }

    @Override
    public void delete(long id) {
        DiamondCut diamondCut = diamondCutRepository.findById(id).orElse(null);
        if (diamondCut == null) {
            throw new RuntimeException("Diamond Cut not found");
        }
        diamondCut.setStatus("DISABLED");
        diamondCutRepository.save(diamondCut);
    }
}
