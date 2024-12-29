package ejercicio.pkg18;

public class Empleado {
    private String codigo;
    private String nombres;
    private int horasTrabajadas;
    private double valorHora;
    private double porcentajeRetencion;

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public int getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }

    public double getPorcentajeRetencion() { return porcentajeRetencion; }
    public void setPorcentajeRetencion(double porcentajeRetencion) { this.porcentajeRetencion = porcentajeRetencion; }

    // Métodos de cálculo
    public double calcularSalarioBruto() {
        return horasTrabajadas * valorHora;
    }

    public double calcularSalarioNeto() {
        double bruto = calcularSalarioBruto();
        double retencion = bruto * (porcentajeRetencion / 100.0);
        return bruto - retencion;
    }
}
