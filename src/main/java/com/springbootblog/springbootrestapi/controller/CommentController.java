package com.springbootblog.springbootrestapi.controller;
import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/posts/{PostId}/comments")
    public ResponseEntity<CommentDto> guardarComentario(@Valid @PathVariable(name = "PostId") Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.save(postId,commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto>getCommentByPostId(@PathVariable(value = "postId")Long postId){
       return commentService.getCommentByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId")Long postId,@PathVariable(value = "id")Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    //Incompleto
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updatedComment(@Valid @PathVariable(name = "postId") Long postId,@PathVariable(name = "id") Long commentId, @RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updatedComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
}
