/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.*;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.ShellAPI.*;
/**
 *
 * @author Finn Seville
 */
public class notifikasi {
   public static void main(String[] args) throws Exception {
        showNotification("Notifikasi JNA", "Halo dari Java dengan JNA!");
    }
        public static void showNotification(String title, String message) {
        // Inisialisasi struktur NOTIFYICONDATA
        ShellAPI.NOTIFYICONDATA nid = new ShellAPI.NOTIFYICONDATA();
        nid.cbSize = nid.size();
        nid.hWnd = null;
        nid.uID = 1;
        nid.uFlags = ShellAPI.NIF_MESSAGE | ShellAPI.NIF_INFO;
        nid.uCallbackMessage = WinUser.WM_USER + 1;
        nid.szInfoTitle = title;
        nid.szInfo = message;
        nid.dwInfoFlags = ShellAPI.NIIF_INFO;

        // Tambahkan ikon (opsional)
        nid.hIcon = null;

        // Tampilkan balon notifikasi
        Shell32.INSTANCE.Shell_NotifyIcon(ShellAPI.NIM_ADD, nid);
        Shell32.INSTANCE.Shell_NotifyIcon(ShellAPI.NIM_MODIFY, nid);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hapus ikon dari tray
        Shell32.INSTANCE.Shell_NotifyIcon(ShellAPI.NIM_DELETE, nid);
    }
}

