package com.codereview.linkgenerator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Short")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String link;
    private String original;
    private int rank;
    private int count;
}
