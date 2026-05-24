package Tugas2;

import java.util.Scanner;

public class Main {

    static Menu[] daftarMenu = new Menu[50];
    static int jumlahMenu    = 0;
    static int[]    pesananIndex  = new int[50];
    static int[]    pesananJumlah = new int[50];
    static int      jumlahPesanan = 0;
    static Scanner sc = new Scanner(System.in);
//aray 
    public static void main(String[] args) {
        daftarMenu[jumlahMenu++] = new Menu("Nasi Padang",   "Makanan",  20000);
        daftarMenu[jumlahMenu++] = new Menu("Gulai Cumi",    "Makanan",  23000);
        daftarMenu[jumlahMenu++] = new Menu("Rendang",       "Makanan",  30000);
        daftarMenu[jumlahMenu++] = new Menu("Tuna Bakar",    "Makanan",  27000);
        daftarMenu[jumlahMenu++] = new Menu("Es Teh Manis",  "Minuman",   3000);
        daftarMenu[jumlahMenu++] = new Menu("Es Jeruk",      "Minuman",   4000);
        daftarMenu[jumlahMenu++] = new Menu("Jus Buah Naga", "Minuman",   5000);
        daftarMenu[jumlahMenu++] = new Menu("Kopi",          "Minuman",   3000);

        int pilihanUtama;
        do {
            System.out.println("\n===============================");
            System.out.println("     RESTORAN BALIKAPAPN      ");
            System.out.println("==============================");
            System.out.println("  1. Pesan Makanan             ");
            System.out.println("  2. Manajemen Menu (Pemilik)  ");
            System.out.println("  0. Keluar                    ");
            System.out.println("===============================");
            System.out.print("silahkan Pilih opsi : ");

            while (!sc.hasNextInt()) {
                System.out.print("Input tidak valid! Masukkan angka: ");
                sc.next();
            }
            pilihanUtama = sc.nextInt();

            if (pilihanUtama == 1) {
                menuPelanggan();
            } else if (pilihanUtama == 2) {
                menuManajemen();
            } else if (pilihanUtama != 0) {
                System.out.println("Pilihan tidak tersedia, coba lagi!");
            }

        } while (pilihanUtama != 0);

        System.out.println("Terima kasih telah menggunakan aplikasi kami!");
    }

    public static void menuPelanggan() {
        int pilihan;
        do {
            System.out.println("\n=== MENU PELANGGAN ===");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Mulai Pemesanan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");

            while (!sc.hasNextInt()) {
                System.out.print("Input tidak valid! Masukkan angka: ");
                sc.next();
            }
            pilihan = sc.nextInt();

            if      (pilihan == 1) tampilMenu();
            else if (pilihan == 2) prosesPesanan();
            else if (pilihan != 0) System.out.println("Pilihan tidak ada!");

        } while (pilihan != 0);
    }

        //DFTAR MENU 
    public static void tampilMenu() {
        System.out.println("\n========== DAFTAR MENU ==========");

        System.out.println("\n  [ MAKANAN ]");
        System.out.printf("  %-4s %-18s %s%n", "No.", "Nama", "Harga");
        System.out.println("  " + "-".repeat(35));

        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equals("Makanan")) {
                System.out.printf("  %-4d %-18s Rp %,d%n",
                    (i + 1), daftarMenu[i].getNama(), daftarMenu[i].getHarga());
            }
        }

        System.out.println("\n  [ MINUMAN ]");
        System.out.printf("  %-4s %-18s %s%n", "No.", "Nama", "Harga");
        System.out.println("  " + "-".repeat(35));

        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equals("Minuman")) {
                System.out.printf("  %-4d %-18s Rp %,d%n",
                    (i + 1), daftarMenu[i].getNama(), daftarMenu[i].getHarga());
            }
        }
        System.out.println("=================================");
    }
// PROSES ORDER
    public static void prosesPesanan() {
        jumlahPesanan = 0;

        tampilMenu();
        System.out.println("\n>>> Masukkan NOMOR menu yang ingin dipesan.");
        System.out.println(">>> Ketik 'selesai' untuk mengakhiri pemesanan.\n");

        while (true) {
            System.out.print("Pilih menu (atau 'selesai'): ");
            String inputStr = sc.next();

            if (inputStr.equalsIgnoreCase("selesai")) {
                break;
            }

            int nomorMenu;
            try {
                nomorMenu = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid! Masukkan nomor menu atau 'selesai'.");
                continue;
            }

            if (nomorMenu < 1 || nomorMenu > jumlahMenu) {
                System.out.println("  Nomor menu tidak ada! Silakan pilih nomor yang tersedia.");
                continue;
            }

            System.out.print("  Jumlah: ");
            while (!sc.hasNextInt()) {
                System.out.print("  Input tidak valid! Jumlah: ");
                sc.next();
            }
            int jumlah = sc.nextInt();

            if (jumlah < 1) {
                System.out.println("  Jumlah minimal 1!");
                continue;
            }

            boolean sudahDipesan = false;
            for (int i = 0; i < jumlahPesanan; i++) {
                if (pesananIndex[i] == (nomorMenu - 1)) {
                    pesananJumlah[i] += jumlah;
                    sudahDipesan = true;
                    System.out.println("  Ditambahkan: " + daftarMenu[nomorMenu-1].getNama() + " x" + jumlah);
                    break;
                }
            }

            if (!sudahDipesan) {
                pesananIndex[jumlahPesanan]  = nomorMenu - 1;
                pesananJumlah[jumlahPesanan] = jumlah;
                jumlahPesanan++;
                System.out.println("  Ditambahkan: " + daftarMenu[nomorMenu-1].getNama() + " x" + jumlah);
            }
        }

        if (jumlahPesanan == 0) {
            System.out.println("Tidak ada pesanan. Kembali ke menu.");
            return;
        }

        hitungDanCetakStruk();
    }

   //HITUNG
    
    public static void hitungDanCetakStruk() {

    // Hitung total biaya
        double totalBiaya = 0;
        for (int i = 0; i < jumlahPesanan; i++) {
            int idx = pesananIndex[i];
            int qty = pesananJumlah[i];
            totalBiaya += daftarMenu[idx].getHarga() * qty;
        }

    // Cek diskon
        double diskon = 0;
        String infoDiskon = "";
        if (totalBiaya > 100000) {
            diskon     = totalBiaya * 0.10;
            infoDiskon = "Diskon 10% (total > Rp 100.000)";
        }

     //GRATISAN
        String menuGratis  = "";
        if (totalBiaya > 50000) {
            System.out.println("\nSELAMAT! Anda mendapat BELI 1 GRATIS 1 untuk minuman!");
            System.out.println("Pilih minuman gratis Anda:");

            int no = 1;
            for (int i = 0; i < jumlahMenu; i++) {
                if (daftarMenu[i].getKategori().equals("Minuman")) {
                    System.out.printf("  %d. %-18s Rp %,d%n", no++, daftarMenu[i].getNama(), daftarMenu[i].getHarga());
                }
            }

            int pilihanGratis = 0;
            boolean valid = false;
            while (!valid) {
                System.out.print("Pilih nomor: ");
                while (!sc.hasNextInt()) { sc.next(); }
                pilihanGratis = sc.nextInt();

                int counterMin = 0;
                for (int i = 0; i < jumlahMenu; i++) {
                    if (daftarMenu[i].getKategori().equals("Minuman")) {
                        counterMin++;
                        if (counterMin == pilihanGratis) {
                            menuGratis  = daftarMenu[i].getNama();
                            valid = true;
                            break;
                        }
                    }
                }
                if (!valid) System.out.println("Pilihan tidak valid, coba lagi!");
            }
            System.out.println(menuGratis + " akan ditambahkan GRATIS!");
        }

        // Hitung pajak & total akhir
        double totalSetelahDiskon = totalBiaya - diskon;
        double pajak              = totalSetelahDiskon * 0.10;
        double biayaLayanan       = 20000;
        double totalAkhir         = totalSetelahDiskon + pajak + biayaLayanan;

        // Cetak struk
        System.out.println("\n");
        System.out.println("============================================");
        System.out.println("            STRUK PEMBAYARAN                ");
        System.out.println("           RESTORAN BALIKPAPAN              ");
        System.out.println("============================================");
        System.out.printf( "   %-20s %6s %9s  %n", "ITEM", "QTY", "SUBTOTAL");
        System.out.println("============================================");

        for (int i = 0; i < jumlahPesanan; i++) {
            int idx      = pesananIndex[i];
            int qty      = pesananJumlah[i];
            int harga    = daftarMenu[idx].getHarga();
            int subtotal = harga * qty;

            String nama = daftarMenu[idx].getNama();
            if (nama.length() > 16) nama = nama.substring(0, 16);

            System.out.printf("  %-16s @%6s  %9s  %n",
                nama,
                String.format("Rp%,d", harga),
                String.format("Rp%,d", subtotal));
            System.out.printf("  %38s  %n", "(x" + qty + ")");
        }

        if (!menuGratis.isEmpty()) {
            System.out.println("=================================================");
            System.out.printf("  %-30s %10s  ║%n", menuGratis + " (GRATIS)", "Rp 0");
        }

        System.out.println("=============================================");
        System.out.printf( "  %-28s %12s  %n", "Subtotal",
            String.format("Rp %,d", (int)totalBiaya));

        if (diskon > 0) {
            System.out.printf("  %-28s %12s  %n", infoDiskon,
                String.format("-Rp %,d", (int)diskon));
            System.out.printf("  %-28s %12s  %n", "Setelah Diskon",
                String.format("Rp %,d", (int)totalSetelahDiskon));
        }

        System.out.printf("  %-28s %12s  %n", "Pajak (10%)",
            String.format("Rp %,d", (int)pajak));
        System.out.printf("  %-28s %12s  %n", "Biaya Layanan",
            String.format("Rp %,d", (int)biayaLayanan));
        System.out.println("============================================");
        System.out.printf( "  %-28s %12s  %n", "TOTAL BAYAR",
            String.format("Rp %,d", (int)totalAkhir));
        System.out.println("============================================");
        System.out.println("       Terima kasih atas kunjungan Anda!    ");
    }

    //  menuManajemen
 
    public static void menuManajemen() {
        int pilihan;
        do {
            System.out.println("\n=== MANAJEMEN MENU ===");
            System.out.println("1. Lihat Daftar Menu");
            System.out.println("2. Tambah Menu Baru");
            System.out.println("3. Ubah Harga Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih: ");

            while (!sc.hasNextInt()) {
                System.out.print("Input tidak valid! Masukkan angka: ");
                sc.next();
            }
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1: tampilMenuManajemen(); break;
                case 2: tambahMenu();          break;
                case 3: ubahHargaMenu();       break;
                case 4: hapusMenu();           break;
                case 0: break;
                default: System.out.println("Pilihan tidak ada!");
            }

        } while (pilihan != 0);
    }
    // MenuManajemen
    public static void tampilMenuManajemen() {
        System.out.println("\n--- DAFTAR MENU (MANAJEMEN) ---");
        System.out.printf("%-5s %-20s %-10s %-15s%n", "No.", "Nama", "Kategori", "Harga");
        System.out.println("-".repeat(53));

        for (int i = 0; i < jumlahMenu; i++) {
            String hargaStr = "Rp " + String.format("%,d", daftarMenu[i].getHarga());
            System.out.printf("%-5d %-20s %-10s %-15s%n",
                (i + 1),
                daftarMenu[i].getNama(),
                daftarMenu[i].getKategori(),
                hargaStr);
        }
        System.out.println("-".repeat(53));
    }

    //  tambahMenu
    public static void tambahMenu() {
        // Validasi jumlah: WHILE loop + try-catch menangkap input teks
        int jumlahBaru = 0;
        while (jumlahBaru < 1) {
            System.out.print("\nBerapa menu baru yang ingin ditambahkan? ");
            String inputJumlah = sc.next();
            sc.nextLine();
            try {
                jumlahBaru = Integer.parseInt(inputJumlah);
                if (jumlahBaru < 1) System.out.println("  Minimal 1 menu!");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid! Masukkan angka.");
            }
        }

        for (int i = 0; i < jumlahBaru; i++) {
            System.out.println("\n--- Tambah Menu ke-" + (i + 1) + " ---");

            System.out.print("Nama menu      : ");
            String nama = sc.nextLine().trim();

            String kategori = "";
            while (!kategori.equals("Makanan") && !kategori.equals("Minuman")) {
                System.out.print("Kategori (1.Makanan / 2.Minuman): ");
                String inputKat = sc.nextLine().trim();
                if (inputKat.equals("1") || inputKat.equalsIgnoreCase("makanan")) {
                    kategori = "Makanan";
                } else if (inputKat.equals("2") || inputKat.equalsIgnoreCase("minuman")) {
                    kategori = "Minuman";
                } else {
                    System.out.println("  Input tidak valid! Ketik 1/Makanan atau 2/Minuman.");
                }
            }

            int harga = 0;
            while (harga < 1) {
                System.out.print("Harga (Rp)     : ");
                String inputHarga = sc.nextLine().trim();
                try {
                    harga = Integer.parseInt(inputHarga.replace(",", "").replace(".", ""));
                    if (harga < 1) System.out.println("  Harga harus lebih dari 0!");
                } catch (NumberFormatException e) {
                    System.out.println("  Input tidak valid! Masukkan angka harga.");
                }
            }

            System.out.println("\n  =================================");
            System.out.printf( "  | Nama     : %-18s|%n", nama);
            System.out.printf( "  | Kategori : %-18s|%n", kategori);
            System.out.printf( "  | Harga    : Rp %-14s|%n", String.format("%,d", harga));
            System.out.println("  ==================================");

       
            String konfirmasi = "";
            while (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
                System.out.print("  Tambahkan menu ini? (Ya/Tidak): ");
                konfirmasi = sc.nextLine().trim();
                if (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
                    System.out.println("  Ketik 'Ya' atau 'Tidak'!");
                }
            }

            if (konfirmasi.equalsIgnoreCase("ya")) {
                daftarMenu[jumlahMenu++] = new Menu(nama, kategori, harga);
                System.out.println("  > Menu '" + nama + "' berhasil ditambahkan!");
            } else {
                System.out.println("  > Penambahan '" + nama + "' dibatalkan.");
            }
        }
        System.out.println("\nProses penambahan menu selesai.");
    }

    //  ubahHargaMenu
   
    public static void ubahHargaMenu() {
        tampilMenuManajemen();

        int nomor = 0;
        while (nomor < 1 || nomor > jumlahMenu) {
            System.out.print("Masukkan nomor menu yang akan diubah harganya: ");
            String inputNomor = sc.next();
            sc.nextLine();
            try {
                nomor = Integer.parseInt(inputNomor);
                if (nomor < 1 || nomor > jumlahMenu)
                    System.out.println("  Nomor tidak valid! Pilih antara 1 - " + jumlahMenu);
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid! Masukkan angka nomor menu.");
                nomor = 0; 
            }
        }

        Menu menuDipilih = daftarMenu[nomor - 1];

        System.out.println("\n  Menu dipilih  : " + menuDipilih.getNama());
        System.out.println("  Kategori      : " + menuDipilih.getKategori());
        System.out.println("  Harga saat ini: Rp " + String.format("%,d", menuDipilih.getHarga()));

        int hargaBaru = 0;
        while (hargaBaru < 1) {
            System.out.print("  Harga baru (Rp): ");
            String inputHarga = sc.nextLine().trim();
            try {
                hargaBaru = Integer.parseInt(inputHarga.replace(",", "").replace(".", ""));
                if (hargaBaru < 1) System.out.println("  Harga harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid! Masukkan angka harga.");
            }
        }
        System.out.println("\n  ====================================");
        System.out.printf( "  | Menu      : %-17s|%n", menuDipilih.getNama());
        System.out.printf( "  | Harga lama: Rp %-13s|%n", String.format("%,d", menuDipilih.getHarga()));
        System.out.printf( "  | Harga baru: Rp %-13s|%n", String.format("%,d", hargaBaru));
        System.out.println("  ===================================");
        
        String konfirmasi = "";
        while (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
            System.out.print("  Konfirmasi ubah harga? (Ya/Tidak): ");
            konfirmasi = sc.nextLine().trim();
            if (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
                System.out.println("  Ketik 'Ya' atau 'Tidak'!");
            }
        }

        if (konfirmasi.equalsIgnoreCase("ya")) {
            menuDipilih.setHarga(hargaBaru);
            System.out.println("  > Harga berhasil diubah menjadi Rp " + String.format("%,d", hargaBaru) + "!");
        } else {
            System.out.println("  > Perubahan harga dibatalkan.");
        }
    }
    
    // menu hapus menu 
    
    public static void hapusMenu() {
        tampilMenuManajemen();

        int nomor = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Masukkan nomor menu yang akan dihapus: ");
            String inputNomor = sc.next();
            sc.nextLine();
            try {
                nomor = Integer.parseInt(inputNomor);
                if (nomor < 1 || nomor > jumlahMenu) {
                    System.out.println("  Nomor tidak valid! Pilih antara 1 - " + jumlahMenu);
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid! Masukkan angka nomor menu.");
            }
        }

        Menu menuDipilih = daftarMenu[nomor - 1];

        System.out.println("\n  ====================================");
        System.out.printf( "  | Nama     : %-18s|%n", menuDipilih.getNama());
        System.out.printf( "  | Kategori : %-18s|%n", menuDipilih.getKategori());
        System.out.printf( "  | Harga    : Rp %-14s|%n", String.format("%,d", menuDipilih.getHarga()));
        System.out.println("  | Status   : Akan DIHAPUS       ");
        System.out.println(" ==================================");

        
        String konfirmasi = "";
        while (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
            System.out.print("  Konfirmasi hapus menu ini? (Ya/Tidak): ");
            konfirmasi = sc.nextLine().trim();
            if (!konfirmasi.equalsIgnoreCase("ya") && !konfirmasi.equalsIgnoreCase("tidak")) {
                System.out.println("  Ketik 'Ya' atau 'Tidak'!");
            }
        }

        if (konfirmasi.equalsIgnoreCase("ya")) {
           
            String namaHapus = menuDipilih.getNama();
            int indexHapus = nomor - 1;
            for (int i = indexHapus; i < jumlahMenu - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }
            daftarMenu[jumlahMenu - 1] = null; 
            jumlahMenu--;                      
            System.out.println("  > Menu '" + namaHapus + "' berhasil dihapus dari daftar!");
            System.out.println("  > Sisa menu: " + jumlahMenu + " item.");
        } else {
            System.out.println("  > Penghapusan dibatalkan.");
        }
    }
}