import java.awt.Color;
import java.util.ArrayList;

public class Field {
    private int fieldWidth = 10;
    private int fieldHeight = 30; 

    private boolean[][] field;
    private Color[][] fieldColors;

    private Shape currentShape;
    private Shape nextShape;

    public Field(int field_width, int field_height) {
        this.fieldWidth = field_width;
        this.fieldHeight = field_height;

        field = new boolean[field_width][field_height];
        fieldColors = new Color[field_width][field_height];
    }   

    public Shape getNextShape() {
        return nextShape;
    }

    public void addRandomShape() {
        if (nextShape == null) {
            currentShape = Shape.getRandomShape();
            nextShape = Shape.getRandomShape();
        } else {
            currentShape = nextShape;
            nextShape = Shape.getRandomShape();
        }

        currentShape.setSpawnPosition(fieldWidth);
        addShape(currentShape);

        NextFigurePanelWithLabel.instance.setNextFigure(nextShape);

        clearShapePosition();

        if (!checkCollision(0, -1)) {
            addShape(currentShape);
            GameManager.exitGame();
        }

        addShape(currentShape);
    }

    public void moveShape(int x, int y) {
        clearShapePosition();

        if (!checkCollision(x, y)) {
            addShape(currentShape);
            return;
        }

        // Update the shape position
        currentShape.setX(currentShape.getX() + x);
        currentShape.setY(currentShape.getY() - y);

        addShape(currentShape);
    }

    public void rotateShape() {
        clearShapePosition();
        currentShape.rotateRight();

        if (!checkCollision(0, 0)) {
            currentShape.rotateLeft();
        }

        addShape(currentShape);
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public boolean isCellFilled(int row, int col) {
        return field[col][row];
    }

    public Color getCellColor(int row, int col) {
        return fieldColors[col][row];
    }

    public boolean isCurrentShapeLocked()
    {
        clearShapePosition();

        if (!checkCollision(0, -1)) {
            addShape(currentShape);
            return true;
        }

        addShape(currentShape);
        return false;
    }

    public void clearFullLines() {
        ArrayList<Integer> rowsToClear = new ArrayList<Integer>();

        for (int row = fieldHeight - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0; col < fieldWidth; col++) {
                if (!field[col][row]) {
                    full = false;
                    break;
                }
            }

            if (full) rowsToClear.add(row);
        }

        if (!rowsToClear.isEmpty()) {
            int counter = 0;
            for (int row : rowsToClear) {
                moveRowsDown(row + counter);

                counter++;
            }
        }
    }

    private void clearShapePosition() {
        boolean[][] shapeForm = currentShape.getShapeForm();
        int shapeX = currentShape.getX();
        int shapeY = currentShape.getY();

        for (int row = 0; row < shapeForm.length; row++) {
            for (int col = 0; col < shapeForm[0].length; col++) {
                if (shapeForm[row][col]) {
                    field[shapeX + col][row + shapeY] = false;
                    fieldColors[shapeX + col][row + shapeY] = null;
                }
            }
        }
    }

    private void addShape(Shape shape) {
        boolean[][] shapeForm = shape.getShapeForm();
        int shapeX = shape.getX();
        int shapeY = shape.getY();
        Color shapeColor = shape.getShapeColor();

        for (int row = 0; row < shapeForm.length; row++) {
            for (int col = 0; col < shapeForm[0].length; col++) {
                if (shapeForm[row][col]) {
                    field[shapeX + col][row + shapeY] = true;
                    fieldColors[shapeX + col][row + shapeY] = shapeColor;
                }
            }
        }
    }

    private void moveRowsDown(int rowIndex) {
        for (int row = rowIndex; row > 0; row--) {
            for (int col = 0; col < fieldWidth; col++) {
                field[col][row] = field[col][row - 1];
                fieldColors[col][row] = fieldColors[col][row - 1];
            }
        }

        Game.instance.updateScore(100);
    }

    private boolean checkCollision(int changeX, int changeY) {
        boolean[][] shapeForm = currentShape.getShapeForm();
        int shapeX = currentShape.getX();
        int shapeY = currentShape.getY();

        for (int row = 0; row < shapeForm.length; row++) {
            for (int col = 0; col < shapeForm[0].length; col++) {
                if (!shapeForm[row][col]) continue;

                if (col + shapeX + changeX < 0 ||
                    col + shapeX + changeX >= fieldWidth) {
                    return false;
                }

                if (row + shapeY - changeY < 0 ||
                    row + shapeY - changeY >= fieldHeight) {
                    return false;
                }

                if (field[col + shapeX + changeX][row + shapeY - changeY]) {
                    return false;
                }
            }
        }
        return true;
    }
}