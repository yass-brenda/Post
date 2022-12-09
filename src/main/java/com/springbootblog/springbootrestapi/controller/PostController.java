package com.springbootblog.springbootrestapi.controller;

import com.springbootblog.springbootrestapi.dtos.PostDto;
import com.springbootblog.springbootrestapi.dtos.PostResponse;
import com.springbootblog.springbootrestapi.service.PostService;
import com.springbootblog.springbootrestapi.utils.AppConstants;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // CREATE A BLOG
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto newPostDto){
        return  new ResponseEntity<>(postService.createPosts(newPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value= "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable(name ="id") Long id){
        return ResponseEntity.ok(postService.getPostsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePosts(@Valid @PathVariable(name ="id") Long id, @RequestBody PostDto newPost){
        PostDto postResponse = postService.updatePosts(newPost,id);
        return  new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosts(@PathVariable(name ="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully" , HttpStatus.OK);
    }


}
