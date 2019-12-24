package test.model.drugs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.exceptions.StockLevelException;
import soft252.model.drugs.Drug;
import soft252.model.drugs.DrugRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugRepositoryTest {
    private static DrugRepository _repo;
    private static Drug _paracetamol;
    private static Drug _ibuprofen;
    private static Drug _morphine;

    @BeforeEach
    void setup(){
        _repo = DrugRepository.getInstance();
        _paracetamol = new Drug.Builder("Paracetamol")
                .setDescription("Painkiller")
                .build()
                .include();
        _paracetamol.setStock(5);

        _ibuprofen = new Drug.Builder("Ibuprofen")
                .setDescription("Painkiller")
                .addSideEffect("Stomach Ulcers")
                .build()
                .include();
        _ibuprofen.setStock(3);

        _morphine = new Drug.Builder("Morphine")
                .setDescription("Painkiller in liquid.")
                .addSideEffect("Hallucintions")
                .build()
                .include();
    }

    @AfterEach
    void teardown(){
        _repo.clear();
    }

    @Test
    @DisplayName("getAll")
    void getAll() {
        Drug[] expResult = new Drug[0]; new ArrayList<>(List.of(_ibuprofen, _paracetamol, _morphine)).toArray(expResult);
        Drug[] result = new Drug[0]; _repo.get().toArray();

        assertArrayEquals(expResult, result);
    }

    @Test
    @DisplayName("get")
    void get() {
        Drug expResult = _ibuprofen;
        Drug result = _repo.get("Ibuprofen");

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("remove")
    void remove() {
        _repo.remove(_paracetamol);

        boolean result = _repo.get().contains(_paracetamol);
        assertFalse(result);
    }

    @Test
    @DisplayName("add")
    void add() {
        _repo.add(_paracetamol);

        boolean result = _repo.get().contains(_paracetamol);
        assertTrue(result);
    }

    @Test
    @DisplayName("add with stock")
    void testAdd() {
        boolean result = _repo.get().contains(_paracetamol);
        assertTrue(result);

        int expResult2 = 5;
        int result2 = _repo.getStock(_paracetamol);
        assertEquals(result2, expResult2);
    }

    @Test
    @DisplayName("contains via drug")
    void contains() {
        boolean result = _repo.contains(_ibuprofen);
        assertTrue(result);
    }

    @Test
    @DisplayName("contains via name")
    void testContains() {
        boolean result = _repo.contains("Paracetamol");
        assertTrue(result);
    }

    @Test
    @DisplayName("getStock")
    void getStock() {
        HashMap<Drug, Integer>  expResult = new HashMap<>();
        expResult.put( _paracetamol, 5);
        expResult.put( _ibuprofen, 3);
        expResult.put( _morphine, 0);

        HashMap<Drug, Integer> result = _repo.getStock();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("getStock of specific drug")
    void testGetStock() {
        int expResult = 5;
        int result = _repo.getStock(_paracetamol);

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("updateStock")
    void updateStock() {
        assertThrows(StockLevelException.class, () -> _repo.updateStock(_ibuprofen, -4));
        assertDoesNotThrow(() -> _repo.updateStock(_ibuprofen, 3));

        int expResult = 6;
        int result = _repo.getStock(_ibuprofen);
        assertEquals(expResult, result);
    }
}