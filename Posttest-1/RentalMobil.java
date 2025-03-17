import java.util.ArrayList;
import java.util.Scanner;

class Mobil {
    int id;
    String nama;
    String deskripsi;
    double hargaSewa;

    public Mobil(int id, String nama, String deskripsi, double hargaSewa) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.hargaSewa = hargaSewa;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nama: " + nama + " | Deskripsi: " + deskripsi + " | Harga Sewa: Rp " + hargaSewa;
    }
}

public class RentalMobil {
    static ArrayList<Mobil> daftarMobil = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== Sistem Manajemen Layanan Rental Mobil =====");
            System.out.println("1. Tambah Mobil");
            System.out.println("2. Lihat Daftar Mobil");
            System.out.println("3. Perbarui Data Mobil");
            System.out.println("4. Hapus Mobil");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    tambahMobil();
                    break;
                case 2:
                    lihatMobil();
                    break;
                case 3:
                    perbaruiMobil();
                    break;
                case 4:
                    hapusMobil();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }

    public static void tambahMobil() {
        System.out.print("Masukkan nama mobil: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan deskripsi mobil: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Masukkan harga sewa per hari: ");
        double hargaSewa = scanner.nextDouble();
        scanner.nextLine();

        daftarMobil.add(new Mobil(nextId++, nama, deskripsi, hargaSewa));
        System.out.println("Mobil berhasil ditambahkan.");
    }

    public static void lihatMobil() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Tidak ada mobil yang tersedia.");
        } else {
            System.out.println("\nDaftar Mobil:");
            for (Mobil mobil : daftarMobil) {
                System.out.println(mobil);
            }
        }
    }

    public static void perbaruiMobil() {
        System.out.print("Masukkan ID mobil yang akan diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Mobil mobil : daftarMobil) {
            if (mobil.id == id) {
                System.out.print("Masukkan nama baru: ");
                mobil.nama = scanner.nextLine();
                System.out.print("Masukkan deskripsi baru: ");
                mobil.deskripsi = scanner.nextLine();
                System.out.print("Masukkan harga sewa baru: ");
                mobil.hargaSewa = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Data mobil berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Mobil dengan ID " + id + " tidak ditemukan.");
    }

    public static void hapusMobil() {
        System.out.print("Masukkan ID mobil yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Mobil mobil : daftarMobil) {
            if (mobil.id == id) {
                daftarMobil.remove(mobil);
                System.out.println("Mobil berhasil dihapus.");
                return;
            }
        }
        System.out.println("Mobil dengan ID " + id + " tidak ditemukan.");
    }
}
