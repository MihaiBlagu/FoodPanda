package FoodPandaBack.service;

import FoodPandaBack.model.*;
import FoodPandaBack.repository.AdminRepository;
import FoodPandaBack.utils.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    AdminRepository adminRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllAdminsTest() {
        List<Admin> adminList = new ArrayList<>();
        adminList.add(new Admin());
        adminList.add(new Admin());
        adminList.add(new Admin());

        when(adminRepository.findAll()).thenReturn(adminList);

        List<Admin> controlList = adminService.getAdmins();

        assertNotNull(controlList);
    }

    @Test
    public void getAdminByUsernameSuccess() {
        Admin adminInDatabase = new Admin();
        adminInDatabase.setUsername("adminInDatabase");

        when(adminRepository.findByUsername("adminInDatabase")).thenReturn(adminInDatabase);

        Admin controlAdmin = adminService.getAdminByUsername("adminInDatabase");

        assertEquals("adminInDatabase", controlAdmin.getUsername());
    }

    @Test
    public void getAdminByUsernameFail() {
        when(adminService.getAdminByUsername("adminNotInDatabase")).thenReturn(null);

        Admin controlAdmin = adminService.getAdminByUsername("adminNotInDatabase");

        assertNull(controlAdmin);
    }

    @Test(expected = Exception.class)
    public void getNullAdmin() {
        when(adminService.getAdminByUsername(null)).thenThrow(new Exception());

        Admin controlAdmin = adminService.getAdminByUsername(null);

        assertNull(controlAdmin);
    }

    @Test
    public void saveNewAdminSuccess() {
        Admin successAdmin = new Admin(1L,
                "successAdmin1",
                "123",
                new Restaurant());

        Admin testAdmin = new Admin(1L,
                "successAdmin1",
                "123",
                new Restaurant());
        when(adminRepository.save(testAdmin)).thenReturn(successAdmin);

        Admin controlAdmin = adminService.saveAdmin(testAdmin);

        assertEquals(successAdmin, controlAdmin);
    }

    @Test(expected = Exception.class)
    public void saveNewAdminFail() {
        when(adminService.saveAdmin(null)).thenThrow(new Exception());

        Admin controlAdmin = adminService.saveAdmin(null);

        assertNull(controlAdmin);
    }

    @Test
    public void updateAdminSuccess() {
        Admin updatedAdmin = new Admin(1L,
                "successAdmin1",
                "123",
                new Restaurant(1L, "newRestaurant"));

        adminService.updateAdmin(updatedAdmin, updatedAdmin.getUserId());
    }

    @Test(expected = NullPointerException.class)
    public void updateAdminFail() {
        Admin updatedAdmin = new Admin(1L,
                "successAdmin1",
                "123",
                null);

        adminService.updateAdmin(updatedAdmin, updatedAdmin.getUserId());
    }

    @Test
    public void generatePdfSuccess() {
        List<MenuItem> successList = new ArrayList<>();
        successList.add(new MenuItem("item1",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRestaurnat")));
        successList.add(new MenuItem("item2",
                14.0,
                Category.DINNER,
                new Restaurant(1L, "newRestaurnat")));

        int controlStatus = adminService.generatePdf(successList);

        assertEquals(0, controlStatus);
    }

    @Test
    public void generatePdfFail() {
        List<MenuItem> failList = new ArrayList<>();

        int controlStatus = adminService.generatePdf(failList);

        assertEquals(-1, controlStatus);
    }
}
