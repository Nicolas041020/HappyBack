package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {
    List<Mensaje> findByChatIdOrderByTimestAsc(Integer chatId);

}
