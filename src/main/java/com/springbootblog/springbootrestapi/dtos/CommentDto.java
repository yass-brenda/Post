package com.springbootblog.springbootrestapi.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springbootblog.springbootrestapi.entity.Post;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Post post;
}
