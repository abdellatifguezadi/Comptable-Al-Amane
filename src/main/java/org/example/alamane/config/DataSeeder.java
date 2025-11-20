package org.example.alamane.config;

import lombok.RequiredArgsConstructor;
import org.example.alamane.enums.Role;
import org.example.alamane.entity.Societe;
import org.example.alamane.entity.Utilisateur;
import org.example.alamane.repository.SocieteRepository;
import org.example.alamane.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final SocieteRepository societeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (societeRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        Societe societe1 = new Societe();
        societe1.setRaisonSociale("Tech Solutions SARL");
        societe1.setIce("001234567890001");
        societe1.setAdresse("123 Rue Mohammed V, Casablanca");
        societe1.setTelephone("0522123456");
        societe1.setEmail("contact@techsolutions.ma");
        societeRepository.save(societe1);

        Societe societe2 = new Societe();
        societe2.setRaisonSociale("Commerce Plus SA");
        societe2.setIce("001234567890002");
        societe2.setAdresse("456 Avenue Hassan II, Rabat");
        societe2.setTelephone("0537654321");
        societe2.setEmail("info@commerceplus.ma");
        societeRepository.save(societe2);

        Utilisateur comptable = new Utilisateur();
        comptable.setEmail("comptable@alamane.ma");
        comptable.setPassword(passwordEncoder.encode("comptable123"));
        comptable.setNomComplet("Ahmed Alami");
        comptable.setRole(Role.COMPTABLE);
        comptable.setStatut(true);
        comptable.setDateCréation(LocalDateTime.now());
        utilisateurRepository.save(comptable);

        Utilisateur user1 = new Utilisateur();
        user1.setEmail("user@techsolutions.ma");
        user1.setPassword(passwordEncoder.encode("user123"));
        user1.setNomComplet("Fatima Zahra");
        user1.setRole(Role.SOCIETE);
        user1.setSociete(societe1);
        user1.setStatut(true);
        user1.setDateCréation(LocalDateTime.now());
        utilisateurRepository.save(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setEmail("user@commerceplus.ma");
        user2.setPassword(passwordEncoder.encode("user123"));
        user2.setNomComplet("Youssef Bennani");
        user2.setRole(Role.SOCIETE);
        user2.setSociete(societe2);
        user2.setStatut(true);
        user2.setDateCréation(LocalDateTime.now());
        utilisateurRepository.save(user2);

        System.out.println("✅ Données initiales créées avec succès!");
        System.out.println("📧 Comptable: comptable@alamane.ma / comptable123");
        System.out.println("📧 Société 1: user@techsolutions.ma / user123");
        System.out.println("📧 Société 2: user@commerceplus.ma / user123");
    }
}
