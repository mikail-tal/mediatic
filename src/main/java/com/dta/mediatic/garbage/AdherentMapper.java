package com.dta.mediatic.garbage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.dta.mediatic.adherent.model.Adherent;

import java.util.List;

import static java.util.stream.Collectors.toList;


public final class AdherentMapper {

    static List<Adherent> mapEntitiesIntoDTOs(List<Adherent> entities) {
        return entities.stream()
                .map(AdherentMapper::mapEntityIntoDTO)
                .collect(toList());
    }

    static Adherent mapEntityIntoDTO(Adherent entity) {
        Adherent dto = new Adherent();

        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setAjour(entity.getAjour());
        dto.setNbrMedia(entity.getNbrMedia());
        return dto;
    }

   public static Page<Adherent> mapEntityPageIntoDTOPage(Pageable page, Page<Adherent> source) {
        List<Adherent> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}