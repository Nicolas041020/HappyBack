package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.User;
import happy.paws.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User searchUser(int id){
        return userRepository.findById(id) .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    public void deleteUser(int id){
     userRepository.deleteById(id);
    }

    public User updateUser(String id, User user){
        User temp = userRepository.findByIdentification(id);
        temp.setFirstname(user.getFirstname());
        temp.setLastname(user.getLastname());
        temp.setIdentification(user.getIdentification());
        temp.setAddress(user.getAddress());
        temp.setEmail(user.getEmail());
        temp.setUsername(user.getUsername());
        temp.setPassword(user.getPassword());
        temp.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(temp);

    }

    public boolean logIn(String email, String contrasena){
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return user.getPassword().equals(contrasena);
        }
        return false;
    }
}
