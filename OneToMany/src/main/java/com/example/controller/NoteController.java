package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Note;
import com.example.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId){
        return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

//    @GetMapping("/notes/#")
//    public Note getNote(@RequestParam(value = "id") String pid){
//        return noteRepository.findById(Long.parseLong(pid)).orElseThrow(() -> new ResourceNotFoundException("Note", "id", pid));
//    }

    @PutMapping("/notes/{id}")
    public Note updateNoteById(@PathVariable(value = "id") Long id, @RequestBody Note newNote){
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        note.setTitle(newNote.getTitle());
        note.setContent(newNote.getContent());
        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable(value = "id") Long id){
        Note note = noteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note", "id", id));
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }






}
