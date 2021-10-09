package com.home.manage.controller;

import com.home.pio.entity.Bucket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manage")
public class ManageController {

    @GetMapping("/node")
    public String node(){
        return "node/index";
    }

    @GetMapping("/preset")
    public String preset(){
        return "preset/index";
    }

    @GetMapping("/bucket")
    public String bucket(){
        return "bucket/index";
    }

    @GetMapping("/image/{bucket}")
    public String image(@PathVariable Bucket bucket, Model model){
        model.addAttribute("bucket", bucket);
        return "image/index";
    }

}
