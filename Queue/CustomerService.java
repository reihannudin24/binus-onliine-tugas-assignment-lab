package Queue;

import java.util.Scanner;

// Class Node untuk merepresentasikan setiap pelanggan dalam antrian
class Node {
    String nama;
    Node next;

    Node(String nama) {
        this.nama = nama;
        this.next = null;
    }
}

// Class Queue untuk mengelola antrian pelanggan
class Queue {
    Node front, rear;

    Queue() {
        front = rear = null; // Antrian awalnya kosong
    }

    // Menambah pelanggan ke antrian
    void enqueue(String nama) {
        Node baru = new Node(nama);
        if (rear == null) {
            // Kalau antrian kosong, front dan rear menunjuk ke pelanggan baru
            front = rear = baru;
        } else {
            // Tambahkan pelanggan baru di belakang antrian
            rear.next = baru;
            rear = baru;
        }
        System.out.println("[System] " + nama + " berhasil bergabung dalam antrean. Silakan menunggu!");
    }

    // Melayani pelanggan (menghapus dari depan antrian)
    void dequeue() {
        if (front == null) {
            System.out.println("[System] Antrian kosong. Tidak ada pelanggan yang bisa dilayani.");
            return;
        }
        System.out.println("[System] Saat ini sedang melayani pelanggan: " + front.nama);
        front = front.next; // Pindahkan front ke pelanggan berikutnya

        if (front == null) {
            rear = null; // Kalau habis, set rear jadi null juga
        }

        // Menampilkan daftar antrian setelah melayani
        display();
    }

    // Menampilkan semua pelanggan dalam antrian
    void display() {
        if (front == null) {
            System.out.println("[System] Saat ini tidak ada pelanggan dalam antrean.");
            return;
        }
        System.out.println("\n===== Pelanggan dalam antrean =====");
        Node temp = front;
        int nomor = 1;
        while (temp != null) {
            System.out.println(nomor + ". " + temp.nama);
            temp = temp.next;
            nomor++;
        }
    }
}

public class CustomerService {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue customerQueue = new Queue(); // Membuat antrian baru
        int pilihan;

        System.out.println("\n===== Selamat Datang di Layanan Customer Service Kantin A =====");

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Daftar Antrian Baru");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Cek Antrian Saat Ini");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = input.nextInt();
            input.nextLine(); // Buat ngambil enter setelah nextInt()

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = input.nextLine();
                    customerQueue.enqueue(nama); // Tambah pelanggan baru
                    break;
                case 2:
                    customerQueue.dequeue(); // Layani pelanggan
                    break;
                case 3:
                    customerQueue.display(); // Tampilkan semua pelanggan
                    break;
                case 4:
                    System.out.println("\n[System] Terima kasih telah menggunakan layanan kami. Sampai jumpa!");
                    break;
                default:
                    System.out.println("[System] Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (pilihan != 4);

        input.close(); // Tutup Scanner
    }
}
