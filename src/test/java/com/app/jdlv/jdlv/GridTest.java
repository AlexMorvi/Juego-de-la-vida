package com.app.jdlv.jdlv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GridTest {

    private Grid grid;

    @BeforeEach
    public void setUp() {
        grid = new Grid(3, 3); // Crear una cuadrícula de 3x3 para las pruebas
    }

    @Test
    public void testInitialGridIsDead() {
        // Comprobar que todas las células están inicialmente muertas
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertFalse(grid.getCell(row, col).isAlive());
            }
        }
    }

    @Test
    public void testSetCellAlive() {
        grid.setCell(1, 1, true);
        assertTrue(grid.getCell(1, 1).isAlive());
    }

    @Test
    public void testCountAliveNeighbors() {
        // Crear una configuración conocida de células vivas
        grid.setCell(0, 0, true);
        grid.setCell(0, 1, true);
        grid.setCell(1, 0, true);

        // Comprobar el conteo de vecinos vivos
        assertEquals(2, grid.countAliveNeighbors(0, 0));
        assertEquals(2, grid.countAliveNeighbors(0, 1));
        assertEquals(2, grid.countAliveNeighbors(1, 0));
        assertEquals(3, grid.countAliveNeighbors(1, 1));
        assertEquals(1, grid.countAliveNeighbors(0, 2));
    }

    @Test
    public void testUpdateGrid() {
        // Crear una configuración conocida de células vivas
        grid.setCell(0, 0, true);
        grid.setCell(0, 1, true);
        grid.setCell(1, 0, true);

        // Actualizar la cuadrícula
        grid.updateGrid();

        // Comprobar el nuevo estado de las células
        assertTrue(grid.getCell(0, 0).isAlive());
        assertTrue(grid.getCell(0, 1).isAlive());
        assertTrue(grid.getCell(1, 0).isAlive());
        assertTrue(grid.getCell(1, 1).isAlive()); // Debe vivir por tener 3 vecinos vivos

        // Celda (1,1) originalmente muerta con 3 vecinos vivos
        assertFalse(grid.getCell(0, 2).isAlive());
        assertFalse(grid.getCell(1, 2).isAlive());
        assertFalse(grid.getCell(2, 0).isAlive());
        assertFalse(grid.getCell(2, 1).isAlive());
        assertFalse(grid.getCell(2, 2).isAlive());
    }
}

