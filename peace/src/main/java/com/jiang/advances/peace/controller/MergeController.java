package com.jiang.advances.peace.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MergeController {
    @GetMapping("/aa")
    public String merge(){
        return "爱仕达美女世界";
    }
}

