package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class ReceiptResponse extends CommonResponse {
    @NotNull
    private List<Long> jewelryIdList;
}
