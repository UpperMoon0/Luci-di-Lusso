package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EDiamondClarity;
import vn.fpt.diamond_shop.constant.EDiamondColor;
import vn.fpt.diamond_shop.constant.EDiamondCut;
import vn.fpt.diamond_shop.constant.EDiamondShape;
import vn.fpt.diamond_shop.model.dto.DiamondUpdateRequest;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiamondService implements IDiamondService {
    private final IDiamondRepository diamondRepository;
    private final IDiamondCutRepository diamondCutRepository;
    private final IDiamondColorRepository diamondColorRepository;
    private final IDiamondClarityRepository diamondClarityRepository;
    private final IDiamondShapeRepository diamondShapeRepository;

    @Autowired
    public DiamondService(IDiamondRepository diamondRepository,
                          IDiamondCutRepository diamondCutRepository,
                          IDiamondColorRepository diamondColorRepository,
                          IDiamondClarityRepository diamondClarityRepository,
                          IDiamondShapeRepository diamondShapeRepository) {
        this.diamondRepository = diamondRepository;
        this.diamondCutRepository = diamondCutRepository;
        this.diamondColorRepository = diamondColorRepository;
        this.diamondClarityRepository = diamondClarityRepository;
        this.diamondShapeRepository = diamondShapeRepository;
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

    @Override
    public Diamond findFirst() {
        return diamondRepository.findFirstBy().orElse(null);
    }

    @Override
    public void updateDiamond(DiamondUpdateRequest request) {
        DiamondCut cut = diamondCutRepository.findById(request.getCutId()).orElse(null);
        DiamondColor color = diamondColorRepository.findById(request.getColorId()).orElse(null);
        DiamondClarity clarity = diamondClarityRepository.findById(request.getClarityId()).orElse(null);
        Float carat = request.getCarat();
        DiamondShape shape = diamondShapeRepository.findById(request.getShapeId()).orElse(null);
        Diamond diamond = getDiamondById(request.getId());

        if (cut == null || color == null || clarity == null || shape == null || diamond == null) {
            throw new IllegalArgumentException("Invalid diamond update request");
        }

        diamond.setCut(cut);
        diamond.setColor(color);
        diamond.setClarity(clarity);
        diamond.setCarat(carat);
        diamond.setShape(shape);
        diamondRepository.save(diamond);
    }

    @Override
    public void createNewDiamond() {
        Diamond diamond = new Diamond();
        DiamondCut cut = diamondCutRepository.findByCut(EDiamondCut.EXCELLENT).orElse(null);
        DiamondColor color = diamondColorRepository.findByColor(EDiamondColor.D).orElse(null);
        DiamondClarity clarity = diamondClarityRepository.findByClarity(EDiamondClarity.IF).orElse(null);
        DiamondShape shape = diamondShapeRepository.findByShape(EDiamondShape.ROUND).orElse(null);

        if (cut == null || color == null || clarity == null || shape == null) {
            throw new RuntimeException("Failed to create new diamond");
        }

        diamond.setCut(cut);
        diamond.setColor(color);
        diamond.setClarity(clarity);
        diamond.setShape(shape);
        diamond.setCarat(1.0f);
        diamond.setCreateAt(LocalDateTime.now());
        diamond.setQuantity(100);

        diamondRepository.save(diamond);
    }
}
