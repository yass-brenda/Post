package com.springbootblog.springbootrestapi.service;

import com.springbootblog.springbootrestapi.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDTO);
    List<CommentDto> getCommentsByPotsId(Long postId);
    CommentDto getCommentBYId(Long postId,Long commentId);

    CommentDto updateComment(Long postId,Long commentId, CommentDto commentDto);

    void deleteComment (Long postId , Long commentId);


}
