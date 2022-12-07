package com.springbootblog.springbootrestapi.service.impl;

import com.springbootblog.springbootrestapi.dtos.PostDto;
import com.springbootblog.springbootrestapi.dtos.PostResponse;
import com.springbootblog.springbootrestapi.entity.Post;
import com.springbootblog.springbootrestapi.exceptions.ResourceNotFoundException;
import com.springbootblog.springbootrestapi.repository.PostRepository;
import com.springbootblog.springbootrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    // dto a entidad y viceversa
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public PostDto createPosts(PostDto newPostDto) {
        // Convertir dto a entidad
        Post post = mapToEntity(newPostDto);
        Post newPost = postRepository.save(post);
        // convertir entity to dto
        PostDto postResponse = mapToDTo(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort); //Sort.by(sortBy).descending()
        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post>listOfPots = posts.getContent();

        List<PostDto>content =  listOfPots.stream().map(post -> mapToDTo(post)).collect(Collectors.toList());



        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostsById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));
        return  mapToDTo(post);
    }

    @Transactional
    @Override
    public PostDto updatePosts(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));
        post.setTittle(postDto.getTittle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post updatePots = postRepository.save(post);
        return mapToDTo(updatePots);
    }
    @Transactional
    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));
        postRepository.delete(post);
    }

    // Convert Entity to DTO
    private PostDto mapToDTo(Post post){
        //PostDto postDto = new PostDto();
        PostDto postDto = modelMapper.map(post,PostDto.class);
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        //PostDto postDto = modelMapper.map(post,PostDto.class);
        /*postDto.setId(post.getId());
        postDto.setTittle(post.getTittle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());*/
        return postDto;
    }

    // Convert DTO a entity
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        //Post post = modelMapper.map(postDto,Post.class);
        //Post post = this.modelMapper.map(postDto.getClass(),Post.class);
        /*post.setTittle(postDto.getTittle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/
        return  post;
    }
}
