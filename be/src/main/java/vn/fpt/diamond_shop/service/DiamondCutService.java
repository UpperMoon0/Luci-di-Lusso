package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
