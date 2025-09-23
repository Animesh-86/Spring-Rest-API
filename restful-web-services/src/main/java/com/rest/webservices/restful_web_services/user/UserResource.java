package com.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

//    @GetMapping("/users/{id}")
//    public User retrieveUser(@PathVariable int id){
//        User user = service.findOne(id);
//        if(user == null)
//            throw new UserNotFoundException("id:"+id);
//        return user;
//    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")     //replace the placeholder {id} with the actual id of the saved user
                .buildAndExpand(savedUser.getId())
                .toUri()  ;  // converting into URI
        return ResponseEntity.created(location).build();
    }
}
