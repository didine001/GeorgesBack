package com.georges.georges.privileges.models;

import com.georges.georges.role.models.Role;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}