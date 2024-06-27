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
            String temp = "";
            String tagString = "";
            if (e.toString().contains("_")) {
                temp = e.toString().split("_")[0] + " " + e.toString().split("_")[1];
                System.out.println(temp);
            } else {
                temp = e.toString();
            }
            tagString = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
            tags.add(tagString);
        }
        return ok(tags);
    }
}
