package FoodPandaBack.controller;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/admins")
    public List<Admin> getAdmins() {
        logger.info("Admin Controller: Received GET Request - get all admins");
        return adminService.getAdmins();
    }

    @PostMapping("/admins")
    public Admin newAdmin(@RequestBody Admin newAdmin) {
        logger.info("Admin Controller: Received POST Request - insert new admin");
        return adminService.saveAdmin(newAdmin);
    }

    @GetMapping("/admins/{username}")
    public Admin getAdminByUsername(@PathVariable String username) {
        logger.info("Admin Controller: Received GET Request - get admin by username");
        return adminService.getAdminByUsername(username);
    }

    @PostMapping("/admins/pdf")
    public int generatePdf(@RequestBody List<MenuItem> items) {
        logger.info("Admin Controller: Received Request - generate menu PDF");
        return adminService.generatePdf(items);
    }

    @PutMapping("/admins/{userId}")
    public void updateAdmin(@RequestBody Admin newAdmin, @PathVariable Long userId) {
        logger.info("Admin Controller: Received PUT Request - update admin");
        adminService.updateAdmin(newAdmin, userId);
    }


}
