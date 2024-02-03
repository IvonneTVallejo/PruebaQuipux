package com.prueba.quipux.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraTextoEntity {

    @Id
    private Integer id;
    private Integer hora;
    private Integer minutos;

}
