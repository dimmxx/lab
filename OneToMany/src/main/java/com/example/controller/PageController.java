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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PageController {

    final PageRepository pageRepository;
    final NoteRepository noteRepository;

    public PageController(PageRepository pageRepository, NoteRepository noteRepository) {
        this.pageRepository = pageRepository;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/pages")
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    @GetMapping("/pages/{id}")
    public Page getPageById(@PathVariable(value = "id") Long pageId) {
        return pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page", "id", pageId));
    }

    @GetMapping("/pages/color/{color}")
    public List<Page> getPageByColor(@PathVariable(value = "color") String color) {
        return pageRepository.findByColor(color);
    }




//    @GetMapping("/pages/create")
//    public Page createPage() {
//        Page page = new Page("blue");
//        Note note = new Note("Java title", "Java content", "01-01-2000", "05-05-2005");
//        note.setPage(page);
//        List<Note> notes = new ArrayList<>();
//        notes.add(note);
//        page.setNote(notes);
//        return pageRepository.save(page);
//    }

    @PostMapping("/pages/create")
    public ResponseEntity<Object> createPage(@RequestBody Page page) {
        pageRepository.saveAndFlush(page);
        return new ResponseEntity("OK", HttpStatus.OK);
    }


}
