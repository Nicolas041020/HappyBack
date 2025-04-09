package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.History;

public interface HistoryRepository extends JpaRepository<History,Integer> {

    @Query(value = "SELECT r FROM History r WHERE r.pet= :petId", nativeQuery = true)
    List<History> findAllByPetId(@Param("userId") Integer petId);

}
