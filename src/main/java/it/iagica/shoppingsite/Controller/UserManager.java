package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.Orders;
import it.iagica.shoppingsite.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UserManager {

    Session session = HibernateFactory.getFactoryMysql().openSession();
/**
     * Questo metodo accetta un oggetto di tipo Customer in input e lo salva sul database
     * @param user il customer da salvare
     * @return boolean true se l'oggetto è stato salvato correttamente
     * */
    public boolean saveUser(User user){
        session.getTransaction().begin();
        Serializable obj = session.save(user);
        session.getTransaction().commit();
        if(obj != null){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Questo metodo ritorna una lista di oggetti Customer
     * @return List<Customer> lista di customer
     * */
    public List<User> getAllUsers(){
        List<User> users = session.createQuery("from User", User.class).getResultList();
        return users;
    }
    public List<Orders> getAllOrdini(){
        List<Orders> orderList = session.createQuery("from Orders", Orders.class).getResultList();
        return orderList;
    }

    public List<Orders> getIdUtenteOrders(User user){
        List<Orders> orderResult = new ArrayList<>();
        for (Orders order: getAllOrdini()) {
            if(order.getIdUser().getId().equals(user.getId())){
                orderResult.add(order);
            }

        } return orderResult;
    }

    /**
     * Questo metodo ritorna un utente ricevendo uno username in input
     * @return User user
     * */
    public User getUser(String username){
        Query query = session.createQuery("FROM User u WHERE u.username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    public User getUserInfo(Integer userId){
        Query query = session.createQuery("FROM User u WHERE u.iduser=:iduser");
        query.setParameter("iduser", userId);
        return (User) query.uniqueResult();
    }
    
    
    public boolean saveUserDetails(User user){
        session.getTransaction().begin();
        Serializable obj = session.save(user);
        session.getTransaction().commit();
        if(obj != null){
            return true;
        } else{
            return false;
        }
    }

    public static Integer getUserId(String usrn){
        Session session = HibernateFactory.getFactoryMysql().openSession();
        Query query = session.createQuery("SELECT idUser FROM User u WHERE u.username=:usrn");
        query.setParameter("usrn", usrn);
        return (Integer) query.uniqueResult();
    }



    public static void deleteUser(int id) {
        //Create session factory object
        SessionFactory sessionFactory = HibernateFactory.getFactoryMysql();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        User user = (User)session.load(User.class, id);
        session.delete(user);
        System.out.println("Deleted Successfully");
        session.getTransaction().commit();
        sessionFactory.close();
    }
    
    
//    public boolean  updateUser(int id, String nome,String  cognome, Date data, String telefono, String email) {
//		//Create session factory object
//	  SessionFactory sessionFactory = HibernateFactory.getFactoryMysql();
//	  //getting session object from session factory
//	  Session session = sessionFactory.openSession();
//	  //getting transaction object from session object
//	  session.beginTransaction();
//
//	  User user = (User)session.get(User.class, id);
//	  user.setNome(nome);
//	  user.setCognome(cognome);
//	  user.setData_nascita(data);
//	  user.setTelefono(telefono);
//	  user.setEmail(email);
//	  System.out.println("Updated Successfully");
//      session.save(user);
//	  session.getTransaction().commit();
//	  sessionFactory.close();
//      return true;
// }
    

    public boolean validate(String username) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateFactory.getFactoryMysql().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
                    .uniqueResult();

            if (user != null) {

                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}