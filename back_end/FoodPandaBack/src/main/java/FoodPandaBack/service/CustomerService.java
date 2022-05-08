package FoodPandaBack.service;

import FoodPandaBack.model.Customer;
import FoodPandaBack.repository.CustomerRepository;
import FoodPandaBack.utils.CartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * Gets list of all Customers
     *
     * @return list of Customers
     */
    public List<Customer> getCustomers() {
        try{
            List<Customer> l = customerRepository.findAll();
            logger.info("Customer Service: Successful GET request - get all customers; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Customer Service: Failed GET Request - get all customers - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves a new Customer to the database
     *
     * @param newCustomer the Customer to be saved
     * @return the Customer that was saved
     */
    public Customer saveCustomer(Customer newCustomer) {
        try{
            Customer c = customerRepository.save(newCustomer);
            logger.info("Customer Service: Successful POST request - insert new customer with username: " + c.getUsername());
            return c;
        } catch (Exception e) {
            logger.error("Customer Service: Failed POST Request - insert new customer - " + e.getMessage());
            return null;
        }
    }

    /**
     * Get Customer by username
     *
     * @param username username you want to search Customer by
     * @return the Customer that was found given the username
     */
    public Customer getCustomerByUsername(String username) {
        try{
            Customer c = customerRepository.findByUsername(username);;
            logger.info("Customer Service: Successful GET request - get customer with username: " + c.getUsername());
            return c;
        } catch (Exception e) {
            logger.error("Customer Service: Failed GET Request - get customer by username - " + e.getMessage());
            return null;
        }
    }

    /**
     * Sends email to Restaurant Admin with the current order
     *
     * @param address address where the customer wants the food delivered
     * @param details additional order details
     * @param cart items currently in cart and their quantities
     * @param total total price
     * @return status indicating the success or failure of action
     */
    public int sendEmail(String address, String details, List<CartItem> cart, double total) {
        String to = "mihaiblagu13@gmail.com";
        String from = "web@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mihaiblagu13@gmail.com", "******");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("New Order!");

            String order = "";
            order += address;
            order += "\n";
            order += details;
            order += "\n\n";
            order += "Items: \n";
            for(CartItem c : cart) {
                order += c.getItem().getTitle();
                order += " - ";
                order += c.getNbOfItems();
                order += "\n";
            }
            order += "\nTotal: " + total;

            message.setText(order);
            Transport.send(message);

            logger.info("Customer Controller: Successful Request - sent email");
            return 0;
        } catch (MessagingException mex) {
            logger.info("Customer Controller: Failed Request - something went wrong: ");
            mex.printStackTrace();
            return -1;
        }
    }
}
