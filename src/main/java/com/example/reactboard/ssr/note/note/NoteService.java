package com.example.reactboard.ssr.note.note;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note saveDefault() {
        return noteRepository.save(Note.builder().name("제목 없음").createDate(LocalDateTime.now()).build());
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}
