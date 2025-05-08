package happy.paws.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happy.paws.model.Paseador;
import happy.paws.model.Request;
import happy.paws.model.User;

public interface RequestRepository extends JpaRepository <Request,Integer> {

    @Query(value= "SELECT m.* FROM PASEADOR m INNER JOIN REQUEST r ON m.id = r.paseador_id WHERE r.usuario_id = :userId AND r.estado = 1",nativeQuery = true)
    List<Paseador> getPaseadoresPorUserId(@Param("userId") int userId);

    @Query(value="SELECT p.paseador_id FROM REQUEST p WHERE p.usuario_id = :userId AND p.estado = -1", nativeQuery = true)
    List<Integer> findPaseadoresRechazadosByUsuario(@Param("userId") Integer userId);

    @Query(value = "SELECT p.id FROM Paseador p WHERE p.id NOT IN ( SELECT r.paseador_id FROM REQUEST r WHERE r.usuario_id = :userId)",nativeQuery = true)
    List<Integer> findNoRela(@Param("userId") Integer userId);

    @Query(value="SELECT COUNT(*) FROM REQUEST p WHERE p.paseador_id = :pasId AND p.estado = 0", nativeQuery = true)
    Integer findNumNotAccepted(@Param("pasId") Integer pasId);

    @Query(value="SELECT DISTINCT m.* FROM USER_T m INNER JOIN REQUEST r on m.user_id = r.usuario_id WHERE r.paseador_id = :pasId AND r.estado = 1",nativeQuery = true)
    List<User> getPaseadoresAceptadosUser(@Param("pasId") Integer pasId);

    @Query(value="SELECT r.* FROM REQUEST r WHERE r.paseador_id = :pasId AND r.estado = 0",nativeQuery = true)
    List<Request> findRequestToAccept(@Param("pasId") Integer pasId);

    @Query(value="SELECT r.* FROM REQUEST r WHERE r.usuario_id = :userId",nativeQuery = true)
    List<Request> findRequestOfUser(@Param("userId") Integer userId);

   // @Query(value = "SELECT DISTINCT p.* FROM Paseador p JOIN REQUEST r ON p.id = r.paseador_id WHERE p.name ILIKE '%' || :nombre || '%'",nativeQuery = true)
   // List<Paseador> findPaseadoresLikee(@Param("nombre") String nombre);

   @Query(value =
  "SELECT DISTINCT p.* FROM paseador p LEFT JOIN request r ON p.id = r.paseador_id WHERE (r.estado = -1 OR r.paseador_id IS NULL) AND p.name ILIKE '%' || :nombre || '%'",
  nativeQuery = true
)
List<Paseador> findPaseadoresLikee(@Param("nombre") String nombre);

}
