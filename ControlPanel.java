import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControlPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Laberinto laberinto;
    private JButton resetMaze;
    private JSlider slider;
    private JTextArea notesArea;
    private JRadioButton randomRB,
    						customRB;
    private JPanel randomJP,
    				   customJP;
    private JComboBox<String> wallOptions;
    private String archivo;
    private JFileChooser fc;
    
    public ControlPanel(){
        super();
        this.setPreferredSize(new Dimension(400, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.resetMaze = new JButton("Iniciar nueva simulacion");
        
        this.resetMaze.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if(ControlPanel.this.laberinto.getState() != 4){
                    if(ControlPanel.this.randomRB.isSelected()){
                        // Cambiar la aglomeracion de bloques
                        switch((String)ControlPanel.this.wallOptions.getSelectedItem()){
                        case "60%":
                            ControlPanel.this.laberinto.setWallAglomeration(4);
                            break;
                        case "70%":
                            ControlPanel.this.laberinto.setWallAglomeration(3);
                            break;
                        case "50%":
                            ControlPanel.this.laberinto.setWallAglomeration(5);
                            break;
                        }
                        ControlPanel.this.laberinto.reset();
                    }else {
                    		ControlPanel.this.laberinto.reset(ControlPanel.this.archivo);
                    }
                }
            }
        });
        this.slider = new JSlider(SwingConstants.HORIZONTAL, 0, 150, 75);
        this.slider.setPaintTicks(true);    
        this.slider.setPaintLabels(true);   
        this.slider.setMajorTickSpacing(25);
        this.slider.setMinorTickSpacing(5);
        this.slider.setBackground(Color.LIGHT_GRAY);
        this.slider.addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent e) {
                ControlPanel.this.laberinto.setVelocidad(ControlPanel.this.slider.getValue());
            }
        });
        JLabel noteLabel = new JLabel("Instrucciones:");
        noteLabel.setFont(new Font("normal", Font.BOLD, 20));
        this.notesArea = new JTextArea(5, 30);
        // Radio Buttons
        this.randomRB = new JRadioButton("Laberinto generado aleatoriamente", true);
        this.customRB= new JRadioButton("Importar laberinto");
        this.customRB.setBackground(Color.LIGHT_GRAY);
        this.randomRB.setBackground(Color.LIGHT_GRAY);
        ButtonGroup bg = new ButtonGroup();
        this.randomRB.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.randomJP.setVisible(true);
                ControlPanel.this.customJP.setVisible(false);
                ControlPanel.this.resetMaze.setEnabled(true);
            }
        });
        this.customRB.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                ControlPanel.this.randomJP.setVisible(false);
                ControlPanel.this.customJP.setVisible(true);
                ControlPanel.this.resetMaze.setEnabled(false);
            }
        });
        bg.add(this.customRB);
        bg.add(this.randomRB);
        // Panel de generacion random
        this.randomJP = new JPanel();
        this.randomJP.setPreferredSize(new Dimension(400, 100));
        String[] wallNumber = {"70%", "60%", "50%"};
        this.wallOptions = new JComboBox<String>(wallNumber);
        this.wallOptions.setSelectedIndex(1);
        this.randomJP.add(new JLabel("Porcentaje de aglomeracion de paredes en el laberinto"));
        this.randomJP.add(this.wallOptions);
        this.randomJP.setBackground(Color.LIGHT_GRAY);
        
        //Laberinto importado
        this.customJP = new JPanel();
        this.customJP.setPreferredSize(new Dimension(400, 100));
        this.customJP.setVisible(false);    // Originalmente empieza no visible
        this.customJP.add(new JLabel("Subir archivo con laberinto .txt"));
        this.customJP.add(new JLabel("Debe tener medidas 20x20 y no tener errores"));
        JButton chooseFile = new JButton("Selecciona un archivo");
        chooseFile.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                int returnVal = ControlPanel.this.fc.showOpenDialog(ControlPanel.this);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    ControlPanel.this.archivo = ControlPanel.this.fc.getSelectedFile().toString();
                    ControlPanel.this.resetMaze.setEnabled(true);
                    ControlPanel.this.laberinto.reset(ControlPanel.this.archivo);
                }
                
            }
        });
        this.fc = new JFileChooser(new File(System.getProperty("user.dir")));
        this.fc.setFileFilter(new FileNameExtensionFilter("text file", "txt"));
        
        this.customJP.add(chooseFile);
        this.customJP.setBackground(Color.LIGHT_GRAY);
        
        // Panel de velocidad
        JPanel velocityPanel = new JPanel();
        velocityPanel.setPreferredSize(new Dimension(300, 100));
        JLabel velLabel = new JLabel("Cambiar velocidad en milisegundos:");
        velLabel.setFont(new Font("normal", Font.BOLD, 16));
        velocityPanel.setBackground(Color.LIGHT_GRAY);
        velocityPanel.add(velLabel);
        velocityPanel.add(this.slider);
        
        this.add(noteLabel);
        this.add(this.notesArea);
        this.add(this.randomRB);
        this.add(this.customRB);
        this.add(this.randomJP);
        this.add(this.customJP);
        this.add(this.resetMaze);
        this.add(velocityPanel);
    }
    
    public void setNotesText(String text){
        this.notesArea.setText(text);
    }
    
    public void addLaberinto(Laberinto laberinto){
        this.laberinto = laberinto;
    }
}
