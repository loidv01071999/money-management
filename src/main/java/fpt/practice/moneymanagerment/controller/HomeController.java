package fpt.practice.moneymanagerment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @GetMapping("/home")
    public String index(){
        return "nhubuoi";
    }
}
