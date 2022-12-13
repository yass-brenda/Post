package com.springbootblog.springbootrestapi.service;

import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto save(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentByPostId(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updatedComment(Long postId,Long commentId, CommentDto commentDto);
    void deleteCommentById(Long postId,Long commentId);

}
