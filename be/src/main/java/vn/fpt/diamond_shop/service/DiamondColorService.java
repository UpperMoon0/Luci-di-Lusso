package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
