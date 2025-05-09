package happy.paws.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.RitmoCardiaco;

public interface RitmoCardiacoRepository extends JpaRepository <RitmoCardiaco,Integer>{

    @Query(value =
  "SELECT AVG(r.valor) FROM ritmo r WHERE DATE(r.date) = :fecha AND r.pet_id = :pet_id",
  nativeQuery = true
)
Double findByDateOnly(@Param("fecha") LocalDate fecha, @Param("pet_id") int petId);

}
