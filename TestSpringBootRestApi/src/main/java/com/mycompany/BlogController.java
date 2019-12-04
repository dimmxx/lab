package com.mycompany;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    private BlogMockedData blogMockedData = BlogMockedData.getInstance();

    @RequestMapping("/")
    public String indexStart() {
        return "Congratulations from BlogController.java";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("type") String type) throws IOException {
        File convertFile = new File("src/main/resources/" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<>("File uploaded succesfully", HttpStatus.OK);
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
