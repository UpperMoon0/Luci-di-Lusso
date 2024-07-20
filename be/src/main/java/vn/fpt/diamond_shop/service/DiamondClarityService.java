package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
