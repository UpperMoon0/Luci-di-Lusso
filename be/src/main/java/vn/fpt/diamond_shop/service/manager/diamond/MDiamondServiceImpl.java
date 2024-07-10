package vn.fpt.diamond_shop.service.manager.diamond;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.DiamondRequest;
import vn.fpt.diamond_shop.model.entity.Diamond;
import vn.fpt.diamond_shop.repository.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MDiamondServiceImpl implements MDiamondService {
    private final IDiamondClarityRepository diamondClarityRepository;
    private final IDiamondColorRepository diamondColorRepository;
    private final IDiamondCutRepository diamondCutRepository;
    private final IDiamondShapeRepository diamondShapeRepository;
    private final IDiamondRepository diamondRepository;


    public DiamondRequest addDiamond(DiamondRequest diamondRequest) throws IOException {
        Diamond diamond = new Diamond();
        diamond.setQuantity(diamondRequest.getQuantity());

        // Set clarity
        diamond.setClarity(diamondClarityRepository.findById(diamondRequest.getClarityId())
                .orElseThrow(() -> new RuntimeException("Clarity not found")));

        // Set color
        diamond.setColor(diamondColorRepository.findById(diamondRequest.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found")));

        // Set cut
        diamond.setCut(diamondCutRepository.findById(diamondRequest.getCutId())
                .orElseThrow(() -> new RuntimeException("Cut not found")));

        // Set shape
        diamond.setShape(diamondShapeRepository.findById(diamondRequest.getShapeId())
                .orElseThrow(() -> new RuntimeException("Shape not found")));

        diamond.setCarat(diamondRequest.getCarat());

        return diamondRepository.save(diamond).getDiamondRequest();
    }

    public List<DiamondRequest> getAllDiamonds() {
        List<Diamond> diamonds = diamondRepository.findAll();
        return diamonds.stream().map(Diamond::getDiamondRequest).toList();
    }

    public List<DiamondRequest> getAllDiamondsById(Long id) {
        List<Diamond> diamonds = diamondRepository.findAllById(id);
        return diamonds.stream().map(Diamond::getDiamondRequest).toList();
    }

    public boolean deleteDiamond(Long id) {
        Optional<Diamond> optionalDiamond = diamondRepository.findById(id);
        if(optionalDiamond.isPresent()) {
            diamondRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
