package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Request;

public interface RequestRepository extends JpaRepository <Request,Integer> {

}
