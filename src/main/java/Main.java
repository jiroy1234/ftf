import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main extends JFrame {
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

    public Main() {
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
        float result;
        final int sensorX = 36;
        final int sensorY = 24;
        final int focal = (int) inputFocal.getValue();
        final float width;
        final float height;

        try {
            width = Float.parseFloat(inputWidth.getText());
            height = Float.parseFloat(inputHeight.getText());
        } catch (Exception e) {
            labelError.setText("Invalid width/height input.");
            return;
        }
        if (width == 0 || height == 0) {
            labelError.setText("Width/height cannot be 0.");
            return;
        }
        if (focal == 0) {
            outputFOV.setText("");
            labelError.setText("Focal length cannot be 0.");
            return;
        }

        if (width / height >= (float) sensorX / sensorY) {
            result = (float) ((float) (180 / Math.PI) * 2 * Math.atan((double) (sensorX / width * height) / (2 * focal)));
        } else {
            result = (float) ((float) (180 / Math.PI) * 2 * Math.atan((double) sensorY / (2 * focal)));
        }

        outputFOV.setText("" + result);
        labelError.setText(" ");
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Main();
    }
}