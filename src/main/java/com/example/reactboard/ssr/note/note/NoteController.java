package com.example.reactboard.ssr.note.note;

import com.example.reactboard.domain.article.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public String add() {
        noteService.saveDefault();
        return "redirect:/note/action";
    }
    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/action")
    public String action(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "note";
    }
}
