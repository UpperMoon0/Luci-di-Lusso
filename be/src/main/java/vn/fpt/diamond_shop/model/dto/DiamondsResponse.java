package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiamondsResponse extends CommonResponse {
    private List<DiamondDTO> diamonds = new ArrayList<>();

    public DiamondsResponse(List<Diamond> diamonds) {
        diamonds.forEach(diamond -> this.diamonds.add(new DiamondDTO(
                diamond.getId(),
                diamond.getCut().getCut().getValue(),
                diamond.getColor().getColor().name(),
                diamond.getClarity().getClarity().name(),
                diamond.getCarat(),
                diamond.getShape().getShape().getValue()
        )));
    }
}

record DiamondDTO(
    Long id,
    String cut,
    String color,
    String clarity,
    Float carat,
    String shape
) { }
