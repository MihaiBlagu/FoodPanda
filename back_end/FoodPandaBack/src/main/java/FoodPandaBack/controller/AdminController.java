package FoodPandaBack.controller;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.Customer;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }

    @PostMapping("/admins")
    public Admin newAdmin(@RequestBody Admin newAdmin) {
        return adminService.saveAdmin(newAdmin);
    }

    @GetMapping("/admins/{username}")
    public Admin getAdminByUsername(@PathVariable String username){
        return adminService.getAdminByUsername(username);
    }

    @PutMapping("/admins/{userId}")
    public void updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long userId) {
        adminService.updateAdmin(newAdmin, userId);
    }
}
