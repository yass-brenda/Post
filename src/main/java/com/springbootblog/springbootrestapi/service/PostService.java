package com.springbootblog.springbootrestapi.service;

import com.springbootblog.springbootrestapi.dtos.PostDto;
import com.springbootblog.springbootrestapi.dtos.PostResponse;
import com.springbootblog.springbootrestapi.entity.Post;

import java.util.List;
public interface PostService {
    PostDto createPosts(PostDto newPostDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostsById(Long id);
    PostDto updatePosts(PostDto pots, Long id);

    void deletePostById(Long id);

}
