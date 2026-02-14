package com.fsad.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductDAO {
    public void addProduct(Product product) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Product getProduct(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    // HQL: Sort by price ascending
    public List<Product> getProductsSortedByPriceAsc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product order by price asc", Product.class).list();
        }
    }

    // HQL: Sort by price descending
    public List<Product> getProductsSortedByPriceDesc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product order by price desc", Product.class).list();
        }
    }

    // HQL: Sort by quantity descending
    public List<Product> getProductsSortedByQuantityDesc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product order by quantity desc", Product.class).list();
        }
    }

    // HQL: Pagination (first n products)
    public List<Product> getProductsPaginated(int start, int count) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product order by id", Product.class)
                    .setFirstResult(start)
                    .setMaxResults(count)
                    .list();
        }
    }

    // HQL: Count total products
    public long countTotalProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from Product", Long.class).uniqueResult();
        }
    }

    // HQL: Count products where quantity > 0
    public long countProductsWithQuantityGreaterThanZero() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select count(*) from Product where quantity > 0", Long.class).uniqueResult();
        }
    }

    // HQL: Count products grouped by description
    public List<Object[]> countProductsGroupedByDescription() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select description, count(*) from Product group by description", Object[].class).list();
        }
    }

    // HQL: Find min and max price
    public Object[] findMinAndMaxPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select min(price), max(price) from Product", Object[].class).uniqueResult();
        }
    }

    // HQL: Group by description
    public List<Object[]> groupByDescription() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select description, count(*) from Product group by description", Object[].class).list();
        }
    }

    // HQL: Filter by price range
    public List<Product> filterByPriceRange(double min, double max) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product where price between :min and :max", Product.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .list();
        }
    }

    // HQL: LIKE queries
    public List<Product> nameStartsWith(String prefix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product where name like :prefix", Product.class)
                    .setParameter("prefix", prefix + "%")
                    .list();
        }
    }

    public List<Product> nameEndsWith(String suffix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product where name like :suffix", Product.class)
                    .setParameter("suffix", "%" + suffix)
                    .list();
        }
    }

    public List<Product> nameContains(String pattern) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product where name like :pattern", Product.class)
                    .setParameter("pattern", "%" + pattern + "%")
                    .list();
        }
    }

    public List<Product> nameWithExactLength(int length) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product where length(name) = :len", Product.class)
                    .setParameter("len", length)
                    .list();
        }
    }

    public void updateProduct(Product product) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
