package Stack;

import java.util.Scanner;
import java.util.Stack;

public class TextEditorSimulator {
    public static void main(String[] args) {
        Stack<String[]> undoStack = new Stack<>(); // Stack untuk undo
        Stack<String[]> redoStack = new Stack<>(); // Stack untuk redo
        Scanner input = new Scanner(System.in);

        String currentNama = "";
        String currentMenu = "";

        while (true) {
            // Tampilkan pesanan saat ini
            System.out.println("\n==============");
            if (currentNama.isEmpty() && currentMenu.isEmpty()) {
                System.out.println("Pesanan anda: -");
            } else {
                System.out.println("Pesanan anda: " + currentNama + " - " + currentMenu);
            }
            System.out.println("==============");

            // Menu pilihan
            System.out.println("Menu:");
            System.out.println("1. Buat Pesanan");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Batalkan Pesanan");
            System.out.println("5. Konfirmasi Pesanan");
            System.out.print("Pilih aksi: ");

            int choice = input.nextInt();
            input.nextLine(); // Buat ambil enter

            switch (choice) {
                case 1:
                    // Simpan kondisi sekarang sebelum perubahan
                    undoStack.push(new String[]{currentNama, currentMenu});
                    redoStack.clear(); // Reset redo karena ada perubahan baru

                    System.out.print("Masukkan nama: ");
                    currentNama = input.nextLine();
                    System.out.print("Masukkan menu: ");
                    currentMenu = input.nextLine();
                    break;

                case 2:
                    // Undo perubahan
                    if (!undoStack.isEmpty()) {
                        redoStack.push(new String[]{currentNama, currentMenu});
                        String[] undoState = undoStack.pop();
                        currentNama = undoState[0];
                        currentMenu = undoState[1];
                        System.out.println("[System] Undo berhasil!");
                    } else {
                        System.out.println("[System] Tidak ada yang bisa di-undo!");
                    }
                    break;

                case 3:
                    // Redo perubahan
                    if (!redoStack.isEmpty()) {
                        undoStack.push(new String[]{currentNama, currentMenu});
                        String[] redoState = redoStack.pop();
                        currentNama = redoState[0];
                        currentMenu = redoState[1];
                        System.out.println("[System] Redo berhasil!");
                    } else {
                        System.out.println("[System] Tidak ada yang bisa di-redo!");
                    }
                    break;

                case 4:
                    // Membatalkan pesanan
                    System.out.println("[System] Pesanan berhasil dibatalkan.");
                    input.close();
                    return;

                case 5:
                    System.out.println("\n[System] Pesanan berhasil dibuat!");
                    if (currentNama.isEmpty() && currentMenu.isEmpty()) {
                        System.out.println("[System] Tidak ada pesanan yang dipilih.");
                    } else {
                        System.out.println("Detail Pesanan:");
                        System.out.println("Nama  : " + currentNama + ", Menu  : " + currentMenu);
                    }
                    System.out.println("[System] Pesanan berhasil dibuat, silahkan lanjutkan pembayaran. Terima kasih!");
                    input.close();
                    return;

                default:
                    System.out.println("[System] Pilihan tidak sesuai, coba lagi ya!");
            }
        }
    }
}
