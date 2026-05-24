package Tugas2;

public class Menu {
    private String nama;
    private String kategori;
    private int harga;
    private boolean tersedia;

    public Menu(String nama, String kategori, int harga) {
        this.nama     = nama;
        this.kategori = kategori;
        this.harga    = harga;
        this.tersedia = true;
    }

    public String getNama()     { return nama; }
    public String getKategori() { return kategori; }
    public int    getHarga()    { return harga; }
    public boolean isTersedia() { return tersedia; }

    public void setHarga(int harga)         { this.harga    = harga; }
    public void setTersedia(boolean status) { this.tersedia = status; }
}