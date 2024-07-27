package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondCutRequest;
import vn.fpt.diamond_shop.model.entity.DiamondCut;

import java.util.List;

public interface IDiamondCutService {
    List<DiamondCut> findAll();

    void save(DiamondCutRequest request);

    void delete(long id);
}
