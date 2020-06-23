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
import javax.swing.ImageIcon;

public class Missel {
    private Image imagem;
    private int x,y,largura,altura;
    private boolean isVisible;
    private int dano;

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
            
    private static final int Altura_Da_Tela=400;
    private static final int VELOCIDADE=5;
    
    public Missel(int x ,int y){
        this.isVisible=true;
        this.x=x;
        this.y=y;
        ImageIcon referencia=new ImageIcon("recursos//missel.png");
        imagem=referencia.getImage();
        this.altura=imagem.getHeight(null);
        this.largura=imagem.getWidth(null);
        this.dano=1;
    }
    public void mexer(){
        this.x+=VELOCIDADE;
        if(this.x>=Altura_Da_Tela){
            isVisible=false;
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
        public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

        public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura); 
        
    }
    
}
