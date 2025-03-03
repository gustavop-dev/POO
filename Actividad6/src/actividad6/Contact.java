package actividad6;

public class Contact {
    private String id;
    private String name;
    private String phone;
    
    public Contact(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + name + " | Teléfono: " + phone;
    }
    
    // Formato para guardar en archivo
    public String toFileString() {
        return id + "," + name + "," + phone;
    }
    
    // Crear un contacto desde una línea del archivo
    public static Contact fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new Contact(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}