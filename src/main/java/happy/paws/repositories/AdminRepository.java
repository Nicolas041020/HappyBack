package happy.paws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import happy.paws.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
    Admin findByEmail(String Email);
}
