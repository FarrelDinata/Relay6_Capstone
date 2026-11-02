package dao;

import config.HibernateUtil;
import model.Pengguna;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {
    
    // Fungsi untuk Login
    public static Pengguna login(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pengguna> query = session.createQuery("FROM Pengguna WHERE username = :u AND password = :p", Pengguna.class);
            query.setParameter("u", username);
            query.setParameter("p", password);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fungsi Registrasi Pelanggan
    public static boolean registerPelanggan(String nama, String telp, String alamat, String email, String username, String password) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Cek ketersediaan username
            Query<Pengguna> query = session.createQuery("FROM Pengguna WHERE username = :u", Pengguna.class);
            query.setParameter("u", username);
            if (query.uniqueResult() != null) return false; // Username sudah ada

            tx = session.beginTransaction();
            Pengguna p = new Pengguna(nama, username, password, "Pelanggan", null);
            session.persist(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Fungsi Registrasi Akun Internal (Pusat)
    public static boolean registerInternal(String nama, String username, String password, String role) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Cek ketersediaan username
            Query<Pengguna> query = session.createQuery("FROM Pengguna WHERE username = :u", Pengguna.class);
            query.setParameter("u", username);
            if (query.uniqueResult() != null) return false;

            tx = session.beginTransaction();
            Pengguna p = new Pengguna(nama, username, password, role, null);
            session.persist(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Fungsi Update Data Akun (Untuk Menu Kelola Akun Pelanggan)
    public static boolean updateUser(Pengguna p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}