package com.tg.pms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Rubble
 * Created on 2020/3/26
 */
@RestController
public class ProductController {

    @GetMapping("/product")
    public String product() {
        return "product api json";
    }

    @GetMapping("/product/user")
    public Principal getProduct(Principal principal) {
        return principal;
    }
}
