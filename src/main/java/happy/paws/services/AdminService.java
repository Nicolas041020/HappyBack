package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.Admin;
import happy.paws.repositories.AdminRepository;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public Admin logIn(String email, String contrasena){
        Admin admin = adminRepository.findByEmail(email);
        if(admin!=null && admin.getPassw().equals(contrasena)){
            return admin;
        }
        return null;
    }
}
