package com.tak.web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flat_images")
public class FlatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "flat_id", insertable = false, updatable = false)
    private Integer flatId;

    @Column(name = "image_path", length = 500)
    private String imagePath;

    public FlatImage() {}

    public FlatImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public FlatImage(Integer flatId, String imagePath) {
        this.flatId = flatId;
        this.imagePath = imagePath;
    }

    public Integer getImageId() { return imageId; }
    public void setImageId(Integer imageId) { this.imageId = imageId; }

    public Integer getFlatId() { return flatId; }
    public void setFlatId(Integer flatId) { this.flatId = flatId; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
