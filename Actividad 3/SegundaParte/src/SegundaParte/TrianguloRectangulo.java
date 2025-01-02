package SegundaParte;

public class TrianguloRectangulo {
    int base;
    int altura;

    public TrianguloRectangulo(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    double calcularArea(){
        return (base*altura/2);
    }

    public TrianguloRectangulo() {
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
    
    

    double calcularPerimetro() {
        return (base + altura + calcularHipotenusa());
    }

    double calcularHipotenusa(){
        return Math.sqrt(Math.pow(base,2) + Math.pow(altura,2));
    }

    String determinarTipoTriangulo(){
        if ((base == altura) && (base== calcularHipotenusa()) && (altura == calcularHipotenusa())){
            return "Es un triángulo equilátero";
        }
        else if ((base != altura) && (base != calcularHipotenusa()) && (altura != calcularHipotenusa())){
            return "Es un triángulo escaleno";
        }
        else{
            return "Es un triángulo isósceles";
        }
    }
}
