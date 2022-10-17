package com.example.bid_submission_rabbitmq.repository;


import com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO;
import com.example.bid_submission_rabbitmq.entity.Tender;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TenderFormRepository extends JpaRepository<TenderForm, Long> {


    Optional<TenderForm> findByNameAndTender(String name, Tender tender);


    @Query("select new com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO(t.id,t.name) from TenderForm t ")
    List<TenderFormDTO> getAll();


    @Query("select new com.example.bid_submission_rabbitmq.dto.tenderform.TenderFormDTO(t.id,t.name) from TenderForm t  where t.id = :tenderFormId")
    Optional<TenderFormDTO> get(Long tenderFormId);
}
