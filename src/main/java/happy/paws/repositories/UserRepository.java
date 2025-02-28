package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByIdentification(String identification);
    User findByEmail(String email);
}
