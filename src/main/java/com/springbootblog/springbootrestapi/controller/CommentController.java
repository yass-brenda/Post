package com.springbootblog.springbootrestapi.controller;


import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createCommet(@PathVariable(value = "postId") Long postId, @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPotsId(@PathVariable Long postId){
        return commentService.getCommentsByPotsId(postId);
    }


    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsByPotsId(@PathVariable(value="postId") Long postId, @PathVariable(value ="id" ) Long commentId){
        CommentDto commentDto = commentService.getCommentBYId(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") Long postId, @PathVariable(value ="id" ) Long commentId,@RequestBody CommentDto newcommentDto){
        CommentDto commentDto = commentService.updateComment(postId,commentId,newcommentDto);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable(value = "postId") Long postId,@PathVariable(value ="id") Long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment delete successful",HttpStatus.OK);
    }
}
