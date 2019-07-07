package cz.cellar.springreview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="review_id")
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="item_id")
    private Long itemId;
    @NotBlank
    @Column(name="text_short")
    private String textShort;
    @NotBlank
    @Column(name="text_long")
    private String textLong;

    public Review(){}

    public Review(@JsonProperty("userId")Long userId,@JsonProperty("itemId") Long itemId, @JsonProperty("textShort")String textShort,@JsonProperty("textLong") String textLong) {
        this.userId = userId;
        this.itemId = itemId;
        this.textShort = textShort;
        this.textLong = textLong;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getTextShort() {
        return textShort;
    }

    public String getTextLong() {
        return textLong;
    }

    public void setTextShort(String textShort) {
        this.textShort = textShort;
    }

    public void setTextLong(String textLong) {
        this.textLong = textLong;
    }
}
