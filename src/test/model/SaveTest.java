package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import persistence.Save;

public class SaveTest {

    double balance;
    Save save;

    @BeforeEach
    public void runBefore() {
        save = new Save();
    }

    @Test
    public void testSave() {
        save.saveBalance(400, "./data/testsave.txt");
        assertEquals(400, save.loadBalance("./data/testsave.txt"));
    }

    @Test
    public void testIfNoFile() {
        assertEquals(0, save.loadBalance("random.txt"));
    }

    @Test
    public void testException() {
        assertEquals("Unable to save.", save.saveBalance(100, "\n"));
    }
}
