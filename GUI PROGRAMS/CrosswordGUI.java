import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

class CrosswordGenerator {
    private char[][] puzzle;
    private Map<String, String> wordClues;

    public CrosswordGenerator() {
        puzzle = new char[10][10];
        for (int i = 0; i < 10; i++) {
            java.util.Arrays.fill(puzzle[i], ' ');
        }
        wordClues = new java.util.HashMap<>();
    }

    public void addWord(String word, String clue) {
        wordClues.put(word, clue);
    }

    public void generateCrossword() {
        Collections.shuffle(new ArrayList<>(wordClues.keySet()));
        for (String word : wordClues.keySet()) {
            placeWord(word);
        }
        fillEmptySpaces();
    }

    private void placeWord(String word) {
        // Placeholder logic for placing words in the puzzle.
        Random random = new Random();
        boolean placed = false;

        while (!placed) {
            int row = random.nextInt(puzzle.length);
            int col = random.nextInt(puzzle[0].length);
            boolean horizontal = random.nextBoolean();

            if (tryPlaceWord(word, row, col, horizontal)) {
                placed = true;
            }
        }
    }

    private boolean tryPlaceWord(String word, int row, int col, boolean horizontal) {
        int len = word.length();

        if (horizontal && col + len <= puzzle[0].length) {
            for (int i = 0; i < len; i++) {
                if (puzzle[row][col + i] != ' ' && puzzle[row][col + i] != word.charAt(i)) {
                    return false;
                }
            }

            for (int i = 0; i < len; i++) {
                puzzle[row][col + i] = word.charAt(i);
            }
            return true;
        } else if (!horizontal && row + len <= puzzle.length) {
            for (int i = 0; i < len; i++) {
                if (puzzle[row + i][col] != ' ' && puzzle[row + i][col] != word.charAt(i)) {
                    return false;
                }
            }

            for (int i = 0; i < len; i++) {
                puzzle[row + i][col] = word.charAt(i);
            }
            return true;
        }

        return false;
    }

    private void fillEmptySpaces() {
        // Placeholder logic for filling empty spaces in the puzzle.
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (puzzle[i][j] == ' ') {
                    puzzle[i][j] = getRandomLetter();
                }
            }
        }
    }

    private char getRandomLetter() {
        // Placeholder logic for getting a random letter.
        Random random = new Random();
        return (char) ('A' + random.nextInt(26));
    }

    public char[][] getPuzzle() {
        return puzzle;
    }

    public Map<String, String> getWordClues() {
        return wordClues;
    }
}

class CrosswordGUI extends JFrame {
    private CrosswordGenerator crosswordGenerator;
    private List<Point> selectedCells;

    public CrosswordGUI() {
        crosswordGenerator = new CrosswordGenerator();
        crosswordGenerator.addWord("HELLO", "Greeting");
        crosswordGenerator.addWord("WORLD", "Planet");
        crosswordGenerator.addWord("JAVA", "Programming language");

        selectedCells = new ArrayList<>();

        JButton generateButton = new JButton("Generate Crossword");
        generateButton.addActionListener(e -> generateCrossword());

        JPanel puzzlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPuzzle(g);
            }
        };

        puzzlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCellClick(e.getX(), e.getY());
            }
        });

        setLayout(new BorderLayout());
        add(generateButton, BorderLayout.NORTH);
        add(puzzlePanel, BorderLayout.CENTER);

        setTitle("Crossword Puzzle Generator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void generateCrossword() {
        crosswordGenerator.generateCrossword();
        selectedCells.clear();
        repaint();
    }

    private void drawPuzzle(Graphics g) {
        char[][] puzzle = crosswordGenerator.getPuzzle();
        int cellSize = 30;

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                boolean isSelected = selectedCells.contains(new Point(i, j));
                if (isSelected) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.drawString(String.valueOf(puzzle[i][j]), j * cellSize + cellSize / 2, i * cellSize + cellSize / 2);
            }
        }

        Map<String, String> wordClues = crosswordGenerator.getWordClues();
        int yOffset = puzzle.length * cellSize + 20;

        for (String word : wordClues.keySet()) {
            g.setColor(Color.BLACK);
            g.drawString(word + ": " + wordClues.get(word), 10, yOffset);
            yOffset += 20;
        }
    }

    private void handleCellClick(int x, int y) {
        int cellSize = 30;
        int row = y / cellSize;
        int col = x / cellSize;

        Point clickedCell = new Point(row, col);

        if (!selectedCells.contains(clickedCell)) {
            selectedCells.add(clickedCell);
        } else {
            selectedCells.remove(clickedCell);
        }

        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrosswordGUI crosswordGUI = new CrosswordGUI();
            crosswordGUI.setVisible(true);
        });
    }
}
