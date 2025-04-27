package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Consulta;
import happy.paws.model.Pet;

public interface ConsultaRepository extends JpaRepository<Consulta,Integer>{

    @Query(value = "SELECT * FROM consulta WHERE pet_id=:pet_id", nativeQuery = true)
    List<Consulta> getConsultasByPetId(@Param("pet_id") int pet_id);

    @Query(value = "SELECT p.*  FROM pet p JOIN consulta c ON p.pet_id = c.pet_id \n" + //
                "GROUP BY p.pet_id  ORDER BY COUNT(c.pet_id) DESC LIMIT 1; ", nativeQuery = true)
    Pet getMascotaMasFreqC();

    @Query(value = "SELECT DISTINCT p.*  FROM pet p JOIN consulta c ON p.pet_id = c.pet_id \n" + //
                "WHERE c.estado= :state", nativeQuery = true)
    List<Pet> findByState(@Param("state") String state);

    @Query(value = "SELECT COUNT(*) FROM consulta WHERE pet_id = :petId", nativeQuery = true)
    int countVisitasByPetId(@Param("petId") Integer petId);


}
