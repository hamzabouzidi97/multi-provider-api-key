package com.multiprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiProviderDemoController {

    @GetMapping("/demo")
    public String demo(){
        return "MultiProvider Demo";
    }
}
