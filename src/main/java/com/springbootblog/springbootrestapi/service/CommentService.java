package com.springbootblog.springbootrestapi.service;

import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto save(Long postId, CommentDto commentDto);

}
