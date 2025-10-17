/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

class DataItem {
    int id;
    String nama;
    String waktu;
    String deskripsi;
    String status;

    public DataItem(int id, String nama, String waktu, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.status = status;
    }
}

/**
 *
 * @author ASUS
 */
public class crjava {
    private static final String FILE_PATH = "data.json";
    private static final Gson gson = new Gson();

    // Fungsi membaca data dari JSON
    public static List<DataItem> readData() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<DataItem>>() {}.getType();
            List<DataItem> data = gson.fromJson(reader, listType);
            return data != null ? data : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Gagal membaca file JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Fungsi menulis data ke JSON
    public static void writeData(List<DataItem> data) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Gagal menulis file JSON: " + e.getMessage());
        }
    }

    // Fungsi CREATE: menambah data baru
    public static void createData(String nama, String waktu, String deskripsi) {
        List<DataItem> dataList = readData();
        int newId = dataList.isEmpty() ? 1 : dataList.get(dataList.size() - 1).id + 1;

        DataItem newData = new DataItem(newId, nama, waktu, deskripsi);
        dataList.add(newData);

        writeData(dataList);
        System.out.println("✅ Data berhasil ditambahkan: " + nama);
    }

    // Menampilkan semua data
    public static void displayData() {
        List<DataItem> dataList = readData();
        if (dataList.isEmpty()) {
            System.out.println("📂 Tidak ada data.");
        } else {
            for (DataItem item : dataList) {
                System.out.println("ID: " + item.id);
                System.out.println("Nama: " + item.nama);
                System.out.println("Waktu: " + item.waktu);
                System.out.println("Deskripsi: " + item.deskripsi);
                System.out.println("-------------------------");
            }
        }
    }

    public static void main(String[] args) {
        // Contoh CREATE
        createData("Belajar JSON CRUD", "2025-10-17 22:00", "Membuat fungsi CRUD di Java");

        // Contoh READ
        displayData();
    }
}
