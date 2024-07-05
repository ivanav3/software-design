/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import ui.controller.Controller;

/**
 *
 * @author HP
 */
public class CheckThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                if (Controller.getInstance().checkServerShutDown()) {
                    System.out.println("Nit klijenta trenutno se zatvara");
                    System.exit(0);
//                    break;
                }
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
