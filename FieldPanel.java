import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class FieldPanel extends JPanel implements KeyListener {
    private final int CELL_SIZE_PX = 30;
    private Field field;
    private Timer timer;

    public FieldPanel(Field field) {
        this.field = field;

        setPreferredSize(new Dimension(this.field.getFieldWidth() * CELL_SIZE_PX,
                                       this.field.getFieldHeight() * CELL_SIZE_PX));
        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(this);
        
        timer = new Timer(1000, e -> gameTick());
        timer.start();
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < field.getFieldHeight(); row++) {
            for (int col = 0; col < field.getFieldWidth(); col++) {
                if (field.isCellFilled(row, col)) {
                    g.setColor(field.getCellColor(row, col));
                    g.fillRect(col * CELL_SIZE_PX, row * CELL_SIZE_PX, CELL_SIZE_PX, CELL_SIZE_PX);
                }

                g.setColor(Color.DARK_GRAY);
                g.drawRect(col * CELL_SIZE_PX, row * CELL_SIZE_PX, CELL_SIZE_PX, CELL_SIZE_PX);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Process input based on key events
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> field.moveShape(-1, 0);
            case KeyEvent.VK_RIGHT -> field.moveShape(1, 0);
            case KeyEvent.VK_DOWN -> field.moveShape(0, -1);
            case KeyEvent.VK_UP -> field.rotateShape();
        }

        repaint(); 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void setNewDelay(int delay) {
        timer.setDelay(delay);
    }

    private void gameTick() {
        if (field.isCurrentShapeLocked()) {
            field.clearFullLines();
            field.addRandomShape();
            repaint();

            return;
        }
        
        field.moveShape(0, -1);
        repaint();
    }
}
