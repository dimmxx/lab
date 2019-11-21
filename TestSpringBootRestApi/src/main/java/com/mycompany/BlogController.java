package com.mycompany;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    private BlogMockedData blogMockedData = BlogMockedData.getInstance();


    @RequestMapping("/")
    public String indexStart() {
        return "Congratulations from BlogController.java";
    }


    @GetMapping("/blog")
    public List<Blog> index() {
        return blogMockedData.fetchBlogs();
    }


}
