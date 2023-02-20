package com.openclassrooms.watchlist23;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



public class WatchlistItem {

    @NotBlank(message="You cannot leave it empty")
    private String title;
    private String rating;
    @NotBlank(message = "cannot leave blank")
    private String priority;

    @Size(max = 50, message = "The maximum characters are 50")
    private String comment;
    private Integer id;

    public WatchlistItem(String title, String rating, String priority, String comment, Integer id) {
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
        this.id = id;
    }

    public WatchlistItem() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
