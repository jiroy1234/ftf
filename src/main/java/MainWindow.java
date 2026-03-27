import javax.swing.*;

public class MainWindow extends JFrame {
    private JPanel panelMain;
    private JTextField inputWidth;
    private JTextField outputFOV;
    private JLabel labelHeight;
    private JLabel labelFocal;
    private JLabel labelWidth;
    private JLabel labelFOV;
    private JSpinner inputFocal;
    private JLabel labelError;
    private JTextField inputHeight;
    private JButton buttonCalculate;

    public MainWindow() {
        setContentPane(panelMain);
        setTitle("Focal to FOV");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        buttonCalculate.addActionListener(actionEvent -> calculate());
    }

    void calculate() {
        try {
            float width = Float.parseFloat(inputWidth.getText());
            float height = Float.parseFloat(inputHeight.getText());
            int focal = (int) inputFocal.getValue();
            float fov = Calculate.toFOV(width,height,focal);
            outputFOV.setText("" + fov);
            labelError.setText(" ");
        } catch (NumberFormatException e) {
            labelError.setText("Invalid width/height input");
        } catch (IllegalArgumentException e) {
            labelError.setText(e.getMessage());
        }
    }
}