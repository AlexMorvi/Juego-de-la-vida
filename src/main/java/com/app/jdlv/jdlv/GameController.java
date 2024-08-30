package com.app.jdlv.jdlv;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GameController {

    private Grid grid;
    private int gridHeight = 0; // Altura de la cuadrícula
    private int gridWidth = 0;  // Ancho de la cuadrícula
    private int generation = 0;

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Button nextGenerationButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField widthInput;

    @FXML
    private Button applySizeButton;

    @FXML
    private Label generationCounter;

    @FXML
    public void initialize() {
        gameCanvas.setOnMouseClicked(event -> {
            if (gridHeight > 0 && gridWidth > 0) {
                double x = event.getX();
                double y = event.getY();
                int row = (int) (y / (gameCanvas.getHeight() / gridHeight));
                int col = (int) (x / (gameCanvas.getWidth() / gridWidth));

                if (row >= 0 && row < gridHeight && col >= 0 && col < gridWidth) {
                    boolean isAlive = !grid.getCell(row, col).isAlive();
                    grid.setCell(row, col, isAlive);
                    drawGrid();
                    generation = 0;
                }
            }
        });
    }

    @FXML
    public void applySize() {
        try {
            int newHeight = Integer.parseInt(heightInput.getText());
            int newWidth = Integer.parseInt(widthInput.getText());
            if (newHeight > 0 && newWidth > 0) {
                gridHeight = newHeight;
                gridWidth = newWidth;
                grid = new Grid(gridHeight, gridWidth);
                generation = 0;
                generationCounter.setText("Generación: " + generation);
                drawGrid();

                // Limpiar los campos de texto
                heightInput.clear();
                widthInput.clear();
            }
        } catch (NumberFormatException e) {
            // Manejar errores si los tamaños no son números válidos
            heightInput.setText("Altura inválida");
            widthInput.setText("Ancho inválido");
        }
    }

    @FXML
    public void nextGeneration() {
        if (gridHeight > 0 && gridWidth > 0) {
            grid.updateGrid();
            generation++;
            drawGrid();
            generationCounter.setText("Generación: " + generation);
        }
    }

    @FXML
    public void resetGame() {
        generation = 0;
        generationCounter.setText("Generación: " + generation);
        grid = new Grid(gridHeight, gridWidth);
        drawGrid();
    }

    @FXML
    private Label gridSizeLabel;

    private void drawGrid() {
        if (gridHeight == 0 || gridWidth == 0) return; // No dibujar si el tamaño es 0x0

        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        double canvasWidth = gameCanvas.getWidth();
        double canvasHeight = gameCanvas.getHeight();
        double cellWidth = canvasWidth / gridWidth;
        double cellHeight = canvasHeight / gridHeight;
        gc.setFont(javafx.scene.text.Font.font(8));
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                if (grid.getCell(row, col).isAlive()) {
                    gc.setFill(Color.BLACK);
                } else {
                    gc.setFill(Color.WHITE);
                }
                gc.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                gc.setStroke(Color.GRAY);
                gc.strokeRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);

                // Mostrar coordenadas de cada celda
                gc.setFill(Color.GRAY);
                gc.fillText("(" + row + "," + col + ")", col * cellWidth + 2, row * cellHeight + 12);
            }
        }

        // Actualizar el tamaño actual de la cuadrícula
        gridSizeLabel.setText("Tamaño de la Cuadrícula: " + gridHeight + "x" + gridWidth);
    }
}
