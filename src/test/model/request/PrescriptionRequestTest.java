package test.model.request;

import org.junit.jupiter.api.*;
import soft252.exceptions.StockLevelException;
import soft252.model.drugs.Drug;
import soft252.model.drugs.DrugRepository;
import soft252.model.drugs.Prescription;
import soft252.model.request.PrescriptionRequest;
import soft252.model.request.RequestRepository;
import soft252.model.request.RequestType;
import soft252.model.user.Patient;
import soft252.model.user.info.Gender;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionRequestTest {
    private Patient _patient;
    private Drug _drug;
    private PrescriptionRequest _request;

    @BeforeEach
    void setUp() {
        _patient = (Patient) new Patient(
                "1111",
                "Lucas",
                "McCarron",
                Gender.MALE).include();

        _drug = new Drug.Builder("Paracetamol")
                .setDescription("Painkiller")
                .build()
                .include();

        Prescription prescription = new Prescription(_drug, 7, 7);

        _request = (PrescriptionRequest) new PrescriptionRequest(_patient, prescription).include();
    }

    @AfterEach
    void tearDown() {
        DrugRepository.getInstance().clear();
        RequestRepository.getInstance().clear();
    }

    @Test
    @DisplayName("approve - successful")
    void approve() {
        _drug.setStock(10);

        assertDoesNotThrow(() -> _request.approve());
        assertEquals(DrugRepository.getInstance().getStock(_drug), 3);
        assertTrue(RequestRepository.getInstance().get(RequestType.PRESCRIPTION).size() < 1);

    }

    @Test
    @DisplayName("approve - unsuccessful")
    void approve_2() {
        _drug.setStock(5);

        assertThrows(StockLevelException.class, () -> _request.approve());
        assertEquals(DrugRepository.getInstance().getStock(_drug), 5);
        assertTrue(RequestRepository.getInstance().get(RequestType.PRESCRIPTION).size() > 0);
    }

    @Test
    @DisplayName("deny")
    void deny() throws Exception {
        _request.deny();

        assertTrue(_patient.getMessages().size() > 0);
        assertTrue(RequestRepository.getInstance().get(RequestType.PRESCRIPTION).size() < 1);
    }

    @Test
    @DisplayName("update")
    void update() {
        int stockChange = 23;
        _drug.setStock(stockChange);

        assertEquals(stockChange, _request.getDrugStock());
    }
}