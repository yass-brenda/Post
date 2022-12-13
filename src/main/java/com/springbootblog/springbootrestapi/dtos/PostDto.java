package com.springbootblog.springbootrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @Nullable
    private Long id;

    @NotEmpty(message = "No puede ser nulo o vacio")
    @Size(min = 2, message = "El titulo debe ser mayor a 2 carcteres")
    private String tittle;

    @NotEmpty(message = "No puede ser nulo o vacio")
    @Size(min = 10, message = "La descripcion debe ser mayor a 10 carcteres")
    private String description;

    @NotEmpty
    private String content;

}
