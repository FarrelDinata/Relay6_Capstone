package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pengiriman")
public class Pengiriman {

    @Id
    @Column(name = "nomor_resi")
    private String nomorResi;

    @Column(name = "tanggal_pengiriman")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalPengiriman;

    @Column(name = "tanggal_diterima")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalDiterima;

    @Column(name = "status_pengiriman")
    private String statusPengiriman;

    @Column(name = "cabang_id_lokasi")
    private int cabangIdLokasi;

    @Column(name = "agen_id_lokasi")
    private int agenIdLokasi;

    public Pengiriman() {}

    public String getNomorResi() { return nomorResi; }
    public Date getTanggalPengiriman() { return tanggalPengiriman; }
    public Date getTanggalDiterima() { return tanggalDiterima; }
    public String getStatusPengiriman() { return statusPengiriman; }
    public int getCabangIdLokasi() { return cabangIdLokasi; }
    public int getAgenIdLokasi() { return agenIdLokasi; }
}