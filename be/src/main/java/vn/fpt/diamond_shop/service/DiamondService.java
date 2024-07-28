package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.SaveDiamondRequest;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

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
    public void toggleStatus(long id) {
        Diamond diamond = diamondRepository.findById(id).orElse(null);

        if (diamond == null) {
            throw new RuntimeException("Diamond not found");
        }

        if (diamond.getStatus().equals("ACTIVE")) {
            diamond.setStatus("INACTIVE");
        } else {
            diamond.setStatus("ACTIVE");
        }

        diamondRepository.save(diamond);
    }

    @Override
    public Diamond findById(long id) {
        return diamondRepository.findById(id).orElse(null);
    }

    @Override
    public List<Diamond> getAllDiamonds() {
        return diamondRepository.findAll();
    }

    @Override
    public void save(SaveDiamondRequest request) {
        Diamond diamond;
        if (request.getId() == 0) {
            diamond = new Diamond();
        } else {
            diamond = findById(request.getId());
            if (diamond == null) {
                throw new RuntimeException("Diamond not found");
            }
        }

        DiamondCut cut = diamondCutRepository.findById(request.getCutId()).orElse(null);
        DiamondColor color = diamondColorRepository.findById(request.getColorId()).orElse(null);
        DiamondClarity clarity = diamondClarityRepository.findById(request.getClarityId()).orElse(null);
        DiamondShape shape = diamondShapeRepository.findById(request.getShapeId()).orElse(null);

        if (cut == null || color == null || clarity == null || shape == null) {
            throw new RuntimeException("Invalid diamond update request");
        }

        diamond.setCut(cut);
        diamond.setColor(color);
        diamond.setClarity(clarity);
        diamond.setCarat(request.getCarat());
        diamond.setShape(shape);
        diamond.setQuantity(request.getQuantity());
        diamondRepository.save(diamond);
    }
}
