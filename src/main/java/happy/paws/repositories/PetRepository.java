package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Pet;

public interface PetRepository extends JpaRepository <Pet,Integer> {

}
