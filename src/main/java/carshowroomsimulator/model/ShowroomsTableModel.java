package carshowroomsimulator.model;

import javafx.beans.property.*;

public class ShowroomsTableModel {
    private LongProperty showroomId;
    private StringProperty showroomName;
    private IntegerProperty showroomCapacity;
    private LongProperty reviewsCount;
    private DoubleProperty averageRating;

    public ShowroomsTableModel(long showroomId, String showroomName, int showroomCapacity, long reviewsCount, double averageRating) {
        this.showroomId = new SimpleLongProperty(showroomId);
        this.showroomName = new SimpleStringProperty(showroomName);
        this.showroomCapacity = new SimpleIntegerProperty(showroomCapacity);
        this.reviewsCount = new SimpleLongProperty(reviewsCount);
        this.averageRating = new SimpleDoubleProperty(averageRating);
    }

    public long getShowroomId() {
        return showroomId.get();
    }

    public LongProperty showroomIdProperty() {
        return showroomId;
    }

    public void setShowroomId(long showroomId) {
        this.showroomId.set(showroomId);
    }

    public String getShowroomName() {
        return showroomName.get();
    }

    public StringProperty showroomNameProperty() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName.set(showroomName);
    }

    public int getShowroomCapacity() {
        return showroomCapacity.get();
    }

    public IntegerProperty showroomCapacityProperty() {
        return showroomCapacity;
    }

    public void setShowroomCapacity(int showroomCapacity) {
        this.showroomCapacity.set(showroomCapacity);
    }

    public long getReviewsCount() {
        return reviewsCount.get();
    }

    public LongProperty reviewsCountProperty() {
        return reviewsCount;
    }

    public void setReviewsCount(long reviewsCount) {
        this.reviewsCount.set(reviewsCount);
    }

    public double getAverageRating() {
        return averageRating.get();
    }

    public DoubleProperty averageRatingProperty() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating.set(averageRating);
    }
}

