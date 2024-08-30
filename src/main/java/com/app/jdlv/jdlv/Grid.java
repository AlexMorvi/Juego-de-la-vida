package com.app.jdlv.jdlv;

public class Grid {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        // Inicializar todas las células como muertas
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(false);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, boolean alive) {
        cells[row][col].setAlive(alive);
    }

    // Metodo para contar vecinos vivos alrededor de una célula dada
    public int countAliveNeighbors(int row, int col) {
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // No contar la célula misma
                }
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < rows && c >= 0 && c < cols && cells[r][c].isAlive()) {
                    aliveNeighbors++;
                }
            }
        }
        return aliveNeighbors;
    }

    // Metodo para actualizar el estado del tablero basado en las reglas del Juego de la Vida
    public void updateGrid() {
        Cell[][] newGrid = new Cell[rows][cols];

        // Calcular el nuevo estado de cada célula
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                newGrid[row][col] = new Cell(cells[row][col].isAlive());
                int aliveNeighbors = countAliveNeighbors(row, col);
                newGrid[row][col].updateState(aliveNeighbors);
            }
        }

        // Actualizar el estado del tablero
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = newGrid[row][col];
            }
        }
    }
}