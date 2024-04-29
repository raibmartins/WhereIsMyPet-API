package com.unesc.net.WhereIsMyPet.resources.petlocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetLocationPetDto implements Serializable {
    private Long id;
    private String nome;
}
