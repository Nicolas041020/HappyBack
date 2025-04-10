package happy.paws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.User;
import happy.paws.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
       return userRepository.save(user);
       
    }

    public User searchUser(int id){
        return userRepository.findById(id) .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    public void deleteUser(int id){
     userRepository.deleteById(id);
    }

    public User updateUser(String id, User user){
        User temp = userRepository.findByIdentification(id);

        if (temp == null) {
            return null; 
        }
    
        if (user.getFirstname() != null) temp.setFirstname(user.getFirstname());
        if (user.getLastname() != null) temp.setLastname(user.getLastname());
        if (user.getIdentification() != null) temp.setIdentification(user.getIdentification());
        if (user.getAddress() != null) temp.setAddress(user.getAddress());
        if (user.getEmail() != null) temp.setEmail(user.getEmail());
        if (user.getUsername() != null) temp.setUsername(user.getUsername());
        if (user.getPassword() != null) temp.setPassword(user.getPassword());
        if (user.getPhoneNumber() != null) temp.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(temp);

    }

    public User logIn(String username, String contrasena){
        User user = userRepository.findByUsername(username);
        if(user!=null && user.getPassword().equals(contrasena)){
            return user;
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUserByMostP(){
        return userRepository.getUserByMostPets();
    }
}
