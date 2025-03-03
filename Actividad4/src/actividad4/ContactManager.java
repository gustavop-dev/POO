package actividad4;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ContactManager {
    private final String fileName;
    
    public ContactManager(String fileName) {
        this.fileName = fileName;
        // Crear el archivo si no existe
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    
    // CREATE - Crear un nuevo contacto
    public void createContact(Contact contact) throws IOException {
        // Verificar si ya existe un contacto con ese ID
        Contact existingContact = readContact(contact.getId());
        if (existingContact != null) {
            throw new IOException("Ya existe un contacto con el ID: " + contact.getId());
        }
        
        // Añadir el nuevo contacto al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(contact.toFileString());
            writer.newLine();
        }
    }
    
    // READ - Leer un contacto por ID
    public Contact readContact(String id) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromString(line);
                if (contact != null && contact.getId().equals(id)) {
                    return contact;
                }
            }
        }
        return null;
    }
    
    // READ ALL - Leer todos los contactos
    public String readAllContacts() throws IOException {
        StringBuilder result = new StringBuilder();
        File file = new File(fileName);
        
        // Verificar si el archivo existe y no está vacío
        if (!file.exists() || file.length() == 0) {
            return "No hay contactos registrados.";
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromString(line);
                if (contact != null) {
                    result.append(contact.toString()).append("\n");
                }
            }
        }
        
        // Si no se encontraron contactos válidos
        if (result.length() == 0) {
            return "No hay contactos registrados o el formato de archivo es incorrecto.";
        }
        
        return result.toString();
    }
    
    // UPDATE - Actualizar un contacto existente
    public boolean updateContact(Contact updatedContact) throws IOException {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromString(line);
                if (contact != null && contact.getId().equals(updatedContact.getId())) {
                    lines.add(updatedContact.toFileString());
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        }
        
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        
        return found;
    }
    
    // DELETE - Eliminar un contacto
    public boolean deleteContact(String id) throws IOException {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromString(line);
                if (contact != null && contact.getId().equals(id)) {
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        }
        
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        
        return found;
    }
}