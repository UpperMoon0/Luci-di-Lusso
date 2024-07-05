package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequest {
    @NotNull
    private Long userId;
    @NotNull
    private List<Long> jewelryIdList;
}
