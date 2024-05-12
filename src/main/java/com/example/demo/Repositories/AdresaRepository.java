package com.example.demo.Repositories;

import com.example.demo.Domain.Entities.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Integer>, PagingAndSortingRepository<Adresa, Integer> {
}
