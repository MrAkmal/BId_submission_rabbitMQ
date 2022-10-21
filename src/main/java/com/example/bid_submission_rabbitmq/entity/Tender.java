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
@Table(name = "tender")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String description;


    @OneToMany(mappedBy = "tender", cascade = CascadeType.ALL)
    private List<TenderForm> tenderForms;


    @OneToMany(mappedBy = "tender", cascade = CascadeType.ALL)
    private List<BidSubmission> bidSubmissions;


}
