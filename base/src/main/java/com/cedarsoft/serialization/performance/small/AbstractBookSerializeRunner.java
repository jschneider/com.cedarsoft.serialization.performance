package com.cedarsoft.serialization.performance.small;

import com.cedarsoft.serialization.performance.AbstractRunner;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public abstract class AbstractBookSerializeRunner extends AbstractRunner {
  @Nonnull
  private final ScenarioSize scenarioSize;

  protected AbstractBookSerializeRunner( @Nonnull ScenarioSize scenarioSize ) {
    this.scenarioSize = scenarioSize;
  }

  public void runDeserializationBenchmark() {
    Book book = new Book( "Design Patterns: Elements of Reusable Object-Oriented Software",
                          ImmutableList.of( "Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides" ),
                          38.02, Book.Rating.FOUR );

    byte[] serialized = serializeBook( book );


    runBenchmark( () -> {
      Book deserialized = deserializeBook( serialized );
      if ( !deserialized.getTitle().equals( book.getTitle() ) ) {
        throw new IllegalStateException( "Different title. Was <" + deserialized.getTitle() + "> but expected <" + book.getTitle() + ">" );
      }
    }, scenarioSize.getCount() );
  }

  /**
   * Serializes the book
   *
   * @param book the book that shall be serialized
   * @return the bytes
   */
  protected abstract byte[] serializeBook( @Nonnull Book book );

  /**
   * Deserializes the book
   *
   * @param serialized the serialized data
   */
  @Nonnull
  protected abstract Book deserializeBook( @Nonnull byte[] serialized );

  public enum ScenarioSize {
    SMALL( 10000 ),
    MEDIUM( 10000 * 10 ),
    LARGE( 10000 * 100 );
    private final int count;

    ScenarioSize( int count ) {
      this.count = count;
    }

    public int getCount() {
      return count;
    }
  }
}
