package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.dtos.answer.NewAnswerDto;
import br.com.valkyrie.campus.model.dtos.responses.AnswerResponseDto;
import br.com.valkyrie.campus.model.entities.Answer;
import br.com.valkyrie.campus.services.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService service;

    @PostMapping
    @RequestMapping("/new")
    public ResponseEntity<Answer> publishNewAnswer(@RequestBody @Valid NewAnswerDto dto) {
        return ResponseEntity.ok(service.publishNewAnswer(dto));
    }

    @GetMapping("list/{answerId}")
    public ResponseEntity<AnswerResponseDto> getAnswer(@PathVariable UUID answerId) {
        return ResponseEntity.ok(service.getAnswer(answerId));
    }

}
