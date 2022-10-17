package com.example.bid_submission_rabbitmq.repository;

import com.example.bid_submission_rabbitmq.entity.Item;
import com.example.bid_submission_rabbitmq.entity.ItemRate;
import com.example.bid_submission_rabbitmq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ItemRateRepository extends JpaRepository<ItemRate, Long> {


    Optional<ItemRate> findByItemAndBidder(Item item, User bidder);


    @Query(value = """
            select sum(ir.rate)
            from item_rate ir
                     inner join users u on ir.bidder_id = u.id
                     inner join item i on ir.item_id = i.id

                     inner join tender_form tf on i.tender_form_id = tf.id
            where ir.bidder_id = :bidderId
              and tf.tender_id = :tenderId
            """, nativeQuery = true)
    BigDecimal sumUpRate(Long bidderId, Long tenderId);


}
