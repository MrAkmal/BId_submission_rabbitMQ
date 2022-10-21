package com.example.bid_submission_rabbitmq.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tender_form")
public class TenderForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Tender tender;


    @OneToMany(mappedBy = "tenderForm",cascade = CascadeType.ALL)
    private List<Item> items;

}
