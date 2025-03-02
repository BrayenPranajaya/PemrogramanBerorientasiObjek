import java.util.ArrayList;
import java.util.Scanner;

// Class User sebagai induk
class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

// Class Admin (turunan dari User)
class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void tambahKendaraan(ArrayList<Kendaraan> daftarKendaraan) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama kendaraan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan jenis kendaraan: ");
        String jenis = scanner.nextLine();
        System.out.print("Masukkan harga sewa per hari: ");
        double hargaSewa = scanner.nextDouble();

        daftarKendaraan.add(new Kendaraan(nama, jenis, hargaSewa));
        System.out.println("✅ Kendaraan berhasil ditambahkan!");
    }

    public void hapusKendaraan(ArrayList<Kendaraan> daftarKendaraan) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama kendaraan yang ingin dihapus: ");
        String namaKendaraan = scanner.nextLine();

        boolean ditemukan = false;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNama().equalsIgnoreCase(namaKendaraan)) {
                daftarKendaraan.remove(k);
                System.out.println("✅ Kendaraan berhasil dihapus!");
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("❌ Kendaraan tidak ditemukan!");
        }
    }
}

// Class Penyewa (turunan dari User)
class Penyewa extends User {
    private ArrayList<Transaksi> riwayatTransaksi = new ArrayList<>();

    public Penyewa(String username, String password) {
        super(username, password);
    }

    public void sewaKendaraan(ArrayList<Kendaraan> daftarKendaraan) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== KENDARAAN TERSEDIA ===");
        for (Kendaraan k : daftarKendaraan) {
            if (!k.isDisewa()) k.tampilkanInfo();
        }

        System.out.print("Masukkan nama kendaraan yang ingin disewa: ");
        String namaKendaraan = scanner.nextLine();

        for (Kendaraan k : daftarKendaraan) {
            if (k.getNama().equalsIgnoreCase(namaKendaraan) && !k.isDisewa()) {
                k.setDisewa(true);
                riwayatTransaksi.add(new Transaksi(this, k));
                System.out.println("✅ " + k.getNama() + " berhasil disewa!");
                return;
            }
        }

        System.out.println("❌ Kendaraan tidak ditemukan atau sudah disewa.");
    }

    public void kembalikanKendaraan(ArrayList<Kendaraan> daftarKendaraan) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== KENDARAAN YANG ANDA SEWA ===");
        for (Transaksi t : riwayatTransaksi) {
            if (t.getPenyewa() == this && t.getKendaraan().isDisewa()) {
                System.out.println("• " + t.getKendaraan().getNama());
            }
        }

        System.out.print("Masukkan nama kendaraan yang ingin dikembalikan: ");
        String namaKendaraan = scanner.nextLine();

        for (Transaksi t : riwayatTransaksi) {
            if (t.getKendaraan().getNama().equalsIgnoreCase(namaKendaraan) && t.getKendaraan().isDisewa()) {
                t.getKendaraan().setDisewa(false);
                System.out.println("✅ " + namaKendaraan + " berhasil dikembalikan!");
                return;
            }
        }

        System.out.println("❌ Kendaraan tidak ditemukan dalam daftar sewaan Anda.");
    }

    public void lihatRiwayat() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Tidak ada transaksi.");
            return;
        }

        for (Transaksi t : riwayatTransaksi) {
            System.out.println("• " + t.getKendaraan().getNama() + " | Status: " + (t.getKendaraan().isDisewa() ? "Disewa" : "Dikembalikan"));
        }
    }
}

// Class Kendaraan
class Kendaraan {
    private String nama;
    private String jenis;
    private double hargaSewa;
    private boolean disewa;

    public Kendaraan(String nama, String jenis, double hargaSewa) {
        this.nama = nama;
        this.jenis = jenis;
        this.hargaSewa = hargaSewa;
        this.disewa = false;
    }

    public String getNama() {
        return nama;
    }

    public boolean isDisewa() {
        return disewa;
    }

    public void setDisewa(boolean disewa) {
        this.disewa = disewa;
    }

    public void tampilkanInfo() {
        System.out.println(nama + " | Jenis: " + jenis + " | Harga: Rp" + hargaSewa + " | Status: " + (disewa ? "Disewa" : "Tersedia"));
    }
}

// Class Transaksi
class Transaksi {
    private Penyewa penyewa;
    private Kendaraan kendaraan;

    public Transaksi(Penyewa penyewa, Kendaraan kendaraan) {
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }
}

// Main class
public class RentalKendaraan {
    public static void main(String[] args) {
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        Admin admin = new Admin("admin", "1234");
        Penyewa penyewa = new Penyewa("user", "5678");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan username: ");
        String username = scanner.next();
        System.out.print("Masukkan password: ");
        String password = scanner.next();

        if (admin.login(username, password)) {
            admin.tambahKendaraan(daftarKendaraan);
            admin.hapusKendaraan(daftarKendaraan);
        } else if (penyewa.login(username, password)) {
            penyewa.sewaKendaraan(daftarKendaraan);
            penyewa.kembalikanKendaraan(daftarKendaraan);
            penyewa.lihatRiwayat();
        } else {
            System.out.println("Login gagal!");
        }
    }
}
