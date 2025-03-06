package happy.paws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.User;
import happy.paws.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
        
    }

    @PostMapping("/save")
    private ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    @GetMapping("/search/{id}")
    public ResponseEntity<User> searchUser(@PathVariable int id){
        return ResponseEntity.ok().body(userService.searchUser(id)); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build(); 
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user){
        userService.updateUser(id,user);
        return ResponseEntity.ok().build();
        
    }

    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User user){
        User usert = userService.logIn(user.getUsername(),user.getPassword());
        if (usert != null) return ResponseEntity.ok(usert);
        return ResponseEntity.badRequest().build();
    }

}
