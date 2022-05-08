package FoodPandaBack.service;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.repository.AdminRepository;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    public AdminRepository adminRepository;

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private static final String EXPORT_PATH = "D:\\Mihai\\Facultate\\AN3\\SD\\FoodPanda\\back_end\\FoodPandaBack\\pdf_exports";

    /**
     * Gets list of all Admins
     *
     * @return list of Admins
     */
    public List<Admin> getAdmins() {
        try{
            List<Admin> l = adminRepository.findAll();
            logger.info("Admin Service: Successful GET request - get all admins; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Admin Service: Failed GET Request - get all admins - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves a new Admin to the database
     *
     * @param newAdmin the Admin to be saved
     * @return the Admin that was saved
     */
    public Admin saveAdmin(Admin newAdmin) {
        try{
            Admin a = adminRepository.save(newAdmin);
            logger.info("Admin Service: Successful POST request - insert new admin with username: " + a.getUsername());
            return a;
        } catch (Exception e) {
            logger.error("Admin Service: Failed POST Request - insert new admin - " + e.getMessage());
            return null;
        }
    }

    /**
     * Get Admin by username
     *
     * @param username username you want to search Admin by
     * @return the Admin that was found given the username
     */
    public Admin getAdminByUsername(String username) {
        try{
            Admin a = adminRepository.findByUsername(username);;
            logger.info("Admin Service: Successful GET request - get admin by username with username: " + a.getUsername());
            return a;
        } catch (Exception e) {
            logger.error("Admin Service: Failed GET Request - get admin by username - " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates the Restaurant of an Admin
     *
     * @param newAdmin Admin instance with updated restaurant
     * @param userId userId of Admin that needs to be updated
     * @throws NullPointerException if userId is null
     */
    public void updateAdmin(Admin newAdmin, Long userId) throws NullPointerException {
        if(newAdmin.getRestaurant() == null) {
            throw new NullPointerException();
        }
        try{
            adminRepository.updateAdminRest(newAdmin.getRestaurant(), userId);
            logger.info("Admin Service: Successful PUT request - update admin with username: " + newAdmin.getUsername());
        } catch (Exception e) {
            logger.error("Admin Service: Failed PUT Request - update admin - " + e.getMessage());
        }
    }

    /**
     * Generates a pdf of the menu of the restaurant owned by the Admin
     *
     * @param items the items in the menu
     * @return status indicating the success or failure of action
     */
    public int generatePdf(List<MenuItem> items) {
        if(items.size() > 0) {
            String docName = items.get(0).getRestaurant().getName() + "_menu.pdf";
            try {
                Document document = new Document(PageSize.A4);
                File file = new File(EXPORT_PATH + "\\" + docName);
                file.createNewFile();
                FileOutputStream fop = new FileOutputStream(file);
                PdfWriter.getInstance(document, fop);

                document.open();

                document.add(new Paragraph("MENU"));
                document.add(new Paragraph(""));
                for(MenuItem i : items) {
                    String entry = i.getCategory().name() + " " + i.getTitle() + " " + i.getPrice();
                    Paragraph paragraph = new Paragraph(entry);
                    document.add(paragraph);
                }

                document.close();
                fop.flush();
                fop.close();

                logger.info("Admin Service: Successful request - generated menu PDF at: " + EXPORT_PATH);
                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Admin Service: Failed request - generate menu PDF: " + e.getMessage());
                return 1;
            }
        } else {
            logger.error("Admin Service: Failed request - generate menu PDF: "
                    + "No items passed to function. No PDF was created");
            return -1;
        }
    }
}
