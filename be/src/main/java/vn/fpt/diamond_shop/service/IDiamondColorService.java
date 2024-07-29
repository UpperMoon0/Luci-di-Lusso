package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveDiamondColorRequest;
import vn.fpt.diamond_shop.model.entity.DiamondColor;

import java.util.List;

public interface IDiamondColorService {
    List<DiamondColor> findAll();

    void save(SaveDiamondColorRequest request);

    void toggleStatus(long id);
}
