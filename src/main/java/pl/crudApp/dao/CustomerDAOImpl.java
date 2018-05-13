package pl.crudApp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.crudApp.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> getCustomers() {

        // get the current hibernate session

        Session session = sessionFactory.getCurrentSession();

        // create a query

        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        // execute and get result list

        List<Customer> customers = query.getResultList();

        // return the results

        return customers;
    }

    public void saveCustomer(Customer customer) {

        // get current hibernate session

        Session session = sessionFactory.getCurrentSession();

        // save the customer

        session.saveOrUpdate(customer);
    }

    public Customer getCustomer(int id) {

        // get the current hibernate session

        Session session = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key

        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    public void deleteCustomer(int id) {

        // get the current hibernate session

        Session session = sessionFactory.getCurrentSession();

        // delete object with primary key

        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();

    }

    public List<Customer> searchCustomers(String theSearchName) {
        Session session = sessionFactory.getCurrentSession();

        Query theQuery = null;
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = session.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;

    }
}
