import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class PaintApplication extends JFrame {
    private List<Shape> shapes;
    private List<Color> shapeColors;
    private int currentColorIndex;
    private int currentShapeType;
    private Shape currentShape;

    public PaintApplication() {
        shapes = new ArrayList<>();
        shapeColors = new ArrayList<>();
        currentColorIndex = 0;
        currentShapeType = 0; // 0: Line, 1: Rectangle, 2: Oval

        setTitle("Simple Paint Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton colorButton = new JButton("Change Color");
        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);

        Canvas canvas = new Canvas();
        canvas.addMouseListener(new DrawingMouseListener());
        canvas.addMouseMotionListener(new DrawingMouseListener());

        colorButton.addActionListener(e -> changeColor());
        undoButton.addActionListener(e -> undo());
        redoButton.addActionListener(e -> redo());

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    private void changeColor() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK};
        currentColorIndex = (currentColorIndex + 1) % colors.length;
    }

    private void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            shapeColors.remove(shapeColors.size() - 1);
            repaint();
        }
    }

    private void redo() {
        // Implement redo functionality based on your specific requirements.
        // This is a simple example, and redo might involve storing undo actions.
    }

    private class DrawingMouseListener extends MouseAdapter {
        private int startX, startY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();

            switch (currentShapeType) {
                case 0:
                    currentShape = new Line2D.Double(startX, startY, startX, startY);
                    break;
                case 1:
                    currentShape = new Rectangle2D.Double(startX, startY, 0, 0);
                    break;
                case 2:
                    currentShape = new Ellipse2D.Double(startX, startY, 0, 0);
                    break;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int endX = e.getX();
            int endY = e.getY();

            switch (currentShapeType) {
                case 0:
                    ((Line2D.Double) currentShape).setLine(startX, startY, endX, endY);
                    break;
                case 1:
                    ((Rectangle2D.Double) currentShape).setRect(
                            Math.min(startX, endX),
                            Math.min(startY, endY),
                            Math.abs(endX - startX),
                            Math.abs(endY - startY)
                    );
                    break;
                case 2:
                    ((Ellipse2D.Double) currentShape).setFrame(
                            Math.min(startX, endX),
                            Math.min(startY, endY),
                            Math.abs(endX - startX),
                            Math.abs(endY - startY)
                    );
                    break;
            }

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapes.add(currentShape);
            shapeColors.add(getCurrentColor());
            currentShape = null;
            repaint();
        }
    }

    private Color getCurrentColor() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK};
        return colors[currentColorIndex];
    }

    private class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i < shapes.size(); i++) {
                g2d.setColor(shapeColors.get(i));
                g2d.draw(shapes.get(i));
            }

            if (currentShape != null) {
                g2d.setColor(getCurrentColor());
                g2d.draw(currentShape);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintApplication().setVisible(true));
    }
}
