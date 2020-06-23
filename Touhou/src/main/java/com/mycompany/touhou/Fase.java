/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.touhou;

/**
 *
 * @author Bruno
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Nave nave;
    private javax.swing.Timer timer;
    private boolean emJogo;
    private List<Inimigo> inimigos;
    private Inimigo inimigo;
    private Chefe chefe;
    private int cont = 0;
    private boolean cond;
    private long tempinit;
    
    Jogador jogador = new Jogador();
   
    Random gerador = new Random();
    java.util.Timer timer2 = new java.util.Timer();
    
    
    private int[][] coordenadas = {{2380, 29}, {2600, 59}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
    {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
    {920, 300}, {856, 328}, {456, 320}, {2380, 29}, {2600, 59}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
    {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
    {920, 300}, {856, 328}, {456, 320}};

    public Fase() {
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("recursos//novofundo.gif");
        fundo = referencia.getImage();
        timer = new Timer(5, this);
        timer.start();
        emJogo = true;
        nave = new Nave();
        chefe = new Chefe(300, 80);
        inicializaInimigos();
        tempinit = System.currentTimeMillis();
        timer2.scheduleAtFixedRate(tarefa, 0, 1000);
    }

    public void inicializaInimigos() {
        
        inimigos = new ArrayList<Inimigo>();
        for (int i = 0; i < coordenadas.length; i++) {
            inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));

        }
  
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        if (emJogo) {

            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
            List<Missel> misseis = nave.getMisseis();
            for (int i = 0; i < misseis.size(); i++) {
                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo ini = (Inimigo) inimigos.get(i);
                graficos.drawImage(ini.getImagem(), ini.getX(), ini.getY(), this);
            }
            if (inimigos.size() == 0) {
                Chefe c = (Chefe) chefe;
                graficos.drawImage(c.getImagem(), c.getX(), c.getY(), this);
               
            }
            List<MisselChefe> misseisChefe = chefe.getMisseis();
            for (int i = 0; i < misseisChefe.size(); i++) {
                MisselChefe m2 = (MisselChefe) misseisChefe.get(i);
                graficos.drawImage(m2.getImagem(), m2.getX(), m2.getY(), this);
            }

            graficos.setColor(Color.white);
            graficos.drawString("INIMIGOS:" + inimigos.size(), 5, 15);

        } else {
            if((!emJogo)&&(chefe.isDefeated())){
            ImageIcon vitoria = new ImageIcon ("recursos//youwin.png");
            graficos.drawImage(vitoria.getImage(), 0, 0, null);
        }else{
            ImageIcon fimJogo = new ImageIcon("recursos//gameover.jpg");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }
        g.dispose();

    }
    }
    public void checarColisoes() {
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();
            if (formaNave.intersects(formaInimigo)) {
                nave.setIsVisible(false);
                tempInimigo.setIsVisible(false);
                emJogo = false;
            }
        }
        Rectangle formaChefe = chefe.getBounds();
        Rectangle formaMisselChefe;

        if (inimigos.size() == 0) {
            // Rectangle formaChefe = chefe.getBounds();
            if (formaNave.intersects(formaChefe)) {
                nave.setIsVisible(false);
                chefe.setIsVisible(false);
                emJogo = false;
            }
        }
        List<MisselChefe> misseisChefe = chefe.getMisseis();
        for (int i = 0; i < misseisChefe.size(); i++) {
            MisselChefe tempMisselChefe = misseisChefe.get(i);
            tempMisselChefe.setIsVisible(true);
            formaMisselChefe = tempMisselChefe.getBounds();
            if (formaMisselChefe.intersects(formaNave)) {
                tempMisselChefe.setIsVisible(false);
                nave.setIsVisible(false);
                emJogo = false;
            }
        }

        List<Missel> misseis = nave.getMisseis();
        for (int i = 0; i < misseis.size(); i++) {
            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();
            for (int j = 0; j < inimigos.size(); j++) {
                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();
                if (formaMissel.intersects(formaInimigo)) {
                    tempInimigo.setIsVisible(false);
                    tempMissel.setIsVisible(false);

                }

            }
            int life=10;
            if (inimigos.size() == 0) {
                 if (formaMissel.intersects(formaChefe)) {
                    tempMissel.setIsVisible(false);
                    chefe.tiraVida(tempMissel.getDano());
                    if(tempMissel.getDano()==1){
                        tempMissel.setDano(0);
                    }
                    if(chefe.getVida()==0){
                        chefe.setIsVisible(false);
                        chefe.setDefeated(true);
                        emJogo=false;
                        jogador.setTempo(System.currentTimeMillis() - tempinit);
                    }
                }
            }
        }
    }

    TimerTask tarefa = new TimerTask() {

        @Override
        public void run() {
            cond=true;
        }
    };
       
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (chefe.isIsVisible() && inimigos.size() == 0) {
            chefe.mexer();
            if(cond==true){
                chefe.atira();
                cond=false;
            }
         List<MisselChefe> misseisChefe = chefe.getMisseis();
         for (int i = 0; i < misseisChefe.size(); i++) {
         MisselChefe mC = (MisselChefe) misseisChefe.get(i);
         if (mC.isIsVisible()) {
         mC.mexer();
         } else {
         misseisChefe.remove(i);
         }
         }
               List<Missel> misseis = nave.getMisseis();
        for (int i = 0; i < misseis.size(); i++) {
            Missel m = (Missel) misseis.get(i);
            if (m.isIsVisible()) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }
         nave.mexer();
        checarColisoes(); 
         repaint();
        }else{

        List<Missel> misseis = nave.getMisseis();
        for (int i = 0; i < misseis.size(); i++) {
            Missel m = (Missel) misseis.get(i);
            if (m.isIsVisible()) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }

      
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo ini = (Inimigo) inimigos.get(i);
            if (ini.isIsVisible()) {
                ini.mexer();
            } else {
                inimigos.remove(i);
            }
        }

        nave.mexer();
        checarColisoes();
        repaint();
        }
    }

    private class TecladoAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent tecla) {
            int codigo = tecla.getKeyCode();
            if (codigo == KeyEvent.VK_ENTER) {
                emJogo = true;
                nave = new Nave();
                chefe = new Chefe(300, 80);
                inicializaInimigos();
            }
            if ((codigo == KeyEvent.VK_ENTER)&&(chefe.isDefeated())) {
             
                
            }

            nave.KeyPressed(tecla);
        }

        public void keyReleased(KeyEvent tecla) {
            nave.KeyReleased(tecla);
        }

    }

}