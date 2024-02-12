package com.min.demo.service;

import java.util.Optional;

import com.min.demo.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.min.demo.entities.Utilisateur;
import com.min.demo.repository.RoleRepository;
import com.min.demo.repository.UtilisateurRepository;

@Service
public class AuthService {

    @Autowired
    private  UtilisateurRepository userRepository;

    @Autowired
    private  RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;


    }


    public   Utilisateur saveUser( Utilisateur user) {
        // Vérifiez si l'utilisateur existe déjà
        Optional<Utilisateur> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Useremail already exists");
        }
        // Set the role based on the role name

        Role role = roleRepository.findByNomRole(user.getRole().getNomRole());
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        user.setRole(role);

        // Hashage du mot de passe avant de le sauvegarder
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        // Hashage du mot de passe avant de le sauvegarder
        user.setEmail(user.getEmail());
        // Hashage du mot de passe avant de le sauvegarder
        user.setNom(user.getNom());
        user.setRole(user.getRole());


        // Sauvegarde de l'utilisateur
        return userRepository.save(user);
    }


    public Utilisateur authenticateUser(String email, String password) {
        Optional<Utilisateur> existingUser = userRepository.findByEmail(email);



            return existingUser.get();


    }

}
