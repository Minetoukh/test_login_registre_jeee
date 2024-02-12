package com.min.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private Long roleID;

    @Column(name = "NomRole")
    private String nomRole;

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Role(Long roleID, String nomRole) {
        super();
        this.roleID = roleID;
        this.nomRole = nomRole;
    }

    public Role(String nomRole) {
        super();
        this.nomRole = nomRole;
    }

    public Long getRoleID() {
        return roleID;
    }


    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }



}

