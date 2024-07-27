package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondColorRequest;
import vn.fpt.diamond_shop.model.entity.DiamondColor;

import java.util.List;

public interface IDiamondColorService {
    List<DiamondColor> findAll();

    void save(DiamondColorRequest request);

    void delete(long id);
}
