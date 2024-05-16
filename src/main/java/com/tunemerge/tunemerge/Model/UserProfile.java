package com.tunemerge.tunemerge.Model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserProfile {
    @Id
    String id;
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    String country;
    String display_name;
    String email;
    int total;

    

}
