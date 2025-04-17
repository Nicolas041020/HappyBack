package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByIdentification(String identification);
    User findByEmail(String email);
    User findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.id IN (SELECT p.user_id FROM Pet p GROUP BY p.user_id ORDER BY COUNT(p.user_id) DESC)",nativeQuery = true)
    List<User> getUserByMostPets();


   // @Query(value = "SELECT user_id FROM user_t WHERE username =:usern",nativeQuery = true)
    //int getIdbyUsername(@Param("usern") String usern);
}
