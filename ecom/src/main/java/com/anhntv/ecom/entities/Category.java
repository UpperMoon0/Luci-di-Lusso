package com.anhntv.ecom.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(columnDefinition = "VARCHAR(255)")
    private String description;
}
