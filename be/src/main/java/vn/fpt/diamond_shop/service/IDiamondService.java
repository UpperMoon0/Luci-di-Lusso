package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.List;

public interface IDiamondService
{
    void saveDiamond(Diamond diamond);
    void deleteDiamond(Integer id);
    Diamond getDiamondById(Long id);
    List<Diamond> getAllDiamonds();
}
