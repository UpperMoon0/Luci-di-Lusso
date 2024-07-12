package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.Diamond;
import vn.fpt.diamond_shop.repository.IDiamondRepository;

import java.util.List;

@Service
public class DiamondService implements IDiamondService {
    private final IDiamondRepository diamondRepository;

    @Autowired
    public DiamondService(IDiamondRepository diamondRepository) {
        this.diamondRepository = diamondRepository;
    }

    @Override
    public void saveDiamond(Diamond diamond) {
        diamondRepository.save(diamond);
    }

    @Override
    public void deleteDiamondById(Integer id) {
        diamondRepository.deleteById(id);
    }

    @Override
    public Diamond getDiamondById(Long id) {
        return diamondRepository.findById(id).orElse(null);
    }

    @Override
    public List<Diamond> getAllDiamonds() {
        return diamondRepository.findAll();
    }
}
