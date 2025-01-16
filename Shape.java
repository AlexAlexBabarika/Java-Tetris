import java.awt.Color;
import java.util.HashMap;

public class Shape {
    private static final HashMap<ShapeType, boolean[][]> SHAPE_FORMS;
    private static final HashMap<ShapeType, Color> SHAPE_COLORS;

    private boolean[][] shapeForm;
    private Color shapeColor;
    private int x, y;

    private int shapeColumns;
    private int shapeRows;
    
    public Shape(ShapeType shapeType) {
        shapeForm = SHAPE_FORMS.get(shapeType); 
        shapeColor = SHAPE_COLORS.get(shapeType);

        shapeColumns = shapeForm[0].length;
        shapeRows = shapeForm.length;
    }

    public static Shape getRandomShape() {
        int randomIndex = (int)(Math.random() * ShapeType.values().length);
        ShapeType randomShapeType = ShapeType.values()[randomIndex];
        return new Shape(randomShapeType);
    }

    public void setSpawnPosition(int fieldWidth) {
        x = (fieldWidth - shapeColumns) / 2;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean[][] getShapeForm() {
        return shapeForm;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void rotateRight() {
        shapeRows = shapeForm.length;
        shapeColumns = shapeForm[0].length;
    
        // Create a new 2D array for the rotated shape
        boolean[][] rotatedShape = new boolean[shapeColumns][shapeRows];
    
        for (int row = 0; row < shapeRows; row++) {
            for (int col = 0; col < shapeColumns; col++) {
                rotatedShape[col][shapeRows - 1 - row] = shapeForm[row][col];
            }
        }
    
        // Update shapeForm to the rotated shape
        shapeForm = rotatedShape;
    }
    
    public void rotateLeft() {
        shapeRows = shapeForm.length;
        shapeColumns = shapeForm[0].length;
    
        // Create a new 2D array for the rotated shape
        boolean[][] rotatedShape = new boolean[shapeColumns][shapeRows];
    
        for (int row = 0; row < shapeRows; row++) {
            for (int col = 0; col < shapeColumns; col++) {
                rotatedShape[shapeColumns - 1 - col][row] = shapeForm[row][col];
            }
        }
    
        // Update shapeForm to the rotated shape
        shapeForm = rotatedShape;
    }
    

    static {
        SHAPE_FORMS = new HashMap<ShapeType, boolean[][]>();

        SHAPE_FORMS.put(ShapeType.Z,
        new boolean[][]{{ false, true,  false },
                        { true,  true,  false },
                        { true,  false, false }}); // Z

        SHAPE_FORMS.put(ShapeType.L,
        new boolean[][]{{ false, true,  false},
                        { false, true,  false},
                        { false, true,  true }});  // L

        SHAPE_FORMS.put(ShapeType.O,
        new boolean[][]{{ true, true },
                        { true, true }});  // O

        SHAPE_FORMS.put(ShapeType.S,
        new boolean[][]{{ false, true,  false },
                        { false, true,  true  },
                        { false, false, true  }});  // S

        SHAPE_FORMS.put(ShapeType.I,
        new boolean[][]{{ false, true,  false, false },
                        { false, true,  false, false },
                        { false, true,  false, false },
                        { false, true,  false, false }});  // I

        SHAPE_FORMS.put(ShapeType.J,
        new boolean[][]{{ false, true,  false},
                        { false, true,  false},
                        { true,  true,  false}});  // J

        SHAPE_FORMS.put(ShapeType.T,
        new boolean[][]{{ false, true,  false },
                        { true,  true,  true  },
                        { false, false, false }}); // T

        SHAPE_COLORS = new HashMap<ShapeType, Color>();

        SHAPE_COLORS.put(ShapeType.Z, Color.RED);
        SHAPE_COLORS.put(ShapeType.L, Color.ORANGE);
        SHAPE_COLORS.put(ShapeType.O, Color.YELLOW);
        SHAPE_COLORS.put(ShapeType.S, Color.GREEN);
        SHAPE_COLORS.put(ShapeType.I, Color.CYAN);
        SHAPE_COLORS.put(ShapeType.J, Color.BLUE);
        SHAPE_COLORS.put(ShapeType.T, Color.MAGENTA);
    };
}