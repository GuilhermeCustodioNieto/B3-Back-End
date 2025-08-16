package com.etec.team.museum_b3.Museum.B3.entities;

import com.etec.team.museum_b3.Museum.B3.entities.enums.Format;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_alternative")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;
    @Enumerated(EnumType.STRING)
    private Format format;

    public Alternative(String text, Format format) {
        this.text = text;
        this.format = format;
    }
}
