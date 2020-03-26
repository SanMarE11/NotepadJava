//Author: SanMarE11
//Twitter: SanMarE11
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;
public class Notepad extends JFrame{
    private JPanel panel=new JPanel();
    private JTextArea texto=new JTextArea();
    private JScrollPane scroll=new JScrollPane(texto);
    private JButton nuevo=new JButton();
    private JButton abrir=new JButton();
    private JButton guardar=new JButton();
    private int cont=0;
    private JLabel info=new JLabel();
    private JFileChooser jfc=new JFileChooser();
    private final FileNameExtensionFilter filtro=new FileNameExtensionFilter("TXT","txt");
    private FileReader file;
    private BufferedReader fb;
    private FileWriter fw;
    private BufferedWriter bw;
    private String Filee;
    public Notepad(){
        init();
        initPanel();
        initComponents();
        initListen();
    }
    private void init(){
        this.setLayout(null);
        if(jfc.getSelectedFile()==null){
            Filee="Sin Titulo";
        }else{
            Filee=jfc.getName();
        }
        this.setTitle("Notepad / "+Filee+" / SanMarE11");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initPanel(){
        panel.setSize(600,600);
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }
    private void initComponents(){
        scroll.setBounds(10,30,575,530);
        texto.setLineWrap(true);
        panel.add(scroll);
        nuevo.setBounds(10,5,80,20);
        nuevo.setText("Nuevo");
        panel.add(nuevo);
        abrir.setBounds(100,5,80,20);
        abrir.setText("Abrir");
        panel.add(abrir);
        guardar.setBounds(190,5,80,20);
        guardar.setText("Guardar");
        panel.add(guardar);
        info.setText("® SanMarE11");
        info.setBounds(300,5,200,20);
        panel.add(info);
    }
    private void initListen(){
        abrir.addActionListener((ActionEvent ae) -> {
            initAbrir();
        });
        guardar.addActionListener((ActionEvent ae) -> {
            initGuardar();
        });
        nuevo.addActionListener((ActionEvent ae) -> {
            initNuevo();
        });
    }
    private int initAbrir(){
        if(cont==1){
            int opci=JOptionPane.showConfirmDialog(null, "¿Deseas Guardar?","Abrir otro archivo",JOptionPane.YES_NO_CANCEL_OPTION);
            if(opci==0){
                initGuardar();
            }else if(opci==1){
                
            }else{
                return 0;
            }
        }
        cont=0;
        jfc.setFileFilter(filtro);
        jfc.showOpenDialog(texto);
        if(jfc.getSelectedFile()!=null){
            try{
                Filee=jfc.getSelectedFile().getName();
                this.setTitle("Notepad / "+Filee+" / SanMarE11");
                file=new FileReader(jfc.getSelectedFile());
                fb=new BufferedReader(file);
                while(fb.ready()){
                    if(cont==0){
                        texto.setText("");
                    }
                    cont=1;
                    texto.setText(texto.getText()+fb.readLine()+"\n");
                }
                guardar.setEnabled(true);
                fb.close();
                file.close();
                jfc.setSelectedFile(null);
            }catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,"Archivo Erroneo","Error",JOptionPane.ERROR_MESSAGE);
            }catch(IOException ioe){
                
            }
        }else{
            JOptionPane.showMessageDialog(null,"Archivo no seleccionado","Seleccion de archivo",JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    private int initGuardar(){
        jfc.setFileFilter(filtro);
        jfc.showSaveDialog(texto);
        if(jfc.getSelectedFile()!=null){
            try{
                fw=new FileWriter(jfc.getSelectedFile());
                bw=new BufferedWriter(fw);
                bw.write(texto.getText());
                bw.close();
                fw.close();
                this.setTitle("Notepad / "+Filee+" / SanMarE11");   
            }catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,"Archivo Erroneo","Error",JOptionPane.ERROR_MESSAGE);
            }catch(IOException ioe){
            }
        }else{
            JOptionPane.showMessageDialog(null,"Archivo no seleccionado","Seleccion de archivo",JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    private int initNuevo(){
            if(cont==1){
                int opci=JOptionPane.showConfirmDialog(null, "¿Deseas Guardar?","Abrir otro archivo",JOptionPane.YES_NO_CANCEL_OPTION);
                if(opci==0){
                    initGuardar();
                }else if(opci==1){
                    jfc.setSelectedFile(new File(""));
                    Filee="Sin Titulo";
                    this.setTitle("Notepad / "+Filee+" / SanMarE11");
                    texto.setText("");
                }else{
                    return 0;
                }
            }
            cont=0;
            return 0;
    }
    public static void main(String[] args) {
        Notepad ventana=new Notepad();
        ventana.setVisible(true);
    }
}