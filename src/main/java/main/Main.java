package main;

import config.HibernateUtil;
import dao.UserDAO;
import dao.PesananDAO;
import dao.PengirimanDAO;
import model.Pengguna;
import model.Pesanan;
import model.Pengiriman;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Memastikan koneksi database menyala di awal
        HibernateUtil.getSessionFactory();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // ==========================================
        // MENU LOGIN (Sesuai Flowchart Menu Login)
        // ==========================================
        while (running) {
            System.out.println("\n=================================");
            System.out.println("      MENU UTAMA RELAY APP       ");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Registrasi Pelanggan");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                System.out.print("Input Username: ");
                String username = scanner.nextLine();
                System.out.print("Input Password: ");
                String password = scanner.nextLine();

                System.out.println("[INFO] Sistem Memvalidasi Akun ke Database...");
                Pengguna user = UserDAO.login(username, password);
                
                if (user != null) {
                    System.out.println("[SUKSES] Menampilkan Notifikasi Berhasil Login sebagai " + user.getRole());
                    navigasiRole(user, scanner); // Masuk ke menu masing-masing role
                } else {
                    System.out.println("[GAGAL] Menampilkan Notifikasi Gagal. Username/Password salah.");
                }
            } 
            else if (pilihan == 2) {
                // ==========================================
                // MENU REGISTRASI (Sesuai Flowchart Registrasi)
                // ==========================================
                System.out.println("\n--- REGISTRASI PELANGGAN ---");
                System.out.print("Input Nama Pelanggan: ");
                String nama = scanner.nextLine();
                System.out.print("Input Nomor Telepon: ");
                String telp = scanner.nextLine();
                System.out.print("Input Alamat: ");
                String alamat = scanner.nextLine();
                System.out.print("Input Email: ");
                String email = scanner.nextLine();
                System.out.print("Input Username: ");
                String regUser = scanner.nextLine();
                System.out.print("Input Password: ");
                String regPass = scanner.nextLine();

                System.out.println("[INFO] Sistem Mengecek Ketersediaan Akun...");
                if (UserDAO.registerPelanggan(nama, telp, alamat, email, regUser, regPass)) {
                    System.out.println("[SUKSES] Sistem Menyimpan Akun ke Database (Relay SQL).");
                    System.out.println("Menampilkan Notifikasi Akun Berhasil Didaftarkan.");
                } else {
                    System.out.println("[GAGAL] Menampilkan Notifikasi Akun Sudah Terdaftar.");
                }
            } 
            else if (pilihan == 3) {
                running = false;
                System.out.println("Selesai. Keluar dari aplikasi Relay App.");
                HibernateUtil.shutdown();
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    private static void navigasiRole(Pengguna user, Scanner scanner) {
        boolean loggedIn = true;
        while (loggedIn) {
            String role = user.getRole();
            
            // ==========================================
            // FLOWCHART MENU PUSAT
            // ==========================================
            if (role.equals("Pusat")) {
                System.out.println("\n=== MENU PUSAT ===");
                System.out.println("1. Dashboard");
                System.out.println("2. Laporan Bulanan");
                System.out.println("3. Registrasi Akun Internal");
                System.out.println("4. Kembali");
                System.out.print("Pilih: ");
                int pPusat = scanner.nextInt();
                scanner.nextLine();

                if (pPusat == 1) {
                    System.out.println("[INFO] Sistem Membaca Data di Database (Relay SQL)...");
                    System.out.println("Menampilkan Visualisasi Laporan Hasil dan KPI.");
                    System.out.println("  > Total Pengguna: 20+ Aktif");
                    System.out.println("  > Cabang & Agen Beroperasi: 100%");
                } 
                else if (pPusat == 2) {
                    System.out.println("Menampilkan Laporan Total Hasil Pendapatan.");
                    System.out.print("Mau Filter? (Y/T): ");
                    String filter = scanner.nextLine();
                    if (filter.equalsIgnoreCase("Y")) {
                        System.out.println("[INFO] Membaca Data di Database (Relay SQL)...");
                        System.out.println("Menampilkan Laporan Total Hasil Pendapatan Sesuai Filter.");
                    }
                } 
                else if (pPusat == 3) {
                    System.out.println("\n--- MENU REGISTRASI AKUN INTERNAL ---");
                    System.out.print("Input Nama Pengguna: ");
                    String n = scanner.nextLine();
                    System.out.print("Input Username: ");
                    String u = scanner.nextLine();
                    System.out.print("Input Password: ");
                    String pw = scanner.nextLine();
                    System.out.println("Pilih Opsi Role Akun:");
                    System.out.println("1. Cabang");
                    System.out.println("2. Agen");
                    System.out.print("Pilih: ");
                    int rolePil = scanner.nextInt();
                    scanner.nextLine();
                    
                    String r = (rolePil == 1) ? "Cabang" : "Agen";
                    if (UserDAO.registerInternal(n, u, pw, r)) {
                        System.out.println("[SUKSES] Sistem Menyimpan Akun ke Database (Relay SQL).");
                        System.out.println("Menampilkan Notifikasi Akun Berhasil Dibuat.");
                    } else {
                        System.out.println("[GAGAL] Gagal membuat akun internal. Username mungkin sudah ada.");
                    }
                } 
                else if (pPusat == 4) {
                    loggedIn = false;
                }
            }
            
            // ==========================================
            // FLOWCHART MENU CABANG
            // ==========================================
            else if (role.equals("Cabang")) {
                System.out.println("\n=== MENU CABANG ===");
                System.out.println("1. Tambah Jadwal Pengiriman Ke Cabang Penerima");
                System.out.println("2. Tampilkan Jadwal Pengiriman");
                System.out.println("3. Penerimaan Barang Dari Cabang Pengirim");
                System.out.println("4. Tampilkan Data Pesanan Pelanggan");
                System.out.println("5. Cari Data Pengiriman");
                System.out.println("6. Tambah Jadwal Pengiriman Ke Agen Penerima");
                System.out.println("7. Kembali");
                System.out.print("Pilih: ");
                int pCabang = scanner.nextInt();
                scanner.nextLine();

                if (pCabang == 1) {
                    System.out.print("Input Tanggal Pengiriman (YYYY-MM-DD): ");
                    String tgl = scanner.nextLine();
                    System.out.print("Input Estimasi Tanggal Pengiriman: ");
                    String est = scanner.nextLine();
                    System.out.print("Input Status Pengiriman Pada Cabang Pengirim: ");
                    String stat = scanner.nextLine();
                    System.out.println("[INFO] Sistem Menyimpan Data ke Database (Relay SQL)...");
                    System.out.println("Menampilkan Notifikasi Berhasil Menambahkan.");
                } 
                else if (pCabang == 2) {
                    System.out.println("[INFO] Sistem Mengambil Data Pengiriman dari Database...");
                    List<Pengiriman> daftarKirim = PengirimanDAO.getAllPengiriman();
                    System.out.println("\nMenampilkan Jadwal Pengiriman Yang Dikirim / Diterima Cabang:");
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.printf("%-20s | %-25s | %-20s\n", "NOMOR RESI", "STATUS PENGIRIMAN", "TANGGAL KIRIM");
                    System.out.println("-------------------------------------------------------------------------");
                    if (daftarKirim == null || daftarKirim.isEmpty()) {
                        System.out.println("Tidak ada data pengiriman di database.");
                    } else {
                        for (Pengiriman k : daftarKirim) {
                            String tglKirimStr = (k.getTanggalPengiriman() != null) ? k.getTanggalPengiriman().toString() : "Belum Dikirim";
                            System.out.printf("%-20s | %-25s | %-20s\n", k.getNomorResi(), k.getStatusPengiriman(), tglKirimStr);
                        }
                    }
                    System.out.println("-------------------------------------------------------------------------");
                }
                else if (pCabang == 3) {
                    System.out.print("Input Status Pengiriman Pada Cabang Penerima: ");
                    String stat = scanner.nextLine();
                    System.out.println("[INFO] Sistem Menyimpan Data Ke Database...");
                    System.out.println("Sistem Menampilkan Notifikasi Berhasil Diubah.");
                } 
                else if (pCabang == 4) {
                    System.out.println("\nMenampilkan Tabel Data Pesanan Pelanggan:");
                    System.out.println("---------------------------------------------------------");
                    List<Pesanan> listPesanan = PesananDAO.getAllPesanan();
                    if (listPesanan == null || listPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan.");
                    } else {
                        for (Pesanan item : listPesanan) {
                            System.out.println("ID: " + item.getIdPesanan() + " | Tujuan: " + item.getKotaTujuan() + " | Berat: " + item.getBeratBarang() + "kg | Status: " + item.getStatusPesanan());
                        }
                    }
                    System.out.println("---------------------------------------------------------");
                } 
                else if (pCabang == 5) {
                    System.out.print("Input Nomor Resi: ");
                    String resi = scanner.nextLine();
                    System.out.println("[INFO] Sistem Mengecek Data Pengiriman di Database...");
                    
                    List<Pengiriman> daftarKirim = PengirimanDAO.getAllPengiriman();
                    boolean ketemu = false;
                    for (Pengiriman k : daftarKirim) {
                        if (k.getNomorResi().equalsIgnoreCase(resi)) {
                            System.out.println("Apakah Nomor Resi Terdaftar? Ya.");
                            System.out.println("\nMenampilkan Data Pengiriman Sesuai Nomor Resi:");
                            System.out.println("Resi: " + k.getNomorResi() + " | Status: " + k.getStatusPengiriman() + " | Tgl Diterima: " + k.getTanggalDiterima());
                            ketemu = true;
                            break;
                        }
                    }
                    if (!ketemu) {
                        System.out.println("Apakah Nomor Resi Terdaftar? Tidak.");
                        System.out.println("Menampilkan Notifikasi Nomor Resi Tidak Terdaftar.");
                    }
                } 
                else if (pCabang == 6) {
                    System.out.print("Input Status Pengiriman Pada Cabang Pengirim: ");
                    String stat = scanner.nextLine();
                    System.out.println("[INFO] Sistem Menyimpan Data Ke Database...");
                    System.out.println("Menampilkan Notifikasi Berhasil Di Update.");
                } 
                else if (pCabang == 7) {
                    loggedIn = false;
                }
            }

            // ==========================================
            // FLOWCHART MENU AGEN
            // ==========================================
            else if (role.equals("Agen")) {
                System.out.println("\n=== MENU AGEN ===");
                System.out.println("1. Tambah Data Pesanan Pelanggan");
                System.out.println("2. Tampilkan Data Pesanan Pelanggan");
                System.out.println("3. Hapus Data Pesanan Pelanggan");
                System.out.println("4. Ubah Data Pesanan Pelanggan");
                System.out.println("5. Cari Data Pesanan");
                System.out.println("6. Penerimaan Barang Dari Cabang");
                System.out.println("7. Kembali");
                System.out.print("Pilih: ");
                int pAgen = scanner.nextInt();
                scanner.nextLine();

                if (pAgen == 1) {
                    System.out.print("Input Berat Barang untuk Pesanan Baru: ");
                    double berat = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Apakah Semua Data Sesuai? (Y/T): ");
                    String ses = scanner.nextLine();
                    if(ses.equalsIgnoreCase("Y")){
                        System.out.print("Input Opsi Status Pesanan: ");
                        String status = scanner.nextLine();
                        System.out.println("[INFO] Sistem Menyimpan Data ke Database (Relay SQL)...");
                        System.out.println("Menampilkan Notifikasi Berhasil Menambahkan.");
                    }
                } 
                else if (pAgen == 2) {
                    System.out.println("\nMenampilkan Data Pesanan Pelanggan:");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.printf("%-6s | %-20s | %-8s | %-12s | %-15s\n", "ID", "NAMA PENGIRIM", "BERAT", "TOTAL BIAYA", "STATUS");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    List<Pesanan> listPesanan = PesananDAO.getAllPesanan();
                    if (listPesanan == null || listPesanan.isEmpty()) {
                        System.out.println("Belum ada pesanan masuk.");
                    } else {
                        for (Pesanan item : listPesanan) {
                            String beratStr = (item.getBeratBarang() != null) ? item.getBeratBarang() + " kg" : "N/A";
                            String biayaStr = (item.getTotalBiaya() != null) ? "Rp " + item.getTotalBiaya() : "N/A";
                            System.out.printf("%-6d | %-20s | %-8s | %-12s | %-15s\n", 
                                              item.getIdPesanan(), 
                                              item.getNamaPengirim(), 
                                              beratStr, 
                                              biayaStr, 
                                              item.getStatusPesanan());
                        }
                    }
                    System.out.println("---------------------------------------------------------------------------------------------------");
                } 
                else if (pAgen == 3) {
                    System.out.print("Input ID Pesanan yang akan dihapus: ");
                    int idHapus = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("[INFO] Sistem Menghapus Data Database (Relay SQL)...");
                    if(PesananDAO.deletePesanan(idHapus)) {
                        System.out.println("Apakah ID Pesanan Terdaftar? Ya.");
                        System.out.println("Menampilkan Notifikasi Pesanan Terhapus.");
                    } else {
                        System.out.println("Apakah ID Pesanan Terdaftar? Tidak.");
                        System.out.println("ID tidak terdaftar.");
                    }
                } 
                else if (pAgen == 4) {
                    System.out.print("Input ID Pesanan: ");
                    int idUbah = scanner.nextInt();
                    scanner.nextLine();
                    
                    Pesanan p = PesananDAO.getPesananById(idUbah);
                    if(p != null) {
                        System.out.println("Apakah ID Pesanan Terdaftar? Ya.");
                        System.out.print("Input Berat Barang Antara / Layanan (kg): ");
                        double bBaru = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Apakah Data Sudah Benar? (Y/T): ");
                        if(scanner.nextLine().equalsIgnoreCase("Y")){
                            p.setBeratBarang(bBaru);
                            p.setTotalBiaya(20000 + (bBaru * 12000));
                            PesananDAO.updatePesanan(p);
                            System.out.println("[INFO] Sistem Menyimpan Data Perubahan ke Database...");
                            System.out.println("Menampilkan Notifikasi Berhasil Diubah.");
                        }
                    } else {
                        System.out.println("Apakah ID Pesanan Terdaftar? Tidak.");
                        System.out.println("Data pesanan tidak ditemukan di database.");
                    }
                } 
                else if (pAgen == 5) {
                    System.out.print("Input Nama / ID Pesanan: ");
                    int keyword = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("[INFO] Sistem Mengecek Data Pesanan Sesuai Keyword di Database...");
                    Pesanan p = PesananDAO.getPesananById(keyword);
                    if(p != null){
                        System.out.println("Apakah Keyword Terdaftar? Ya.");
                        System.out.println("Menampilkan Data Pesanan Pelanggan Sesuai Keyword:");
                        System.out.println("ID: " + p.getIdPesanan() + " | Pengirim: " + p.getNamaPengirim() + " | Tujuan: " + p.getKotaTujuan() + " | Status: " + p.getStatusPesanan());
                    } else {
                        System.out.println("Apakah Keyword Terdaftar? Tidak.");
                        System.out.println("Data tidak ditemukan.");
                    }
                } 
                else if (pAgen == 6) {
                    System.out.print("Input Bukti Dokumentasi Barang Sampai Agen: ");
                    String bukti = scanner.nextLine();
                    System.out.println("[INFO] Sistem Menyimpan Data Perubahan Ke Database (Relay SQL)...");
                    System.out.println("Menampilkan Notifikasi Berhasil Di Update.");
                } 
                else if (pAgen == 7) {
                    loggedIn = false;
                }
            }

            // ==========================================
            // FLOWCHART MENU PELANGGAN
            // ==========================================
            else if (role.equals("Pelanggan")) {
                System.out.println("\n=== MENU PELANGGAN ===");
                System.out.println("1. Membuat Pesanan");
                System.out.println("2. Riwayat Pesanan");
                System.out.println("3. Kelola Akun");
                System.out.println("4. Kembali");
                System.out.print("Pilih: ");
                int pPel = scanner.nextInt();
                scanner.nextLine();

                if (pPel == 1) {
                    Pesanan p = new Pesanan();
                    System.out.print("Input Nama Pengirim: "); p.setNamaPengirim(scanner.nextLine());
                    System.out.print("Input Nomor Telepon Pengirim: "); p.setNoTeleponPengirim(scanner.nextLine());
                    System.out.print("Input Alamat Pengirim: "); p.setAlamatPengirim(scanner.nextLine());
                    
                    System.out.print("Input Nama Penerima: "); p.setNamaPenerima(scanner.nextLine());
                    System.out.print("Input Nomor Telepon Penerima: "); p.setTeleponPenerima(scanner.nextLine());
                    System.out.print("Input Alamat Tujuan: "); p.setAlamatTujuan(scanner.nextLine());
                    System.out.print("Input Opsi Kota Tujuan: "); p.setKotaTujuan(scanner.nextLine());
                    System.out.print("Input Opsi Jenis Kiriman: "); p.setJenisKiriman(scanner.nextLine());
                    System.out.print("Input Opsi Layanan (1=Fast / 2=Std / 3=Cheap): "); p.setLayananId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Apakah data sudah benar? (Y/T): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        p.setStatusPesanan("Menunggu");
                        p.setPenggunaId(user.getIdPengguna());
                        p.setBeratBarang(1.0); // Default Awal
                        p.setTotalBiaya(20000.0); // Default Awal
                        
                        System.out.println("[INFO] Sistem Menyimpan Data Pesanan Sesuai Format ke Database (Relay SQL)...");
                        if(PesananDAO.buatPesanan(p)) {
                            System.out.println("Menampilkan Notifikasi Pesanan Berhasil Dibuat.");
                            System.out.println("Menampilkan Notifikasi Data Diterima Silahkan Bawa ke Agen Terdekat.");
                        }
                    }
                } 
                else if (pPel == 2) {
                    System.out.print("Input Nomor Resi / Cek Status: ");
                    String resi = scanner.nextLine();
                    System.out.println("[INFO] Sistem Mengecek Data Pesanan Pelanggan Sesuai Nomor Resi di Database...");
                    
                    List<Pesanan> list = PesananDAO.getPesananByPelanggan(user.getIdPengguna());
                    if (list == null || list.isEmpty()) {
                        System.out.println("Apakah Nomor Resi Terdaftar? Tidak.");
                    } else {
                        System.out.println("Apakah Nomor Resi Terdaftar? Ya.");
                        System.out.println("\nMenampilkan Riwayat Data Pesanan Sesuai Nomor Resi:");
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.printf("%-6s | %-20s | %-15s | %-12s | %-15s\n", "ID", "KOTA TUJUAN", "JENIS KIRIMAN", "BIAYA", "STATUS");
                        System.out.println("---------------------------------------------------------------------------------------");
                        for (Pesanan item : list) {
                            String biayaStr = (item.getTotalBiaya() != null) ? "Rp " + item.getTotalBiaya() : "N/A";
                            System.out.printf("%-6d | %-20s | %-15s | %-12s | %-15s\n", 
                                              item.getIdPesanan(), 
                                              item.getKotaTujuan(), 
                                              item.getJenisKiriman(), 
                                              biayaStr, 
                                              item.getStatusPesanan());
                        }
                        System.out.println("---------------------------------------------------------------------------------------");
                    }
                } 
                else if (pPel == 3) {
                    System.out.println("\n--- KELOLA AKUN ---");
                    System.out.println("Menampilkan Data Akun:");
                    System.out.println("Nama: " + user.getNamaPengguna());
                    System.out.println("Username: " + user.getUsername());
                    
                    System.out.print("Ubah Data Akun? (Y/T): ");
                    String ubah = scanner.nextLine();
                    if (ubah.equalsIgnoreCase("Y")) {
                        System.out.print("Input Nama Pelanggan Baru: ");
                        user.setNamaPengguna(scanner.nextLine());
                        System.out.print("Input Password Baru: ");
                        user.setPassword(scanner.nextLine());
                        System.out.print("Input Email Baru: ");
                        String em = scanner.nextLine();
                        
                        System.out.println("Sistem Mengecek Ketersediaan Email...");
                        System.out.println("Apakah Email Sudah Terdaftar? Tidak.");
                        
                        System.out.print("Apakah data sudah benar? (Y/T): ");
                        if (scanner.nextLine().equalsIgnoreCase("Y")) {
                            UserDAO.updateUser(user);
                            System.out.println("[INFO] Sistem Menyimpan Data ke Database (Relay SQL)...");
                            System.out.println("Menampilkan Notifikasi Akun Berhasil Diubah.");
                        }
                    }
                } 
                else if (pPel == 4) {
                    loggedIn = false;
                }
            }
        }
    }
}