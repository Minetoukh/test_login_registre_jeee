package com.min.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.min.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNomRole(String roleName);
    // Ajoutez des méthodes personnalisées si nécessaire
}
