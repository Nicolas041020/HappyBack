package happy.paws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Admin;
import happy.paws.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



    @PostMapping("/login")
    public ResponseEntity<Admin> logIn(@RequestBody Admin admin){
        Admin ad = adminService.logIn(admin.getEmail(),admin.getPassw());
        if (ad != null) return ResponseEntity.ok(ad);
        return null;
    }
}
