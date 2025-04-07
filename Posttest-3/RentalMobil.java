import java.util.ArrayList;
import java.util.Scanner;

public class RentalMobil {
    static ArrayList<Mobil> daftarMobil = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== Sistem Rental Mobil =====");
            System.out.println("1. Tambah Mobil Mewah");
            System.out.println("2. Tambah Mobil Ekonomi");
            System.out.println("3. Lihat Daftar Mobil");
            System.out.println("4. Edit Mobil");
            System.out.println("5. Hapus Mobil");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahMobilMewah();
                    break;
                case 2:
                    tambahMobilEkonomi();
                    break;
                case 3:
                    lihatMobil();
                    break;
                case 4:
                    editMobil();
                    break;
                case 5:
                    hapusMobil();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 6);
    }

    public static void tambahMobilMewah() {
        System.out.print("Nama mobil: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Harga sewa: ");
        double hargaSewa = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Ada WiFi (ya/tidak): ");
        String wifiInput = scanner.nextLine();
        boolean adaWiFi = wifiInput.equalsIgnoreCase("ya");

        System.out.print("Ada TV (ya/tidak): ");
        String tvInput = scanner.nextLine();
        boolean adaTV = tvInput.equalsIgnoreCase("ya");

        daftarMobil.add(new MobilMewah(nextId++, nama, deskripsi, hargaSewa, adaWiFi, adaTV));
        System.out.println("Mobil mewah berhasil ditambahkan.");
    }

    public static void tambahMobilEkonomi() {
        System.out.print("Nama mobil: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Harga sewa: ");
        double hargaSewa = scanner.nextDouble();
        System.out.print("Konsumsi BBM (L/km): ");
        double konsumsiBBM = scanner.nextDouble();
        scanner.nextLine();

        daftarMobil.add(new MobilEkonomi(nextId++, nama, deskripsi, hargaSewa, konsumsiBBM));
        System.out.println("Mobil ekonomi berhasil ditambahkan.");
    }

    public static void lihatMobil() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Tidak ada mobil tersedia.");
        } else {
            for (Mobil mobil : daftarMobil) {
                System.out.println(mobil);
            }
        }
    }

    public static void editMobil() {
        System.out.print("Masukkan ID mobil yang ingin diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Mobil mobilDiedit = null;

        for (Mobil mobil : daftarMobil) {
            if (mobil.getId() == id) {
                mobilDiedit = mobil;
                break;
            }
        }

        if (mobilDiedit == null) {
            System.out.println("Mobil tidak ditemukan.");
            return;
        }

        System.out.print("Nama baru: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi baru: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Harga sewa baru: ");
        double hargaSewa = scanner.nextDouble();
        scanner.nextLine();

        if (mobilDiedit instanceof MobilMewah) {
            System.out.print("Ada WiFi (ya/tidak): ");
            String wifiInput = scanner.nextLine();
            boolean wifi = wifiInput.equalsIgnoreCase("ya");

            System.out.print("Ada TV (ya/tidak): ");
            String tvInput = scanner.nextLine();
            boolean tv = tvInput.equalsIgnoreCase("ya");

            daftarMobil.set(daftarMobil.indexOf(mobilDiedit), new MobilMewah(id, nama, deskripsi, hargaSewa, wifi, tv));
        } else if (mobilDiedit instanceof MobilEkonomi) {
            System.out.print("Konsumsi BBM baru (L/km): ");
            double konsumsi = scanner.nextDouble();
            scanner.nextLine();

            daftarMobil.set(daftarMobil.indexOf(mobilDiedit), new MobilEkonomi(id, nama, deskripsi, hargaSewa, konsumsi));
        }

        System.out.println("Mobil berhasil diperbarui.");
    }

    public static void hapusMobil() {
        System.out.print("Masukkan ID mobil yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Mobil mobilDihapus = null;
        for (Mobil mobil : daftarMobil) {
            if (mobil.getId() == id) {
                mobilDihapus = mobil;
                break;
            }
        }

        if (mobilDihapus != null) {
            daftarMobil.remove(mobilDihapus);
            System.out.println("Mobil berhasil dihapus.");
        } else {
            System.out.println("Mobil tidak ditemukan.");
        }
    }
}

// Superclass
class Mobil {
    private int id;
    private String nama;
    private String deskripsi;
    private double hargaSewa;

    public Mobil(int id, String nama, String deskripsi, double hargaSewa) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.hargaSewa = hargaSewa;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Deskripsi: " + deskripsi + ", Harga Sewa: " + hargaSewa;
    }
}

// Subclass 1
class MobilMewah extends Mobil {
    private boolean adaWiFi;
    private boolean adaTV;

    public MobilMewah(int id, String nama, String deskripsi, double hargaSewa, boolean adaWiFi, boolean adaTV) {
        super(id, nama, deskripsi, hargaSewa);
        this.adaWiFi = adaWiFi;
        this.adaTV = adaTV;
    }

    @Override
    public String toString() {
        return super.toString() + ", WiFi: " + (adaWiFi ? "Ya" : "Tidak") + ", TV: " + (adaTV ? "Ya" : "Tidak");
    }
}

// Subclass 2
class MobilEkonomi extends Mobil {
    private double konsumsiBBM;

    public MobilEkonomi(int id, String nama, String deskripsi, double hargaSewa, double konsumsiBBM) {
        super(id, nama, deskripsi, hargaSewa);
        this.konsumsiBBM = konsumsiBBM;
    }

    @Override
    public String toString() {
        return super.toString() + ", Konsumsi BBM: " + konsumsiBBM + " L/km";
    }
}
