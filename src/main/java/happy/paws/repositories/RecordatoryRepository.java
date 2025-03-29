package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import happy.paws.model.Recordatory;

public interface RecordatoryRepository extends JpaRepository <Recordatory,Integer>{

    @Query(value = "SELECT * FROM RECORDATORY r WHERE DATE(r.recordatory_date) = CURRENT_DATE AND r.estado = false", nativeQuery = true)
    List<Recordatory> rec();




}
