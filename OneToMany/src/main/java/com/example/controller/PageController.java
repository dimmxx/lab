package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Page;
import com.example.repository.NoteRepository;
import com.example.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PageController {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/pages")
    public List<Page> getAllPages(){
        return pageRepository.findAll();
    }

    @GetMapping("/pages/{id}")
    public Page getPageById(@PathVariable(value = "id") Long pageId){
        return pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page", "id", pageId));
    }















}
