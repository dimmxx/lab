package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Note;
import com.example.model.Page;
import com.example.repository.NoteRepository;
import com.example.repository.PageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/pages/create")
    public Page createPage(){
        Page page = new Page("blue");
        Note note = new Note("Java title", "Java content", "01-01-2000", "05-05-2005");
        note.setPage(page);
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        page.setNote(notes);
        return pageRepository.save(page);
    }

    @PostMapping("/pages/create")
    public String createPage(@RequestBody String json){

        ObjectMapper mapper = new ObjectMapper();
        try {
            Page page = mapper.readValue(json, Page.class);
            pageRepository.save(page);
            System.out.println(page);
            return "OK";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }





}
