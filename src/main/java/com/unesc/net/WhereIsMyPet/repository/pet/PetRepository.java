package com.unesc.net.WhereIsMyPet.repository.pet;

import com.unesc.net.WhereIsMyPet.entity.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
