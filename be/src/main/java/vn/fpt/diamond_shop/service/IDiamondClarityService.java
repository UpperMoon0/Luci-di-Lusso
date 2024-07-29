package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveDiamondClarityRequest;
import vn.fpt.diamond_shop.model.entity.DiamondClarity;

import java.util.List;

public interface IDiamondClarityService {
    List<DiamondClarity> findAll();

    void save(SaveDiamondClarityRequest request);

    void toggleStatus(long id);
}
