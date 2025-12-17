package jiroy1234;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel panelMain;
    private JButton buttonCalculate;
    private JTextField widthInput;
    private JTextField fovOutput;
    private JSlider focalSlider;
    private JLabel heightLabel;
    private JLabel focalLabel;
    private JLabel widthLabel;
    private JLabel fovLabel;
    private JSpinner focalInput;
    private JLabel errorLabel;
    private JTextField heightInput;

    public Main() {
        setContentPane(panelMain);
        setTitle("Focal to FOV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setLocationRelativeTo(null);
        setVisible(true);

        focalInput.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                focalSlider.setValue((int)focalInput.getValue());
            }
        });
        focalSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                focalInput.setValue(focalSlider.getValue());
            }
        });

        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double result;
                final int sensorX = 36;
                final int sensorY = 24;
                final float sensor = (float) sensorX / sensorY;
                final int focal = (int) focalInput.getValue();
                final float width;
                final float height;

                try {
                    width = Float.parseFloat(widthInput.getText());
                    height = Float.parseFloat(heightInput.getText());
                } catch (NumberFormatException e) {
                    errorLabel.setText("Invalid width/height input.");
                    return;
                }

                if (focal == 0) {
                    errorLabel.setText("Specify a focal value other than 0.");
                    return;
                }
                if (width == 0 || height == 0) {
                    errorLabel.setText("Specify a display width/height value other than 0.");
                    return;
                }

                if (width/height >= sensor) {
                    result = (180/Math.PI) * 2 * Math.atan((double) (sensorX / width * height) / (2 * focal));
                } else {
                    result = (180/Math.PI) * 2 * Math.atan((double) sensorY / (2 * focal));
                }

                fovOutput.setText("" + result);
                errorLabel.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
