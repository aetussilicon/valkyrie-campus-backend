package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.dtos.NewPostDto;
import br.com.valkyrie.campus.model.dtos.PostsDto;
import br.com.valkyrie.campus.model.dtos.answer.UpvoteDownvoteDto;
import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.services.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService service;

    @PostMapping("publish")
    public ResponseEntity<Posts> publishNewPost(@RequestBody @Valid NewPostDto dto) {
        return new ResponseEntity<>(service.publishNewPost(dto), HttpStatus.CREATED);
    }

    @GetMapping("list/{postId}")
    public ResponseEntity<PostsDto> listPost(@PathVariable UUID postId) {
        return new ResponseEntity<>(service.listPost(postId), HttpStatus.OK);
    }

    @GetMapping("list/all")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity<List<PostsDto>> listAllPosts() {
        return new ResponseEntity<>(service.listPosts(), HttpStatus.OK);
    }

    @PatchMapping("update/upvote-downvote")
    public ResponseEntity<Posts> updateUpvoteDownvote(@RequestBody @Valid UpvoteDownvoteDto dto) {
        service.updateUpvoteDownvote(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
