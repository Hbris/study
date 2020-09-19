package com.jiang.advances.peace.controller;

import com.jiang.advances.peace.entity.TranslateResult;
import com.jiang.advances.peace.util.Translate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MergeController {
    @GetMapping("/aa")
    public List<TranslateResult>  merge(){
        Translate.merge();
        return Translate.merge();
    }
}
