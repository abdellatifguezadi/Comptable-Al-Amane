package org.example.alamane.repository;

import org.example.alamane.entity.Document;
import org.example.alamane.entity.Societe;
import org.example.alamane.enums.StatutDocument;
import org.example.alamane.enums.TypeDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DocumentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void findBySocieteIdAndExercice_shouldReturnDocuments() {
        Societe societe = new Societe();
        societe.setRaisonSociale("Test Societe");
        entityManager.persist(societe);

        Document doc1 = new Document();
        doc1.setSociete(societe);
        doc1.setDatePiece(LocalDate.of(2024, 1, 15));
        doc1.setType(TypeDocument.FACTURE_ACHAT);
        doc1.setMontant(BigDecimal.valueOf(1000));
        entityManager.persist(doc1);

        Document doc2 = new Document();
        doc2.setSociete(societe);
        doc2.setDatePiece(LocalDate.of(2024, 6, 20));
        doc2.setType(TypeDocument.FACTURE_VENTE);
        doc2.setMontant(BigDecimal.valueOf(2000));
        entityManager.persist(doc2);

        Document doc3 = new Document();
        doc3.setSociete(societe);
        doc3.setDatePiece(LocalDate.of(2023, 12, 10));
        doc3.setType(TypeDocument.TICKET_CAISSE);
        doc3.setMontant(BigDecimal.valueOf(500));
        entityManager.persist(doc3);

        entityManager.flush();

        List<Document> result = documentRepository.findBySocieteIdAndExercice(societe.getId(), 2024);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Document::getDatePiece)
                .allMatch(date -> date.getYear() == 2024);
    }

    @Test
    void findByStatut_shouldReturnDocumentsWithGivenStatus() {
        Document doc1 = new Document();
        doc1.setStatut(StatutDocument.EN_ATTENTE);
        doc1.setType(TypeDocument.FACTURE_ACHAT);
        entityManager.persist(doc1);

        Document doc2 = new Document();
        doc2.setStatut(StatutDocument.VALIDE);
        doc2.setType(TypeDocument.FACTURE_VENTE);
        entityManager.persist(doc2);

        Document doc3 = new Document();
        doc3.setStatut(StatutDocument.EN_ATTENTE);
        doc3.setType(TypeDocument.TICKET_CAISSE);
        entityManager.persist(doc3);

        entityManager.flush();

        List<Document> result = documentRepository.findByStatut(StatutDocument.EN_ATTENTE);

        assertThat(result).hasSize(2);
        assertThat(result).allMatch(doc -> doc.getStatut() == StatutDocument.EN_ATTENTE);
    }

    @Test
    void findBySocieteId_shouldReturnAllDocumentsForSociete() {
        Societe societe1 = new Societe();
        societe1.setRaisonSociale("Societe 1");
        entityManager.persist(societe1);

        Societe societe2 = new Societe();
        societe2.setRaisonSociale("Societe 2");
        entityManager.persist(societe2);

        Document doc1 = new Document();
        doc1.setSociete(societe1);
        doc1.setType(TypeDocument.FACTURE_ACHAT);
        entityManager.persist(doc1);

        Document doc2 = new Document();
        doc2.setSociete(societe1);
        doc2.setType(TypeDocument.FACTURE_VENTE);
        entityManager.persist(doc2);

        Document doc3 = new Document();
        doc3.setSociete(societe2);
        doc3.setType(TypeDocument.TICKET_CAISSE);
        entityManager.persist(doc3);

        entityManager.flush();

        List<Document> result = documentRepository.findBySocieteId(societe1.getId());

        assertThat(result).hasSize(2);
        assertThat(result).allMatch(doc -> doc.getSociete().getId().equals(societe1.getId()));
    }
}
