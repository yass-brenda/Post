package com.springbootblog.springbootrestapi.repository;

import com.springbootblog.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {
    // Lista de metodos para la base de datos
}
