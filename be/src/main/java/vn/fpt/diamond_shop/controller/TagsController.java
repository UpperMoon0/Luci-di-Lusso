package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fpt.diamond_shop.constant.EJewelryTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/tags")
@RestController
public class TagsController implements ITagsController {

    @Override
    @GetMapping("/get-all-tags")
    public ResponseEntity<Object> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (EJewelryTag e : EJewelryTag.values()) {
            tags.add(e.toString());
        }
        return ok(tags);
    }
}
