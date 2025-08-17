package com.etec.team.museum_b3.Museum.B3.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_museum_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MuseumData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 2000)
    private String text;

    @Column
    private String iconUrl;
}
