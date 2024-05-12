package com.example.demo.Domain.Entities.Security;

import com.example.demo.Domain.Entities.CerereAdoptie;
import jakarta.persistence.*;
import java.util.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(
            mappedBy = "utilizator",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CerereAdoptie> cereriAdoptie;
}
