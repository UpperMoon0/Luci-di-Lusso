package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
