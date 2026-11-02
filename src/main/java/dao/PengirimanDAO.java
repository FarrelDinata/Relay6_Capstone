package dao;

import config.HibernateUtil;
import model.Pengiriman;
import org.hibernate.Session;

import java.util.List;

public class PengirimanDAO {
    
    // Menarik seluruh jadwal dan data pengiriman untuk ditampilkan di menu Cabang
    public static List<Pengiriman> getAllPengiriman() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pengiriman", Pengiriman.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}