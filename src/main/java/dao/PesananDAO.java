package dao;

import config.HibernateUtil;
import model.Pesanan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PesananDAO {
    
    // Menarik seluruh data pesanan untuk Agen dan Cabang
    public static List<Pesanan> getAllPesanan() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pesanan", Pesanan.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Menarik riwayat pesanan khusus untuk satu Pelanggan tertentu
    public static List<Pesanan> getPesananByPelanggan(int penggunaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pesanan> query = session.createQuery("FROM Pesanan WHERE penggunaId = :pid", Pesanan.class);
            query.setParameter("pid", penggunaId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Mencari 1 pesanan spesifik berdasarkan ID Pesanan
    public static Pesanan getPesananById(int idPesanan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pesanan.class, idPesanan);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Menyimpan pesanan baru (Dari Pelanggan)
    public static boolean buatPesanan(Pesanan p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Memperbarui berat barang / status pesanan (Oleh Agen)
    public static boolean updatePesanan(Pesanan p) {
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

    // Menghapus data pesanan berdasarkan ID (Oleh Agen)
    public static boolean deletePesanan(int idPesanan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Pesanan p = session.get(Pesanan.class, idPesanan);
            if (p != null) {
                session.remove(p);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}