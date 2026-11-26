package com.tak.web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id")
    private Integer flatId;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "location")
    private String location;

    @Column(name = "rent")
    private Double rent;

    @Column(name = "size")
    private Integer size;

    @Column(name = "bedroom")
    private Integer bedroom;

    @Column(name = "washroom")
    private Integer washroom;

    @Column(name = "status")
    private String status = "Available";

    public Flat() {}

    public Flat(Integer ownerId, String location, Double rent, Integer size, Integer bedroom, Integer washroom) {
        this.ownerId = ownerId;
        this.location = location;
        this.rent = rent;
        this.size = size;
        this.bedroom = bedroom;
        this.washroom = washroom;
        this.status = "Available";
    }

    public Integer getFlatId() { return flatId; }
    public void setFlatId(Integer flatId) { this.flatId = flatId; }

    public Integer getOwnerId() { return ownerId; }
    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getRent() { return rent; }
    public void setRent(Double rent) { this.rent = rent; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public Integer getBedroom() { return bedroom; }
    public void setBedroom(Integer bedroom) { this.bedroom = bedroom; }

    public Integer getWashroom() { return washroom; }
    public void setWashroom(Integer washroom) { this.washroom = washroom; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private java.util.List<FlatImage> images = new java.util.ArrayList<>();

    public java.util.List<FlatImage> getImages() { return images; }
    public void setImages(java.util.List<FlatImage> images) { this.images = images; }

    public void addImage(String imagePath) {
        if (images == null) {
            images = new java.util.ArrayList<>();
        }
        images.add(new FlatImage(this.flatId, imagePath));
    }

    public String getFirstImage() {
        if (images != null && !images.isEmpty()) {
            return images.get(0).getImagePath();
        }
        return null;
    }
}
