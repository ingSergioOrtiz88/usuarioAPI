package com.example.usuarioapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "phone")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Phone {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;


    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "citycode")
    private String citycode;

    @Column(name = "countrycode")
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
}
