package com.springbootblog.springbootrestapi.service.impl;

import com.springbootblog.springbootrestapi.dtos.CommentDto;
import com.springbootblog.springbootrestapi.dtos.PostDto;
import com.springbootblog.springbootrestapi.entity.Comment;
import com.springbootblog.springbootrestapi.entity.Post;
import com.springbootblog.springbootrestapi.exceptions.ResourceNotFoundException;
import com.springbootblog.springbootrestapi.repository.CommentRepository;
import com.springbootblog.springbootrestapi.repository.PostRepository;
import com.springbootblog.springbootrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto save(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return commentToDTo(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
       List<Comment>comments = commentRepository.findByPostId(postId);
       return comments.stream().map(comment -> commentToDTo(comment)).collect(Collectors.toList());
    }




    private CommentDto commentToDTo(Comment comment){
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return comment;
    }
}
