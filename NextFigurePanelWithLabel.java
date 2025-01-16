import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NextFigurePanelWithLabel extends JPanel {
    public static NextFigurePanelWithLabel instance;

    private final int CELL_SIZE_PX = 30;
    private final int PANEL_WIDTH_CELLS = 4;
    private final int PANEL_HEIGHT_CELLS = 4;
    private Color GRID_COLOR = Color.DARK_GRAY;
    private JLabel label;
    private Shape nextShape;

    public NextFigurePanelWithLabel() {
        setLayout(new BorderLayout());

        label = new JLabel("Next Figure", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(Color.DARK_GRAY);
        add(label, BorderLayout.NORTH);

        NextFigurePanel nextFigurePanel = new NextFigurePanel();
        add(nextFigurePanel, BorderLayout.CENTER);
        
        instance = this;
    }

    public void setNextFigure(Shape shape) {
        nextShape = shape;
        repaint();
    }

    private class NextFigurePanel extends JPanel {
        public NextFigurePanel() {
            setPreferredSize(new Dimension(PANEL_WIDTH_CELLS * CELL_SIZE_PX, PANEL_HEIGHT_CELLS * CELL_SIZE_PX));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawShape(g);
            drawGrid(g);
        }
        
        private void drawGrid(Graphics g) {
            g.setColor(GRID_COLOR);
            for (int width = 0; width < PANEL_WIDTH_CELLS; width++) {
                for (int height = 0; height < PANEL_HEIGHT_CELLS; height++) {
                    g.drawRect(width * CELL_SIZE_PX, height * CELL_SIZE_PX, CELL_SIZE_PX, CELL_SIZE_PX);
                }
            }
        }
        
        private void drawShape(Graphics g) {
            if (nextShape == null) return;
        
            boolean[][] shapeForm = nextShape.getShapeForm();
            Color nextShapeColor = nextShape.getShapeColor();
        
            for (int row = 0; row < shapeForm.length; row++) {
                for (int col = 0; col < shapeForm[row].length; col++) {
                    if (shapeForm[row][col]) {
                        g.setColor(nextShapeColor);
                        g.fillRect(col * CELL_SIZE_PX, row * CELL_SIZE_PX, CELL_SIZE_PX, CELL_SIZE_PX);
                    }
                }
            }
        }
    }
}
