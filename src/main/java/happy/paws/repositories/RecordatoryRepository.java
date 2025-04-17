package happy.paws.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Recordatory;

public interface RecordatoryRepository extends JpaRepository <Recordatory,Integer>{

    @Query(value = "SELECT * FROM RECORDATORY r WHERE DATE(r.recordatory_date) = CURRENT_DATE AND r.estado = false", nativeQuery = true)
    List<Recordatory> rec();

    @Query(value = "SELECT r.* FROM recordatory r " +
               "JOIN pet p ON r.pet_id = p.pet_id " +
               "JOIN user_t o ON p.user_id = o.user_id " +
               "WHERE o.user_id = :userId", nativeQuery = true)
    List<Recordatory> findAllByUserId(@Param("userId") Integer userId);

    Recordatory findByVaccineAndDate(String vaccine, Date date);

    @Query("SELECT r FROM Recordatory r WHERE r.recordatoryDate BETWEEN :fecha1 AND :fecha2")
    List<Recordatory> findRecordatoriesBetween(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
    


}
