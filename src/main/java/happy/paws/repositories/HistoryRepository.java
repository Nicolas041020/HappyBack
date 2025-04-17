package happy.paws.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.History;

public interface HistoryRepository extends JpaRepository<History,Integer> {

    @Query(value = "SELECT r FROM History r WHERE r.pet= :petId", nativeQuery = true)
    List<History> findAllByPetId(@Param("userId") Integer petId);

    @Query(value = "SELECT r FROM History r WHERE r.date BETWEEN :fecha1 AND :fecha2", nativeQuery= true)
    List<History> findVaccinesBetween(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);

    @Query(value = "SELECT COUNT(*) FROM history WHERE vaccine = :vaccine", nativeQuery = true)
    Integer countByVaccine(@Param("vaccine") String vaccine);
 
}
