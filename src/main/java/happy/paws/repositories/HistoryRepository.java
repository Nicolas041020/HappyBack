package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.History;

public interface HistoryRepository extends JpaRepository<History,Integer> {

}
