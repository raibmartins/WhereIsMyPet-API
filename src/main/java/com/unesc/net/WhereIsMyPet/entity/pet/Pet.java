package com.unesc.net.WhereIsMyPet.entity.pet;

import com.unesc.net.WhereIsMyPet.entity.user.Usuario;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Entity
@Table(name = "PETS")
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "I_USUARIOS")
    private Usuario usuario;

    private String nome;

    private String telefone;

}

