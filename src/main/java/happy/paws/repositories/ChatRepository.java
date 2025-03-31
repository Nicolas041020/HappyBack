package happy.paws.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Chat;
import happy.paws.model.Paseador;
import happy.paws.model.User;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    Optional<Chat> findByUserAndPaseador(User user, Paseador Paseador);

}
