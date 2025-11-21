package org.example.alamane.repository;

import org.example.alamane.entity.Societe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SocieteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SocieteRepository societeRepository;

    @Test
    void save_shouldPersistSociete() {
        Societe societe = new Societe();
        societe.setRaisonSociale("Test Company");
        societe.setIce("123456789");
        societe.setEmail("test@company.com");

        Societe saved = societeRepository.save(societe);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getRaisonSociale()).isEqualTo("Test Company");
    }

    @Test
    void findById_shouldReturnSociete() {
        Societe societe = new Societe();
        societe.setRaisonSociale("Test Company");
        societe.setIce("123456789");
        entityManager.persist(societe);
        entityManager.flush();

        Optional<Societe> found = societeRepository.findById(societe.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getRaisonSociale()).isEqualTo("Test Company");
    }

    @Test
    void findAll_shouldReturnAllSocietes() {
        Societe societe1 = new Societe();
        societe1.setRaisonSociale("Company 1");
        entityManager.persist(societe1);

        Societe societe2 = new Societe();
        societe2.setRaisonSociale("Company 2");
        entityManager.persist(societe2);

        entityManager.flush();

        assertThat(societeRepository.findAll()).hasSize(2);
    }

    @Test
    void delete_shouldRemoveSociete() {
        Societe societe = new Societe();
        societe.setRaisonSociale("Test Company");
        entityManager.persist(societe);
        entityManager.flush();

        societeRepository.deleteById(societe.getId());

        assertThat(societeRepository.findById(societe.getId())).isEmpty();
    }
}
