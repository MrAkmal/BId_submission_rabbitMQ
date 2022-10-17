package com.example.bid_submission_rabbitmq.repository;


import com.example.bid_submission_rabbitmq.dto.tender.TenderDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TenderRepository extends JpaRepository<Tender, Long> {


    @Query("select new com.example.bid_submission_rabbitmq.dto.tender.TenderDTO(t.id,t.description) from Tender t")
    List<TenderDTO> getAll();

    @Query("select new com.example.bid_submission_rabbitmq.dto.tender.TenderDTO(t.id,t.description) from Tender t where t.id =:tenderId ")
    Optional<TenderDTO> get(Long tenderId);


}
