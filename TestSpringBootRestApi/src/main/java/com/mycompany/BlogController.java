package com.mycompany;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/blog/{id}")
    public Blog blogByIdPathVariable(@PathVariable String id){
        return blogMockedData.getBlogById(Integer.parseInt(id));
    }

    @GetMapping("/blogs/id")
    public Blog blogByIdRequestParams(@RequestParam String id){
        return blogMockedData.getBlogById(Integer.parseInt(id));
    }

    @PostMapping("/blog")
    public Blog createBlog(@RequestBody Map<String, String> requestBody){
        int id = Integer.parseInt(requestBody.get("id"));
        String title = requestBody.get("title");
        String content = requestBody.get("content");
        return blogMockedData.createBlog(id, title, content);
    }

    @PostMapping("/blog/search")
    public List<Blog> searchBlog(@RequestBody Map<String, String> requestBody){
        return blogMockedData.searchBlog(requestBody.get("text"));
    }

    @PutMapping("/blog/{id}")
    public Blog updateBlog(@PathVariable String id, @RequestBody Map<String, String> requestBody){
        return blogMockedData.updateBlog(Integer.parseInt(id), new Blog(Integer.parseInt(id), requestBody.get("title"), requestBody.get("content")));
    }

    @DeleteMapping("/blog/{id}")
    public boolean deleteBlog(@PathVariable String id){
        return blogMockedData.deleteBlog(Integer.parseInt(id));
    }



}
