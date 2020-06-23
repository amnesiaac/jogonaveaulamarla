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

public class Inimigo {
    private Image imagem;

    public static int getContador() {
        return contador;
    }
    private int x,y;
    private int largura,altura;
    private static int contador;

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    private boolean isVisible;
    
    private static final int LARGURA_DA_TELA=500;
    private static final int VELOCIDADE=2;
    
    public Inimigo(int x ,int y){
        this.isVisible=true;
        this.x=x;
        this.y=y;
        ImageIcon referencia;
        if(contador++%3==0){
             referencia=new ImageIcon("recursos//inimigo_2.gif");
        }else{
            referencia=new ImageIcon("recursos//inimigo_1.gif");
        }
        
        imagem=referencia.getImage();
        this.largura=imagem.getHeight(null);
        this.altura=imagem.getWidth(null);
        
    }
    
    
    public void mexer(){
        if(this.x<0){
            this.x=LARGURA_DA_TELA;
        }else{
            this.x-=VELOCIDADE;
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
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura); 
        
    }
    
}
