package PerformanceTest;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class PerformanceTest {
    public static void main(String[] args) {
        // Uji performa Queue
        Queue<String> queue = new LinkedList<>(); // Menggunakan LinkedList sebagai implementasi Queue
        long startQueue = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            queue.offer("Pelanggan" + i);
        }
        long endQueue = System.nanoTime();
        long timeQueue = endQueue - startQueue;

        // Uji performa Stack
        Stack<String[]> undoStack = new Stack<>();
        long startStack = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            undoStack.push(new String[]{"Nama" + i, "Menu" + i});
        }
        long endStack = System.nanoTime();
        long timeStack = endStack - startStack;

        // Uji performa LinkedList Mahasiswa
        LinkedList<String> mahasiswaList = new LinkedList<>();
        long startLinkedList = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            mahasiswaList.add("NIM" + i + " - Nama" + i);
        }
        long endLinkedList = System.nanoTime();
        long timeLinkedList = endLinkedList - startLinkedList;

        // Print hasil perbandingan waktu dalam milisecond (ms)
        System.out.println("\n===== Hasil Perbandingan Waktu Eksekusi =====");
        System.out.println("Queue (Tambah 1000 pelanggan): " + (timeQueue / 1_000_000.0) + " ms");
        System.out.println("Stack (Tambah 1000 histori): " + (timeStack / 1_000_000.0) + " ms");
        System.out.println("Linked List (Tambah 1000 mahasiswa): " + (timeLinkedList / 1_000_000.0) + " ms");
    }
}
