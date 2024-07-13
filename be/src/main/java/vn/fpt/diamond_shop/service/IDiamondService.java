package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondUpdateRequest;
import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.List;
import java.util.Optional;

public interface IDiamondService
{
    void saveDiamond(Diamond diamond);
    void deleteDiamondById(Integer id);
    Diamond getDiamondById(Long id);
    List<Diamond> getAllDiamonds();
    void updateDiamond(DiamondUpdateRequest request);
    void createNewDiamond();
    Diamond findFirst();
}
