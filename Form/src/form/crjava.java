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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;
import java.util.Random;
/**
 *
 * @author ASUS
 */
public class crjava {
    private static final String FILE_PATH = "src/form/data.json";
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
    public static void createData(String nama, String waktu, String deskripsi, String status) {
        List<DataItem> dataList = readData();
        DateTime now = new DateTime();
        DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-D");
        Random random = new Random();
        int randid = random.nextInt(100 ,999);
        String newId = now.toString(format) + "-" + String.valueOf(randid);

        DataItem newData = new DataItem(newId, nama, waktu, deskripsi, status);
        dataList.add(newData);

        writeData(dataList);
        System.out.println("✅ Data berhasil ditambahkan: " + nama);
    }
    
    public static void updateData(String Id, String Unama, String Udesk, String Ustatus, String Utime){
        List<DataItem> datalist = readData();
        boolean found = false;
        for (DataItem item : datalist){
            if (item.id.equals(Id)){
                item.nama = Unama;
                item.status = Ustatus;
                item.waktu = Utime;
                item.deskripsi = Udesk;
                found = true;
                break;
            }
        }
        if (found){
            writeData(datalist);
            System.out.println("Berhasil di perbarui!");
        } else {
            System.err.println("Gagal diperbarui");
        }
    }
    
    public static void deleteData(String[] args) {
        
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
        // Contoh CREATE;

        // Contoh READ
        displayData();
    }
}
