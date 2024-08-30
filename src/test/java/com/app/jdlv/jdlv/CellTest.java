package com.app.jdlv.jdlv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void testCellInitialization() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive(), "La célula debería estar viva al inicializarse con true");

        cell = new Cell(false);
        assertFalse(cell.isAlive(), "La célula debería estar muerta al inicializarse con false");
    }

    @Test
    void testSetAlive() {
        Cell cell = new Cell(false);
        cell.setAlive(true);
        assertTrue(cell.isAlive(), "La célula debería estar viva después de llamar a setAlive(true)");

        cell.setAlive(false);
        assertFalse(cell.isAlive(), "La célula debería estar muerta después de llamar a setAlive(false)");
    }

    @Test
    void testUpdateState() {
        Cell cell = new Cell(true);
        cell.updateState(1); // menos de 2 vecinos vivos
        assertFalse(cell.isAlive(), "La célula debería morir con menos de 2 vecinos vivos");

        cell.setAlive(true);
        cell.updateState(2); // exactamente 2 vecinos vivos
        assertTrue(cell.isAlive(), "La célula debería seguir viva con 2 vecinos vivos");

        cell.updateState(3); // exactamente 3 vecinos vivos
        assertTrue(cell.isAlive(), "La célula debería seguir viva con 3 vecinos vivos");

        cell.updateState(4); // más de 3 vecinos vivos
        assertFalse(cell.isAlive(), "La célula debería morir con más de 3 vecinos vivos");

        cell.setAlive(false);
        cell.updateState(3); // exactamente 3 vecinos vivos para revivir
        assertTrue(cell.isAlive(), "La célula muerta debería revivir con exactamente 3 vecinos vivos");
    }
}
