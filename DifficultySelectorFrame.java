import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultySelectorFrame extends JFrame {
    private JButton continueButton;
    private JComboBox<Difficulty> difficultyChooser;

    public DifficultySelectorFrame() {
        setTitle("Choose Difficulty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        continueButton = new JButton("Continue");

        Difficulty[] difficulties = Difficulty.values();
        difficultyChooser = new JComboBox<Difficulty>(difficulties);

        difficultyChooser.setPreferredSize(new Dimension(150, 30));
        continueButton.setPreferredSize(new Dimension(100, 30));

        add(difficultyChooser, BorderLayout.WEST);
        add(continueButton, BorderLayout.EAST);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.startGame();
                Game.instance.setDifficulty((Difficulty)difficultyChooser.getSelectedItem());
                dispose();
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}