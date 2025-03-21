package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Vet;


public interface VetRepository extends JpaRepository<Vet,Integer>{

    @Query(value = "SELECT * FROM vet WHERE email =:emailr",nativeQuery = true)
    Vet getbyEmail(@Param("emailr") String emailr);
}
