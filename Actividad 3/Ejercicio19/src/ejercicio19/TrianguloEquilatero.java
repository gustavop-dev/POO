/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio19;

public class TrianguloEquilatero {
    private double lado;

    // Constructor
    public TrianguloEquilatero(double lado) {
        this.lado = lado;
    }

    // Getter y Setter
    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    // Método para calcular el perímetro
    public double calcularPerimetro() {
        return 3 * lado;
    }

    // Método para calcular la altura
    public double calcularAltura() {
        return (Math.sqrt(3) / 2) * lado;
    }

    // Método para calcular el área
    public double calcularArea() {
        double altura = calcularAltura();
        return (lado * altura) / 2;
    }
}

