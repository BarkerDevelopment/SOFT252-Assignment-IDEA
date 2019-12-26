package test.services.serialization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DummyRepositorySerialisationTest {
    private DummyRepository _repo;

    @BeforeEach
    void setUp() {
        _repo = DummyRepository.getInstance();
    }

    @AfterEach
    void tearDown() {
        _repo.clear();
    }

    @Test
    void test() {
        new DummyObject("Gidget", 1).include();
        new DummyObject("Berty", 3).include();
        new DummyObject("Sausage", 5).include();

        _repo.save();
        ArrayList< DummyObject > dummies = new ArrayList<>(_repo.get());

        _repo.clear();
        assertTrue(_repo.get().isEmpty());

        _repo.load();
        for (int i = 0; i < dummies.size(); i++) {
            assertEquals(dummies.get(i).getName(), _repo.get().get(i).getName());
            assertEquals(dummies.get(i).getAge(), _repo.get().get(i).getAge());
        }
    }
}