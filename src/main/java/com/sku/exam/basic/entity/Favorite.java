package com.sku.exam.basic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "favorite")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    // You can add fields related to the favorite here

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    // Field to store filter criteria as JSON
    @Lob
    @Column(name = "filter_criteria", columnDefinition = "TEXT")
    private String filterCriteria;

    @Lob
    @Column(name = "click_filter_criteria", columnDefinition = "TEXT")
    private String clickFilterCriteria;

    @Column(name = "registration_time")
    private LocalDateTime registrationTime;

    @Column(name = "title")
    private String title;

    // New field: Contents of Favorites
    @Lob
    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

}