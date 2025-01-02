package SegundaParte;

public class Circulo {
    int radio;

    public Circulo(int radio) {
        this.radio = radio;
    }

    public Circulo() {
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
    
    

    double calcularArea(){
        return Math.PI * Math.pow(radio,2);
    }

    double calcularPerimetro(){
        return 2*Math.PI*radio;
    }
}
