import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LembreteAgua extends JFrame {
    private JTextField tempoField;
    private JButton iniciarButton;
    private Timer timer;
    
    public LembreteAgua() {
        setTitle("Lembrete de Beber Água");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centralizar
        
        tempoField = new JTextField(5);
        iniciarButton = new JButton("iniciar");
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Tempo (minutos):"));
        panel.add(tempoField);
        panel.add(iniciarButton);
        
        add(panel);
        
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarLembrete();
            }
        });
    }
    
    private void iniciarLembrete(){
        try {
            int minutos = Integer.parseInt(tempoField.getText());
            long intervalo = minutos * 60 * 1000L;  
            
            if (timer != null) {
                timer.cancel();
            }
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Hora de Beber água!", "Lembrete!", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
                
            }, intervalo, intervalo);
                 JOptionPane.showMessageDialog(this, "Lembrete ativado com intervalo de " + minutos + " minuto(s).");
                 
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LembreteAgua().setVisible(true);
        });
            
    }
}