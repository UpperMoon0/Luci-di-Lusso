package vn.fpt.diamond_shop.service.manager.diamond;

import vn.fpt.diamond_shop.model.dto.DiamondRequest;

import java.util.List;

public interface MDiamondService{

    DiamondRequest addDiamond(DiamondRequest diamondRequest) throws Exception;

    List<DiamondRequest> getAllDiamonds();

    List<DiamondRequest> getAllDiamondsById(Long id);

    boolean deleteDiamond(Long id);
}
