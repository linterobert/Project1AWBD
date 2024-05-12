package com.example.demo.Repositories;

import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Enum.PetType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>, PagingAndSortingRepository<Pet, Integer>, CrudRepository<Pet, Integer>
{
    Optional<Pet> findById(int id);

    @Query("SELECT p FROM Pet p")
    List<Pet> getAll(Pageable p);
}
