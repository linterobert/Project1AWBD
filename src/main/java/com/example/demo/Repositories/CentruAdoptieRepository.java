package com.example.demo.Repositories;

import com.example.demo.Domain.Entities.CentruAdoptie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentruAdoptieRepository extends JpaRepository<CentruAdoptie, Integer>, PagingAndSortingRepository<CentruAdoptie, Integer> {
}
