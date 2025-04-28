package LinkedList;

import java.util.Scanner;

// Class Mahasiswa untuk menyimpan data mahasiswa
class Mahasiswa {
    String nim;
    String nama;
    int nilai;
    Mahasiswa next;

    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.next = null;
    }
}

// Class LinkedListMahasiswa untuk mengelola daftar mahasiswa
class LinkedListMahasiswa {
    Mahasiswa head;

    public LinkedListMahasiswa() {
        this.head = null; // Awal daftar kosong
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Menambah mahasiswa baru ke akhir list
    public void tambahMahasiswa(String nim, String nama, int nilai) {
        Mahasiswa mahasiswaBaru = new Mahasiswa(nim, nama, nilai);
        if (isEmpty()) {
            head = mahasiswaBaru;
        } else {
            Mahasiswa current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = mahasiswaBaru;
        }
        System.out.println("Mahasiswa " + nama + " berhasil ditambahkan.");
    }

    // Menghapus mahasiswa berdasarkan NIM
    public void hapusMahasiswa(String nim) {
        if (isEmpty()) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }

        if (head.nim.equals(nim)) {
            String namaTerhapus = head.nama;
            head = head.next;
            System.out.println("Mahasiswa dengan NIM " + nim + " (" + namaTerhapus + ") berhasil dihapus.");
            return;
        }

        Mahasiswa current = head;
        Mahasiswa prev = null;
        while (current != null && !current.nim.equals(nim)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        } else {
            String namaTerhapus = current.nama;
            prev.next = current.next;
            System.out.println("Mahasiswa dengan NIM " + nim + " (" + namaTerhapus + ") berhasil dihapus.");
        }
    }

    // Mengupdate nilai mahasiswa
    public void updateNilaiMahasiswa(String nim, int nilaiBaru) {
        if (isEmpty()) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }

        Mahasiswa current = head;
        while (current != null) {
            if (current.nim.equals(nim)) {
                current.nilai = nilaiBaru;
                System.out.println("Nilai mahasiswa dengan NIM " + nim + " (" + current.nama + ") berhasil diupdate menjadi " + nilaiBaru + ".");
                return;
            }
            current = current.next;
        }
        System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
    }

    // Menampilkan daftar seluruh mahasiswa
    public void tampilkanDaftarMahasiswa() {
        if (isEmpty()) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }

        System.out.println("\nDaftar Mahasiswa:");
        System.out.printf("%-10s %-20s %-5s%n", "NIM", "Nama", "Nilai");
        Mahasiswa current = head;
        while (current != null) {
            System.out.printf("%-10s %-20s %-5d%n", current.nim, current.nama, current.nilai);
            current = current.next;
        }
        System.out.println();
    }
}

public class DataMahasiswa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListMahasiswa daftarMahasiswa = new LinkedListMahasiswa();
        int maxNIMLength = 10; // Panjang maksimal NIM

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Update Nilai Mahasiswa");
            System.out.println("4. Tampilkan Daftar Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan NIM: ");
                    String nimBaru = scanner.nextLine();
                    if (nimBaru.length() > maxNIMLength || !nimBaru.matches("\\d+")) {
                        System.out.println("NIM tidak valid. Harus berupa angka dan maksimal " + maxNIMLength + " digit.");
                        break;
                    }
                    System.out.print("Masukkan Nama: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan Nilai: ");
                    int nilaiBaru = scanner.nextInt();
                    scanner.nextLine(); // Buat ngambil enter
                    daftarMahasiswa.tambahMahasiswa(nimBaru, namaBaru, nilaiBaru);
                    break;
                case "2":
                    System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
                    String nimHapus = scanner.nextLine();
                    daftarMahasiswa.hapusMahasiswa(nimHapus);
                    break;
                case "3":
                    System.out.print("Masukkan NIM mahasiswa yang ingin diupdate nilainya: ");
                    String nimUpdate = scanner.nextLine();
                    System.out.print("Masukkan nilai baru: ");
                    int nilaiUpdate = scanner.nextInt();
                    scanner.nextLine(); // Buat ngambil enter
                    daftarMahasiswa.updateNilaiMahasiswa(nimUpdate, nilaiUpdate);
                    break;
                case "4":
                    daftarMahasiswa.tampilkanDaftarMahasiswa();
                    break;
                case "5":
                    System.out.println("Terima kasih!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }
}
