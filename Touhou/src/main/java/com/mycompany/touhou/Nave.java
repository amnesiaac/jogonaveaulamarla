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
import javax.swing.ImageIcon;

public class Nave {
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private boolean isVisible;

   
    
    private List<Missel> misseis;
    private int altura=0,largura=0;
    
    public Nave(){
        ImageIcon referencia=new ImageIcon("recursos//prot.gif");
        imagem=referencia.getImage();
      //  referencia.setImage(referencia.getImage().getScaledInstance(50, 50, 100))
        this.altura=imagem.getHeight(null);
        this.largura=imagem.getWidth(null);
        misseis=new ArrayList<Missel>(); 
        this.x=100;
        this.y=100;
        
    }
    public void mexer(){      
        x+=dx;
        y+=dy;
        if(this.x<1){
            x=1;
        }
        if(this.x>400){
            x=400;
        }
        if(this.y<1){
            y=1;
        }
        if(this.y>338){
            y=338;
        }
    }

    public List<Missel> getMisseis() {
        return misseis;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
     public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public void KeyPressed(KeyEvent tecla){
        int codigo=tecla.getKeyCode();
        
        
        if(codigo==KeyEvent.VK_UP){
            dy=-3;
        }
        if(codigo==KeyEvent.VK_DOWN){
            dy=3;
        }
        if(codigo==KeyEvent.VK_LEFT){
            dx=-3;
        }
        if(codigo==KeyEvent.VK_RIGHT){
            dx=3;
        }
        if(codigo==KeyEvent.VK_Z){
            atira();
        }
        
    }
    public void KeyReleased(KeyEvent tecla){
        int codigo=tecla.getKeyCode();
        
        
        
        if (codigo==KeyEvent.VK_UP){
            dy=0;
        }
        if (codigo==KeyEvent.VK_DOWN){
            dy=0;
        }
        if (codigo==KeyEvent.VK_LEFT){
            dx=0;
        }
        if (codigo==KeyEvent.VK_RIGHT){
            dx=0;
        }
        
        
    }
    public void atira(){
        this.misseis.add(new Missel(this.x+ largura,this.y+altura/2));
    }
    
        public Rectangle getBounds(){
        return new Rectangle(x,y,largura-5,altura-10); 
        
    }
    
}
