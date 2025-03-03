package actividad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ContactManagerGUI extends JFrame implements ActionListener {
    private final JTextField nameField, phoneField, idField;
    private final JTextArea displayArea;
    private final JButton createButton, readButton, updateButton, deleteButton, clearButton;
    private final ContactManager contactManager;
    
    public ContactManagerGUI() {
        contactManager = new ContactManager("contacts.txt");
        
        // Configuración de la ventana
        setTitle("Gestor de Contactos - CRUD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("Teléfono:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        createButton = new JButton("Create");
        readButton = new JButton("Read");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        
        createButton.addActionListener(this);
        readButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        buttonPanel.add(createButton);
        buttonPanel.add(readButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        // Área de visualización
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setRows(10); // Establecer un número fijo de filas para asegurar visibilidad
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Disposición principal
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        
        // Cargar contactos al iniciar
        refreshContactDisplay();
    }
    
    private void refreshContactDisplay() {
        try {
            String contacts = contactManager.readAllContacts();
            displayArea.setText(contacts);
            // Asegurarse de que el área de visualización se actualice
            displayArea.setCaretPosition(0);
            displayArea.repaint();
        } catch (IOException e) {
            displayArea.setText("Error al cargar contactos: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        
        try {
            if (e.getSource() == createButton) {
                if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Contact contact = new Contact(id, name, phone);
                contactManager.createContact(contact);
                JOptionPane.showMessageDialog(this, "Contacto creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } 
            else if (e.getSource() == readButton) {
                if (id.isEmpty()) {
                    refreshContactDisplay();
                } else {
                    Contact contact = contactManager.readContact(id);
                    if (contact != null) {
                        nameField.setText(contact.getName());
                        phoneField.setText(contact.getPhone());
                        displayArea.setText(contact.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } 
            else if (e.getSource() == updateButton) {
                if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Contact contact = new Contact(id, name, phone);
                boolean updated = contactManager.updateContact(contact);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Contacto actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
            else if (e.getSource() == deleteButton) {
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                boolean deleted = contactManager.deleteContact(id);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Contacto eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Contacto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource() == clearButton) {
                clearFields();
                refreshContactDisplay();
            }
            
            // Actualizar la lista de contactos después de cualquier operación
            refreshContactDisplay();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}