package com.example.demo.Repositories;

import com.example.demo.Domain.Entities.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetImageRepository extends JpaRepository<PetImage, Integer>, PagingAndSortingRepository<PetImage, Integer> {
    List<PetImage> getPetImagesByPetId(int petId);
}
