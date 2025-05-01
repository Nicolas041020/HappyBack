package happy.paws.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Recorrido;

public interface RecorridoRepository extends JpaRepository <Recorrido,Integer>{

    Recorrido findTopByOrderByIdDesc();


}
