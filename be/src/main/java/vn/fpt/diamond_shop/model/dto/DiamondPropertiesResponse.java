package vn.fpt.diamond_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.DiamondClarity;
import vn.fpt.diamond_shop.model.entity.DiamondColor;
import vn.fpt.diamond_shop.model.entity.DiamondCut;
import vn.fpt.diamond_shop.model.entity.DiamondShape;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class DiamondPropertiesResponse extends CommonResponse {
    private List<DiamondCut> cuts;
    private List<DiamondClarity> clarities;
    private List<DiamondColor> colors;
    private List<DiamondShape> shapes;
}
