package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByIdentification(String identification);
    User findByEmail(String email);
    User findByUsername(String username);

    @Query(value = "SELECT user_id FROM users WHERE username =:usern",nativeQuery = true)
    int getIdbyUsername(@Param("usern") String usern);
}
