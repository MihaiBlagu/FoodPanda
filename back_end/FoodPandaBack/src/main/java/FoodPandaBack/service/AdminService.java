package FoodPandaBack.service;

import FoodPandaBack.model.Admin;
import FoodPandaBack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    public AdminRepository adminRepository;

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Admin saveAdmin(Admin newAdmin) {
        return adminRepository.save(newAdmin);
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public void updateAdmin(Admin newAdmin, Long userId) {
//        return adminRepository.findById(userId)
//            .map(adm -> {
//                //adminRepository.deleteById(adm.getUserId());
//
//                adm.setUsername(newAdmin.getUsername());
//                adm.setPassword(newAdmin.getPassword());
//                adm.getRestaurant().setRestaurantId(newAdmin.getRestaurant().getRestaurantId());
//                adm.getRestaurant().setDeliveryZones(newAdmin.getRestaurant().getDeliveryZones());
//                adm.getRestaurant().setMenu(newAdmin.getRestaurant().getMenu());
//                adm.getRestaurant().setAdmin(newAdmin);
//                adm.getRestaurant().setName(newAdmin.getRestaurant().getName());
//
//                return adminRepository.updateAdminRest(adm.getRestaurant(), adm.getUserId());
//            })
//            .orElseGet(() -> {
//                newAdmin.setUserId(userId);
//                return adminRepository.save(newAdmin);
//            });
        adminRepository.updateAdminRest(newAdmin.getRestaurant(), userId);
    }
}
