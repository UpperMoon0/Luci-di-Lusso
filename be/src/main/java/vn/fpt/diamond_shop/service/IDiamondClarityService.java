package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondClarityRequest;
import vn.fpt.diamond_shop.model.entity.DiamondClarity;

import java.util.List;

public interface IDiamondClarityService {
    List<DiamondClarity> findAll();

    void save(DiamondClarityRequest request);

    void toggleStatus(long id);
}
