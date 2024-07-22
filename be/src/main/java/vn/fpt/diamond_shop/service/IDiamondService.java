package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.DiamondUpdateRequest;
import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.List;

public interface IDiamondService
{
    void saveDiamond(Diamond diamond);
    void deleteDiamondById(long id);
    Diamond getDiamondById(long id);
    List<Diamond> getAllDiamonds();
    void updateDiamond(DiamondUpdateRequest request);
    void createNewDiamond();
    Diamond findFirst();
}
