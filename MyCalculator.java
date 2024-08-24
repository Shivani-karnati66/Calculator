import java.awt.*;
import java.awt.event.*;
abstract class Operation {
    public abstract int calculate(int a, int b);
}
class Addition extends Operation {
    public int calculate(int a, int b) {
        return a + b;
    }
}
class Subtraction extends Operation {
    public int calculate(int a, int b) {
        return a - b;
    }
}
class Multiplication extends Operation {
    public int calculate(int a, int b) {
        return a * b;
    }
}
class Division extends Operation {
    public int calculate(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
class CalculatorUI {
    private Frame f;
    private TextField tf;
    private Panel p;
    private Button[] buttons;
    private GridLayout g;
    private int num1, num2;
    private Operation currentOperation;
    public CalculatorUI() {
        f = new Frame("Calculator");
        tf = new TextField(20);
        p = new Panel();
        g = new GridLayout(4, 4, 10, 20);
        buttons = new Button[17];
        String[] buttonLabels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "C", "=", "."};
        for (int i = 0; i < 17; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].addActionListener(new ButtonClickListener());
            p.add(buttons[i]);
        }
        p.setLayout(g);
        f.add(tf);
        f.add(p);
        f.setLayout(new FlowLayout());
        f.setSize(300, 300);
        f.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                tf.setText(tf.getText() + command);
            } else if (command.equals("+")) {
                prepareOperation(new Addition());
            } else if (command.equals("-")) {
                prepareOperation(new Subtraction());
            } else if (command.equals("*")) {
                prepareOperation(new Multiplication());
            } else if (command.equals("/")) {
                prepareOperation(new Division());
            } else if (command.equals("=")) {
                executeOperation();
            } else if (command.equals("C")) {
                tf.setText("");
            }
        }
        private void prepareOperation(Operation operation) {
            num1 = Integer.parseInt(tf.getText());
            tf.setText("");
            currentOperation = operation;
        }
        private void executeOperation() {
            num2 = Integer.parseInt(tf.getText());
            try {
                int result = currentOperation.calculate(num1, num2);
                tf.setText(String.valueOf(result));
            } catch (ArithmeticException ex) {
                tf.setText("Error");
            }
        }
    }
}
public class MyCalculator {
    public static void main(String[] args) {
        new CalculatorUI();
    }
}