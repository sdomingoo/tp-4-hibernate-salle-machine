package services;

import entities.Machine;
import entities.Salle;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MachineServiceTest {

    private static SalleService salleService;
    private static MachineService machineService;
    private static Salle testSalle;
    private static int createdMachineId;

    @BeforeAll
    public static void setUp() {
        salleService = new SalleService();
        machineService = new MachineService();

        testSalle = new Salle("TEST_ROOM", "Salle pour tests unitaires");
        salleService.create(testSalle);
    }

    @AfterAll
    public static void tearDown() {
        if (testSalle != null && testSalle.getId() > 0) {
            Salle s = salleService.getById(testSalle.getId());
            if (s != null) {
                salleService.delete(s);
            }
        }
    }

    @Test
    @Order(1)
    public void testCreateMachine() {
        Machine machine = new Machine("M-TEST-1", "Lenovo", testSalle);
        boolean result = machineService.create(machine);

        assertTrue(result, "La création de la machine doit réussir");
        assertTrue(machine.getId() > 0);
        createdMachineId = machine.getId();
    }

    @Test
    @Order(2)
    public void testGetById() {
        Machine machine = machineService.getById(createdMachineId);
        assertNotNull(machine);
        assertEquals("M-TEST-1", machine.getRef());
        assertEquals("Lenovo", machine.getMarque());
    }

    @Test
    @Order(3)
    public void testFindByMarqueHql() {
        List<Machine> machines = machineService.findByMarque("Lenovo");
        assertNotNull(machines);
        assertFalse(machines.isEmpty(), "La requête HQL findByMarque doit trouver la machine Lenovo");
        assertTrue(machines.stream().anyMatch(m -> m.getRef().equals("M-TEST-1")));
    }

    @Test
    @Order(4)
    public void testUpdateMachine() {
        Machine machine = machineService.getById(createdMachineId);
        assertNotNull(machine);

        machine.setRef("M-TEST-1-UPDATED");
        boolean updated = machineService.update(machine);
        assertTrue(updated);

        Machine reloaded = machineService.getById(createdMachineId);
        assertEquals("M-TEST-1-UPDATED", reloaded.getRef());
    }

    @Test
    @Order(5)
    public void testDeleteMachine() {
        Machine machine = machineService.getById(createdMachineId);
        assertNotNull(machine);

        boolean deleted = machineService.delete(machine);
        assertTrue(deleted);

        Machine notFound = machineService.getById(createdMachineId);
        assertNull(notFound);
    }
}
