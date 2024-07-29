package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveDiamondShapeRequest;
import vn.fpt.diamond_shop.model.entity.DiamondShape;

import java.util.List;

public interface IDiamondShapeService {
    List<DiamondShape> findAll();

    void save(SaveDiamondShapeRequest request);

    void toggleStatus(long id);
}
