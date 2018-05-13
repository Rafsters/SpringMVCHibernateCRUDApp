package pl.crudApp.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.crudApp.dao.CustomerDAO;
import pl.crudApp.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

   // need to inject the customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model){

        // get the customers from the dao

        List<Customer> customers = customerService.getCustomers();

        // add the customers to the model

        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        // create model attribute to bind form data

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";

    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model){


        // get the customer from our service

        Customer customer = customerService.getCustomer(id);

        // set a customer as a model attribute to pre-populate the form

        model.addAttribute("customer", customer);

        // send over to our form

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){

        // Delete customer

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model model){
        List<Customer> customers = customerService.searchCustomers(theSearchName);

        model.addAttribute("customers", customers);

        return "list-customers";
    }


}
