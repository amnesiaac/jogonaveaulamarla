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
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Chefe {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private int cont = 0;
    private int vida;
    private boolean defeated;

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    private List<MisselChefe> misseis;

    public List<MisselChefe> getMisseis() {
        return misseis;
    }
    
    public void atira() {
        this.misseis.add(new MisselChefe(this.x + largura-100, this.y + altura / 2));
    }

    

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    private boolean isVisible;

    private static final int LARGURA_DA_TELA = 500;
    private static final int VELOCIDADE = 1;

    public Chefe(int x, int y) {
        this.isVisible = true;
        this.x = x;
        this.y = y;
        ImageIcon referencia;
        referencia = new ImageIcon("recursos//boss.gif");
        imagem = referencia.getImage();
        misseis = new ArrayList<MisselChefe>();
        this.largura = imagem.getHeight(null);
        this.altura = imagem.getWidth(null);
        this.vida=5;
    }
    
    public void tiraVida(int dano){
        this.vida-=dano;
    }
    public int getVida(){
        return this.vida;
    }
    public void mexer() {
        if (this.y == 1) {
            cont = 1;
        }
        if (this.y == 280) {
            cont = 0;
        }
        if (cont == 1) {
            this.y += 1;
        }
        if (cont == 0) {
            this.y -= 1;
        }
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);

    }

}