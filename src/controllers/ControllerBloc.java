
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelBloc;
import views.ViewBloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ControllerBloc {
    
    ModelBloc modelbloc;
    ViewBloc viewbloc;
   
   
    public ControllerBloc(ModelBloc modelbloc, ViewBloc viewbloc) {
        this.modelbloc = modelbloc;
        this.viewbloc = viewbloc;
        this.viewbloc.jmi_abrir.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar_cifrado.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar.addActionListener(actionlistener);
        this.viewbloc.jmi_decifrar.addActionListener(actionlistener);
        initComponents();
    }
    
    ActionListener actionlistener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewbloc.jmi_abrir) {
                abrirArchivo();
                
            }else if (e.getSource() == viewbloc.jmi_guardar_cifrado) {
                enviarTexto();
                guardarCifrado();
                
            }else if (e.getSource() == viewbloc.jmi_decifrar){
                decifrarArchivo();
            }else if (e.getSource() == viewbloc.jmi_guardar){
                guardarDatos();
            }
        }
        
    };
    public void guardarCifrado() {
    String cadena;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    JFileChooser filtro = new JFileChooser();
    
    
    if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewbloc)) {
        try {
            File file = fileChooser.getSelectedFile();
            FileWriter filewriter = new FileWriter(file, false);
            try(PrintWriter printwriter = new PrintWriter(filewriter)){
                cadena = modelbloc.cifrarDatos(modelbloc.getTexto());
                printwriter.println(cadena);  
            }
            /*
            escritor = new FileWriter(archivo);
            escritor.write(notas.getText());*/
        } catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}
    public void guardarDatos() {
 ;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    
    
    if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewbloc)) {
        try{
             File archivo = fileChooser.getSelectedFile();
             FileWriter filewriter = new FileWriter(archivo,  false);
            
            try(PrintWriter printwriter = new PrintWriter(filewriter)){
                printwriter.println(viewbloc.jta_espacio.getText());
            
            }
            
            
        }catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}
    
    
    public void enviarTexto(){
        modelbloc.setTexto(viewbloc.jta_espacio.getText());
    }
    
    public void abrirArchivo() {
        String cadena;
    JFileChooser jfc = new JFileChooser(); //Se crea un objeto para acceder al filechooser
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//definimos el modo de seleccion para el obejto
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "txt");//se agrega el filtro txt
    jfc.setFileFilter(filtro);
    if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(viewbloc)) { 
        File archivo = jfc.getSelectedFile(); //se le asigna la seleccion del file chooser a la variable archivo

        try {
            FileReader lector = new FileReader(archivo); //definimos una variable lector nulla para despues guardar el espacion en el buffered
            BufferedReader bufferedreader = new BufferedReader(lector);//le pasamos la variable lector al objeto de buffered para reservar espacio
            StringBuilder contenido = new StringBuilder(); 
            while ((cadena = bufferedreader.readLine()) != null) {
                contenido.append(cadena);
                contenido.append("\n");
            }
            viewbloc.jta_espacio.setText(contenido.toString());

        } catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}
    
     public void decifrarArchivo() {
        String cadena;
    JFileChooser jfc = new JFileChooser(); //se crea un objeto para acceder al FILECHOOSER
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//definimos el modo de seleccion para el obejto
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "txt");//se agrega el filtro txt el filtro txt
    jfc.setFileFilter(filtro);
    if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(viewbloc)) { 
        File archivo = jfc.getSelectedFile(); //asignamos la seleccion del file chooser a la variable archivo

        try {
            FileReader lector = new FileReader(archivo); //definimos una variable lector nulla para despues guardar el espacion en el buffered
            BufferedReader bufferedreader = new BufferedReader(lector);//le pasamos la variable lector al objeto de bufferedReader para reservar espacio
           
            StringBuilder contenido = new StringBuilder(); 
            while ((cadena = bufferedreader.readLine()) != null) {
                cadena = modelbloc.decifrarDatos(cadena);
                contenido.append(cadena);
                contenido.append("\n");
            }
            viewbloc.jta_espacio.setText(contenido.toString());

        } catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}

    
    
public void initComponents(){
    viewbloc.setVisible(true);
}
    
    
}
