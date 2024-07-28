package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondShapeRequest;
import vn.fpt.diamond_shop.model.entity.DiamondShape;

import java.util.List;

public interface IDiamondShapeService {
    List<DiamondShape> findAll();

    void save(DiamondShapeRequest request);

    void toggleStatus(long id);
}
