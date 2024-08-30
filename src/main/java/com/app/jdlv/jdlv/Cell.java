package com.app.jdlv.jdlv;

public class Cell {
    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Metodo para determinar el próximo estado de la célula basado en el número de vecinos vivos
    public void updateState(int aliveNeighbors) {
        if (alive) {
            // Regla 1 y 3: Una célula viva con menos de 2 o más de 3 vecinos vivos muere
            alive = (aliveNeighbors == 2 || aliveNeighbors == 3);
        } else {
            // Regla 4: Una célula muerta con exactamente 3 vecinos vivos se convierte en una célula viva
            alive = (aliveNeighbors == 3);
        }
    }
}