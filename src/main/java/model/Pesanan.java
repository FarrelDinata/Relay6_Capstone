package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pesanan")
public class Pesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pesanan")
    private int idPesanan;

    @Column(name = "nama_pengirim")
    private String namaPengirim;

    @Column(name = "no_telepon_pengirim")
    private String noTeleponPengirim;

    @Column(name = "alamat_pengirim")
    private String alamatPengirim;

    @Column(name = "email_pengirim")
    private String emailPengirim;

    @Column(name = "nama_penerima")
    private String namaPenerima;

    @Column(name = "telepon_penerima")
    private String teleponPenerima;

    @Column(name = "alamat_tujuan")
    private String alamatTujuan;

    @Column(name = "kota_tujuan")
    private String kotaTujuan;

    @Column(name = "jenis_kiriman")
    private String jenisKiriman;

    @Column(name = "berat_barang")
    private Double beratBarang;

    @Column(name = "total_biaya")
    private Double totalBiaya;

    @Column(name = "tanggal_pesanan", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalPesanan;

    @Column(name = "status_pesanan")
    private String statusPesanan;

    @Column(name = "layanan_id_layanan")
    private int layananId;

    @Column(name = "pengguna_id_pengguna")
    private int penggunaId;

    public Pesanan() {}

    public int getIdPesanan() { return idPesanan; }
    public void setIdPesanan(int idPesanan) { this.idPesanan = idPesanan; }
    public String getNamaPengirim() { return namaPengirim; }
    public void setNamaPengirim(String namaPengirim) { this.namaPengirim = namaPengirim; }
    public String getNoTeleponPengirim() { return noTeleponPengirim; }
    public void setNoTeleponPengirim(String noTeleponPengirim) { this.noTeleponPengirim = noTeleponPengirim; }
    public String getAlamatPengirim() { return alamatPengirim; }
    public void setAlamatPengirim(String alamatPengirim) { this.alamatPengirim = alamatPengirim; }
    public String getEmailPengirim() { return emailPengirim; }
    public void setEmailPengirim(String emailPengirim) { this.emailPengirim = emailPengirim; }
    public String getNamaPenerima() { return namaPenerima; }
    public void setNamaPenerima(String namaPenerima) { this.namaPenerima = namaPenerima; }
    public String getTeleponPenerima() { return teleponPenerima; }
    public void setTeleponPenerima(String teleponPenerima) { this.teleponPenerima = teleponPenerima; }
    public String getAlamatTujuan() { return alamatTujuan; }
    public void setAlamatTujuan(String alamatTujuan) { this.alamatTujuan = alamatTujuan; }
    public String getKotaTujuan() { return kotaTujuan; }
    public void setKotaTujuan(String kotaTujuan) { this.kotaTujuan = kotaTujuan; }
    public String getJenisKiriman() { return jenisKiriman; }
    public void setJenisKiriman(String jenisKiriman) { this.jenisKiriman = jenisKiriman; }
    public Double getBeratBarang() { return beratBarang; }
    public void setBeratBarang(Double beratBarang) { this.beratBarang = beratBarang; }
    public Double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(Double totalBiaya) { this.totalBiaya = totalBiaya; }
    public Date getTanggalPesanan() { return tanggalPesanan; }
    public void setTanggalPesanan(Date tanggalPesanan) { this.tanggalPesanan = tanggalPesanan; }
    public String getStatusPesanan() { return statusPesanan; }
    public void setStatusPesanan(String statusPesanan) { this.statusPesanan = statusPesanan; }
    public int getLayananId() { return layananId; }
    public void setLayananId(int layananId) { this.layananId = layananId; }
    public int getPenggunaId() { return penggunaId; }
    public void setPenggunaId(int penggunaId) { this.penggunaId = penggunaId; }
}