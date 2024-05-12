package com.example.demo.Repositories;

import com.example.demo.Domain.Entities.CerereAdoptie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CerereAdoptieRepository extends JpaRepository<CerereAdoptie, Integer>, PagingAndSortingRepository<CerereAdoptie, Integer> {
    List<CerereAdoptie> getCerereAdoptieByUtilizatorId(int utilizatorId);
}
