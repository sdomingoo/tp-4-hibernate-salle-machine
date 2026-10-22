package services;

import entities.Salle;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleServiceTest {

    private static SalleService service;
    private static int createdSalleId;

    @BeforeAll
    public static void setUp() {
        service = new SalleService();
    }

    @Test
    @Order(1)
    public void testCreateSalle() {
        Salle salle = new Salle();
        salle.setCode("S2");
        salle.setLibelle("Salle Réseau");

        boolean result = service.create(salle);
        assertTrue(result, "La création de la salle doit réussir");
        assertTrue(salle.getId() > 0, "L'identifiant généré doit être supérieur à 0");

        createdSalleId = salle.getId();
    }

    @Test
    @Order(2)
    public void testGetById() {
        Salle salle = service.getById(createdSalleId);
        assertNotNull(salle, "La salle récupérée ne doit pas être nulle");
        assertEquals("S2", salle.getCode());
        assertEquals("Salle Réseau", salle.getLibelle());
    }

    @Test
    @Order(3)
    public void testUpdateSalle() {
        Salle salle = service.getById(createdSalleId);
        assertNotNull(salle);

        salle.setLibelle("Salle Télécom & Réseaux");
        boolean updated = service.update(salle);
        assertTrue(updated, "La mise à jour de la salle doit réussir");

        Salle reloaded = service.getById(createdSalleId);
        assertEquals("Salle Télécom & Réseaux", reloaded.getLibelle());
    }

    @Test
    @Order(4)
    public void testGetAllSalles() {
        List<Salle> salles = service.getAll();
        assertNotNull(salles);
        assertFalse(salles.isEmpty(), "La liste des salles ne doit pas être vide");
    }

    @Test
    @Order(5)
    public void testDeleteSalle() {
        Salle salle = service.getById(createdSalleId);
        assertNotNull(salle);

        boolean deleted = service.delete(salle);
        assertTrue(deleted, "La suppression de la salle doit réussir");

        Salle notFound = service.getById(createdSalleId);
        assertNull(notFound, "La salle supprimée ne doit plus exister");
    }
}
