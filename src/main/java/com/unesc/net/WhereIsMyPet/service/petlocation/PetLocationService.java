package com.unesc.net.WhereIsMyPet.service.petlocation;

import com.unesc.net.WhereIsMyPet.entity.pet.Pet;
import com.unesc.net.WhereIsMyPet.entity.petlocation.PetLocation;
import com.unesc.net.WhereIsMyPet.entity.petlocation.SavePetLocationDTO;
import com.unesc.net.WhereIsMyPet.repository.pet.PetRepository;
import com.unesc.net.WhereIsMyPet.repository.petlocation.PetLocationRepository;
import com.unesc.net.WhereIsMyPet.resources.petlocation.PetLocationDto;
import com.unesc.net.WhereIsMyPet.service.pet.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetLocationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PetService petService;

    @Autowired
    private PetLocationRepository petLocationRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLocation(SavePetLocationDTO savePetLocationDTO) {
        Pet pet = this.petService.findByTelefone(savePetLocationDTO.numero());
        if (pet != null) {

            PetLocation petLocation = PetLocation.builder()
                    .pet(pet)
                    .dataHoraPosicao(LocalDateTime.now())
                    .latitude(savePetLocationDTO.latitude())
                    .longitude(savePetLocationDTO.longitude())
                    .bateria(savePetLocationDTO.bateria())
                    .build();

            this.petLocationRepository.save(petLocation);
        }
    }

    public PetLocationDto[] getLocations() {
        List<PetLocation> locations = new ArrayList<>();
        for (Pet pet : this.petService.getPets()) {
            locations.add(this.petLocationRepository.findPetLocationByPetId(pet.getId()));
        }
        return this.modelMapper.map(locations, PetLocationDto[].class);
    }

}
