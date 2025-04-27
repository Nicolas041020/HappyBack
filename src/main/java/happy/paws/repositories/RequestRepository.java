package happy.paws.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Paseador;
import happy.paws.model.Request;

public interface RequestRepository extends JpaRepository <Request,Integer> {

    @Query(value= "SELECT m.* FROM PASEADOR m INNER JOIN REQUEST r ON m.id = r.paseador_id WHERE r.usuario_id = :userId AND r.estado = 1",nativeQuery = true)
    List<Paseador> getPaseadoresPorUserId(@Param("userId") int userId);

    @Query(value="SELECT p.paseador_id FROM REQUEST p WHERE p.usuario_id = :userId AND p.estado = -1", nativeQuery = true)
    List<Integer> findPaseadoresRechazadosByUsuario(@Param("userId") Integer userId);

    @Query(value = "SELECT p.id FROM Paseador p WHERE p.id NOT IN ( SELECT r.paseador_id FROM REQUEST r WHERE r.usuario_id = :userId)",nativeQuery = true)
    List<Integer> findNoRela(@Param("userId") Integer userId);
}
