package com.springbootblog.springbootrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {


    private Long id;

    @NotEmpty(message = "El titulo del comentario no puedes ser nulo o vacio")
    private String title;

    @NotEmpty(message = "El titulo del email no puedes ser nulo o vacio")
    @Email
    private String email;

    @NotEmpty( message = "El body del comentario no puede ser vacio o nulo")
    @Size(min = 10 , message = "EL vody debe ser de minimo 10 caracteres")
    private String body;

}
