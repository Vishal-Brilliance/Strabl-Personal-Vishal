package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Country extends BaseEntity{

    @PrePersist
    private void onCreate() {
        setCountry_uuid(java.util.UUID.randomUUID());
    }
    private UUID country_uuid;

    private String countryName;
    private String countryFlagUrl;
    private String countryIsoName;
    private Boolean status;

}
