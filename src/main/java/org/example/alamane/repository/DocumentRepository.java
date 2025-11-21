package org.example.alamane.repository;

import org.example.alamane.entity.Document;
import org.example.alamane.enums.StatutDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE d.societe.id = :societeId AND YEAR(d.datePiece) = :exercice")
    List<Document> findBySocieteIdAndExercice(@Param("societeId") Long societeId, @Param("exercice") int exercice);
    
    List<Document> findByStatut(StatutDocument statut);
    
    List<Document> findBySocieteId(Long societeId);
}
