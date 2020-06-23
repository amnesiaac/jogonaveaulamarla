package com.mycompany.touhou;

import javax.swing.JFrame;

/**
 *
 * @author Bruno
 */
 public class Gamestart extends JFrame {
    public static void main(String[] args) {
       JFrame Game = new JFrame();
        Game.add(new Fase()); 
        Game.setTitle("Gangue dos Weebs");
        Game.setSize(500,400);
        Game.setResizable(false);
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.setLocationRelativeTo(null);
        Game.setVisible(true);
    }
 }