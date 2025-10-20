/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

/**
 *
 * @author lenov
 */
public class DataItem {
    String id;
    String nama;
    String waktu;
    String deskripsi;
    String status;

    public DataItem(String id, String nama, String waktu, String deskripsi, String status) {
        this.id = id;
        this.nama = nama;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.status = status;
    }
}
