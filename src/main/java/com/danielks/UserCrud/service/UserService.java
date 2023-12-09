package com.danielks.UserCrud.service;

import com.danielks.UserCrud.config.HibernateUtil;
import com.danielks.UserCrud.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserService {
    public void save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
