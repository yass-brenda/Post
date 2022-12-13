package com.springbootblog.springbootrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="title", nullable = false )
    @NotEmpty(message = "No debe ser vacio")
    @Size(min= 4, message = "El titulo debe tener màs de 4 caràcteres")
    private String title;

    @Column(name ="email", nullable = false)
    private String email;

    @NotEmpty(message = "No debe ser vacio")
    @Column(name ="body", nullable = false)
    @Size(min= 4, message = "El titulo debe tener màs de 4 caràcteres")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false)
    private Post post;


}
