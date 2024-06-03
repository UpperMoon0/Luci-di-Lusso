package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class GetCartResponse extends CommonResponse {
    private HashMap<Long, Integer> cart;
}
