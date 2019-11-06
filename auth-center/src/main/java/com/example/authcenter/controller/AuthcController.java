package com.example.authcenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authc")
public class AuthcController {

    @GetMapping("index")
    public Object index() {
        return "index";
    }

    @GetMapping("admin")
    public Object admin() {
        return "Welcome Admin";
    }

    // delete
    @GetMapping("removable")
    public Object removable() {
        return "removable";
    }

    // insert & update
    @GetMapping("renewable")
    public Object renewable() {
        return "renewable";
    }
}
