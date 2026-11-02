package model;

import jakarta.persistence.*;

@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private int idPengguna;

    @Column(name = "nama_pengguna")
    private String namaPengguna;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "lokasi_operasional_id_lokasi")
    private Integer lokasiOperasionalId;

    public Pengguna() {}

    public Pengguna(String namaPengguna, String username, String password, String role, Integer lokasiOperasionalId) {
        this.namaPengguna = namaPengguna;
        this.username = username;
        this.password = password;
        this.role = role;
        this.lokasiOperasionalId = lokasiOperasionalId;
    }

    public int getIdPengguna() { return idPengguna; }
    public void setIdPengguna(int idPengguna) { this.idPengguna = idPengguna; }
    public String getNamaPengguna() { return namaPengguna; }
    public void setNamaPengguna(String namaPengguna) { this.namaPengguna = namaPengguna; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Integer getLokasiOperasionalId() { return lokasiOperasionalId; }
    public void setLokasiOperasionalId(Integer lokasiOperasionalId) { this.lokasiOperasionalId = lokasiOperasionalId; }
}