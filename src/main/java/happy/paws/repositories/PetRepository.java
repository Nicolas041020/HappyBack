package happy.paws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Pet;

public interface PetRepository extends JpaRepository <Pet,Integer> {

    @Query(value = "SELECT * FROM PET WHERE user_id =:id_owner", nativeQuery = true)
    List<Pet> getAllPets(@Param("id_owner") int id_owner);
}
