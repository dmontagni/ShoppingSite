package it.iagica.shoppingsite.Controller;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateFactory {

    public static SessionFactory getFactoryMysql(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build(); // configures settings from hibernate.cfg.xml
        return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

}
