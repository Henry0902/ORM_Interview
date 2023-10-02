package fa.training.dao.impl;

import fa.training.dao.BaseDao;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoImpl<E,ID extends Serializable> implements BaseDao<E,ID> {
    private Class<E> classType;

    private SessionFactory sessionFactory;

    public BaseDaoImpl(Class<E> classType){
        this.classType = classType;
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public ID save(E e) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            Serializable id = session.save(e);
            session.getTransaction().commit();
            return (ID) id;
        }
    }

    @Override
    public List<E> getAll(){
        try(Session session = HibernateUtils.getSession()){
            session.beginTransaction();

            //use hql
            Query<E> query = session
                    .createQuery("from " + classType.getSimpleName(), classType);
            session.getTransaction().commit();
            return  query.getResultList();
        }

    }
    @Override
    public E findOne(ID id) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.getTransaction().commit();
            return session.get(classType, id);
        }
    }

    @Override
    public void update(E e) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.update(e);
            session.getTransaction().commit();

        }
    }


    @Override
    public void deleteById(ID id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            E e = findOne(id);
            session.delete(e);
            session.getTransaction().commit();
        }
    }
}
