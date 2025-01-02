package SegundaParte;

public class Rectangulo {
    int base;
    int altura;

    public Rectangulo(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    
    public Rectangulo() {
    }

    double calcularArea(){
        return base * altura;
    }

    double calcularPerimetro(){
        return (2*base) + (2*altura);
    }
}
