package com.eepl.lab_back.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageNumber;
    private int newsNumber;
    private String imageUrl;

    public ImageEntity(int newsNumber, String imageUrl) {
        this.newsNumber = newsNumber;
        this.imageUrl = imageUrl;
    }
}
