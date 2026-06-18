package com.uniremington.parque_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Rol {

   @Id 
    private Integer id; 

    private String nombre;
}