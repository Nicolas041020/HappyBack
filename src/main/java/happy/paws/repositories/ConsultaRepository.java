package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Consulta;
import happy.paws.model.History;
import happy.paws.model.Pet;

public interface ConsultaRepository extends JpaRepository<Consulta,Integer>{

    @Query(value = "SELECT * FROM consulta WHERE pet_id=:pet_id", nativeQuery = true)
    List<Consulta> getConsultasByPetId(@Param("pet_id") int pet_id);

    @Query(value = "SELECT m.* FROM consulta m WHERE m.pet_id = (SELECT m2.pet_id FROM consulta m2 GROUP BY m2.pet_id ORDER BY COUNT(m2.pet_id) DESC LIMIT 1)", nativeQuery = true)
    Pet getMascotaMasFreqC();

    @Query(value = "SELECT r.pet_id FROM consulta r WHERE r.resultado= :state", nativeQuery = true)
    List<Pet> findByState(@Param("state") String state);


}
