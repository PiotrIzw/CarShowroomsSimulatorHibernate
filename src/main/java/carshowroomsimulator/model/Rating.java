package carshowroomsimulator.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "rating")
    private RatingEnum rating;
    @ManyToOne()
    @JoinColumn(name = "showroom_id", nullable = false)
    private CarShowroom showroom;
    private String description;
    private java.sql.Timestamp reviewDate;

    public Rating(RatingEnum rating, String description, java.sql.Timestamp reviewDate, CarShowroom carShowroom) {
        this.rating = rating;
        this.description = description;
        this.reviewDate = reviewDate;
        this.showroom = carShowroom;
    }

    public Rating(){

    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(java.sql.Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }
}
