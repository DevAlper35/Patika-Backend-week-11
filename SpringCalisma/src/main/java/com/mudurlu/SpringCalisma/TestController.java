package com.mudurlu.SpringCalisma;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/")
public class TestController {

    @GetMapping("/")
    public String home(){
        return "Anasayfa";
    }

    @GetMapping("/about")
    public String about(){
        return "Hakkımızda sayfası";
    }

    @GetMapping("/user/{userid}")
    public String getUser(@PathVariable("userid")int user_id){
        return "User ID: " +user_id;
    }
}
