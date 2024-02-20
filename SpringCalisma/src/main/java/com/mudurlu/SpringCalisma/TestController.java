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
    public String getUserID(@PathVariable("userid")int user_id){
        return "User ID: " +user_id;
    }

    @GetMapping("/user/{userid}/{username}")
    public String getUserName(@PathVariable("username")String user_name){
        return "Merhaba " +user_name;
    }

    @GetMapping("/user/{userid}/{username}/{usersurname}")
    public String getUserNameSurname(@PathVariable("username")String user_name,@PathVariable("usersurname")String user_surname){
        return "Hoşgeldiniz " + user_name + " " + user_surname;
    }
}
