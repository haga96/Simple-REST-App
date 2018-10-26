package warsztaty.spring.ailleron.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.model.UserList;
import warsztaty.spring.ailleron.services.UserService;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService service;

//    @GetMapping(value = "/users/{name}", headers = "X-API-VERSION=1")
//    public User getUserByName(@PathVariable("name") String name) throws UserNotFoundException {
//        Optional<User> user = service.getUserByName(name);
//        return user.orElseThrow(()->new UserNotFoundException("User not found"));
//    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
//        Optional<User> user = service.getUserByName(id);
//        return user.orElseThrow(()->new UserNotFoundException("User not found"));
        return service.getUserById(id).get();
    }

    @GetMapping("/users/")
    public UserList getAllUsers(){
        return service.getAll();
    }

    @PostMapping("/users/")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        Long id = service.addUser(user);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody @Valid User user, @PathVariable Long id)
        throws  UserNotFoundException{
        return service.updateUser(user,id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws UserNotFoundException {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
