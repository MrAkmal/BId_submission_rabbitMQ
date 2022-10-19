package com.example.bid_submission_rabbitmq.repository;

import com.example.bid_submission_rabbitmq.dto.item.ItemDTO;
import com.example.bid_submission_rabbitmq.dto.item.ItemRateDetailDTO;
import com.example.bid_submission_rabbitmq.entity.Item;
import com.example.bid_submission_rabbitmq.entity.TenderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findByNameAndDescriptionAndTenderForm(String name, String description, TenderForm tenderForm);


    @Query("select new com.example.bid_submission_rabbitmq.dto.item.ItemDTO(i.id,i.name,i.description,i.quantity) from Item i")
    List<ItemDTO> getAll();

    @Query("select new com.example.bid_submission_rabbitmq.dto.item.ItemDTO(i.id,i.name,i.description,i.quantity) from Item i where i.id= :itemId")
    Optional<ItemDTO> get(Long itemId);


    @Query("select new com.example.bid_submission_rabbitmq.dto.item.ItemDTO(i.id,i.name,i.description,i.quantity,ir.rate) from Item i join TenderForm tf on tf.id = i.tenderForm.id join ItemRate ir on ir.item.id=i.id and ir.bidder.id=:bidderId where tf.tender.id = :tenderId")
    List<ItemDTO> findAllByTenderId(Long bidderId, Long tenderId);


    @Query("select new com.example.bid_submission_rabbitmq.dto.item.ItemDTO(i.id,i.name,i.description,i.quantity ) from Item i join TenderForm tf on tf.id = i.tenderForm.id where tf.id = :tenderFormId")
    List<ItemDTO> findAllByTenderFormId(Long tenderFormId);

    @Query("select new com.example.bid_submission_rabbitmq.dto.item.ItemRateDetailDTO(u.name,ir.rate) from ItemRate ir join User u on u.id=ir.bidder.id where ir.item.id= :itemId and ir.bidder.id= :bidderId ")
    List<ItemRateDetailDTO> findByItemIdAndUserId(Long itemId, Long bidderId);
}
