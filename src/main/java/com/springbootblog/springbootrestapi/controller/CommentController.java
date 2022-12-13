package com.springbootblog.springbootrestapi.controller;
import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/posts/{PostId}/comments")
    public ResponseEntity<CommentDto> guardarComentario(@PathVariable(name = "PostId") Long postId,@Valid @RequestBody CommentDto commentDto){
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
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updatedComment(@PathVariable(name = "postId") Long postId,@PathVariable(name = "id") Long commentId,@Valid @RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updatedComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Long postId,@PathVariable(name = "id") Long commentId){
        commentService.deleteCommentById(postId,commentId);
        return new ResponseEntity<>("Comment deleted sucessfully", HttpStatus.OK);
    }

}
