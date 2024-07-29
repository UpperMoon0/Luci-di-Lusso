package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveDiamondRequest;
import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.List;

public interface IDiamondService
{
    void toggleStatus(long id);
    Diamond findById(long id);
    List<Diamond> getAllDiamonds();
    void save(SaveDiamondRequest request);
}
