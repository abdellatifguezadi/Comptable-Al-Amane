package org.example.alamane.repository;

import org.example.alamane.entity.Utilisateur;
import org.example.alamane.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UtilisateurRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    void findByEmail_shouldReturnUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@example.com");
        utilisateur.setPassword("password123");
        utilisateur.setNomComplet("Test User");
        utilisateur.setRole(Role.COMPTABLE);
        entityManager.persist(utilisateur);
        entityManager.flush();

        Optional<Utilisateur> found = utilisateurRepository.findByEmail("test@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getNomComplet()).isEqualTo("Test User");
    }

    @Test
    void findByEmail_shouldReturnEmptyWhenNotFound() {
        Optional<Utilisateur> found = utilisateurRepository.findByEmail("nonexistent@example.com");

        assertThat(found).isEmpty();
    }

    @Test
    void save_shouldPersistUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("new@example.com");
        utilisateur.setPassword("password");
        utilisateur.setNomComplet("New User");
        utilisateur.setRole(Role.SOCIETE);
        utilisateur.setStatut(true);

        Utilisateur saved = utilisateurRepository.save(utilisateur);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("new@example.com");
    }

    @Test
    void findAll_shouldReturnAllUtilisateurs() {
        Utilisateur user1 = new Utilisateur();
        user1.setEmail("user1@example.com");
        user1.setPassword("pass1");
        user1.setRole(Role.COMPTABLE);
        entityManager.persist(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setEmail("user2@example.com");
        user2.setPassword("pass2");
        user2.setRole(Role.SOCIETE);
        entityManager.persist(user2);

        entityManager.flush();

        assertThat(utilisateurRepository.findAll()).hasSize(2);
    }
}
