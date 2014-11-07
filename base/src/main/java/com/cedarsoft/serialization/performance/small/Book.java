package com.cedarsoft.serialization.performance.small;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class Book {
  @Nonnull
  private String title;
  @Nonnull
  private List<String> authors;
  private double price;
  @Nonnull
  private Rating rating;

  public Book() {
  }

  public Book( @Nonnull String title, @Nonnull List<String> authors, double price, @Nonnull Rating rating ) {
    this.title = title;
    this.authors = ImmutableList.copyOf( authors );
    this.price = price;
    this.rating = rating;
  }

  @Nonnull
  public String getTitle() {
    return title;
  }

  @Nonnull
  public List<String> getAuthors() {
    //noinspection ReturnOfCollectionOrArrayField
    return authors;
  }

  public double getPrice() {
    return price;
  }

  @Nonnull
  public Rating getRating() {
    return rating;
  }

  public enum Rating {
    ONE( 1 ),
    TWO( 2 ),
    THREE( 3 ),
    FOUR( 4 ),
    FIVE( 5 );
    private final int stars;

    Rating( int stars ) {
      this.stars = stars;
    }

    public int getStars() {
      return stars;
    }
  }

}
