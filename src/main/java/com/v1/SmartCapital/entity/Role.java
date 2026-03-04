package com.v1.SmartCapital.entity;

import com.v1.SmartCapital.enums.RoleType;
import com.v1.SmartCapital.response.RoleResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    RoleType name;

    public Role(RoleType name) {
        this.name = name;
    }

    public RoleResponse getRoleResponse() {
        return new RoleResponse(id, name);
    }
}
