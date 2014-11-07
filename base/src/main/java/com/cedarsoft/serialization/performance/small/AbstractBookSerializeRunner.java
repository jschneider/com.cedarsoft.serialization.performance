package com.cedarsoft.serialization.performance.small;

import com.cedarsoft.serialization.performance.AbstractRunner;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.concurrent.Callable;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public abstract class AbstractBookSerializeRunner extends AbstractRunner {
  @Nonnull
  private final ScenarioSize scenarioSize;
  @Nonnull
  private final Deserializer deserializer;

  protected AbstractBookSerializeRunner( @Nonnull ScenarioSize scenarioSize, @Nonnull Deserializer deserializer ) {
    this.scenarioSize = scenarioSize;
    this.deserializer = deserializer;
  }

  public void runDeserializationBenchmark() throws Exception {
    Book book = new Book( "Design Patterns: Elements of Reusable Object-Oriented Software",
                          ImmutableList.of( "Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides" ),
                          38.02, Book.Rating.FOUR );

    byte[] serialized = serializeBook( book );


    Callable<Object> objectCallable = () -> {
      Book deserialized = deserializer.deserializeBook( serialized );
      if ( !deserialized.getTitle().equals( book.getTitle() ) ) {
        throw new IllegalStateException( "Different title. Was <" + deserialized.getTitle() + "> but expected <" + book.getTitle() + ">" );
      }
      return null;
    };
    runBenchmark( objectCallable, scenarioSize.getCount() );
  }

  /**
   * Serializes the book
   *
   * @param book the book that shall be serialized
   * @return the bytes
   */
  protected abstract byte[] serializeBook( @Nonnull Book book ) throws Exception;

  public interface Deserializer {
    /**
     * Deserializes the book
     *
     * @param serialized the serialized data
     */
    @Nonnull
    Book deserializeBook( @Nonnull byte[] serialized ) throws Exception;
  }

  public enum ScenarioSize {
    SMALL( 100000 ),
    MEDIUM( 100000 * 10 ),
    LARGE( 100000 * 100 );
    private final int count;

    ScenarioSize( int count ) {
      this.count = count;
    }

    public int getCount() {
      return count;
    }
  }
}
