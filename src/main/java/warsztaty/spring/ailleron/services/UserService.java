package warsztaty.spring.ailleron.services;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warsztaty.spring.ailleron.exceptions.UserExistException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.model.UserList;
import warsztaty.spring.ailleron.repositories.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    private static List<User> users = new ArrayList<>();
    private UserList list;

    @Autowired
    public UserService(UserRepository repository){
        this.repository=repository;
    }


    public Optional<User> getUserByName(String name){
//        return list.getUsers().stream()
//                .filter(u->u.getName().equals(name))
//                .findFirst();
        return repository.getUserByName(name);
    }




    public UserList getAll(){
        return new UserList(repository.findAll());
    }

    public Long addUser(User user){
        Optional<User> optionalUser= getUserByName(user.getName());
        if(optionalUser.isPresent()){
            throw new UserExistException("User" + user.getName() + "already exist");
        }
        user = repository.save(user);
        //list.getUsers();
        return user.getId();


    }

    public User updateUser(User user, Long id) throws UserNotFoundException {
        Optional<User> optionalUser = getUserById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("UserId: " + id + " not found");
        }
        user.setId(id);
        return repository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }
    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("UserId " + id + "not found");
        }
        repository.deleteById(id);

    }
}
