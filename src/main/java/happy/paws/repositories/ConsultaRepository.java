package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta,Integer>{

    @Query(value = "SELECT * FROM consulta WHERE pet_id=:pet_id", nativeQuery = true)
    List<Consulta> getConsultasByPetId(@Param("pet_id") int pet_id);
}
