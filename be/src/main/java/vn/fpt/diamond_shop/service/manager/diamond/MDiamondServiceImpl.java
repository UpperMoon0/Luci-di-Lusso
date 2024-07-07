package vn.fpt.diamond_shop.service.manager.diamond;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.repository.IDiamondClarityRepository;
import vn.fpt.diamond_shop.repository.IDiamondColorRepository;

@Service
@RequiredArgsConstructor
public class MDiamondServiceImpl implements MDiamondService {
    private final IDiamondClarityRepository diamondClarityRepository;
    private final IDiamondColorRepository diamondColorRepository;
}
