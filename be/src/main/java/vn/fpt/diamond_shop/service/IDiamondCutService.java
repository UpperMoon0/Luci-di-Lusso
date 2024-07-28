package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveDiamondCutRequest;
import vn.fpt.diamond_shop.model.entity.DiamondCut;

import java.util.List;

public interface IDiamondCutService {
    List<DiamondCut> findAll();

    void save(SaveDiamondCutRequest request);

    void toggleStatus(long id);
}
