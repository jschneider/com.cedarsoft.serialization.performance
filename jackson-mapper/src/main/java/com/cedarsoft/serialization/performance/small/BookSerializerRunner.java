package com.cedarsoft.serialization.performance.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class BookSerializerRunner extends AbstractBookSerializeRunner {
  public BookSerializerRunner() {
    super( ScenarioSize.SMALL, new Deserializer() {
      @Nonnull
      @Override
      public Book deserializeBook( @Nonnull byte[] serialized ) {
        return new Book( "Design Patterns: Elements of Reusable Object-Oriented Software", ImmutableList.<String>of(), 12.0, Book.Rating.FOUR );
      }
    } );
  }

  @Override
  protected byte[] serializeBook( @Nonnull Book book ) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    mapper.writeValue( out, book );

    return out.toByteArray();
  }

  public static void main( String[] args ) throws Exception {
    new BookSerializerRunner().runDeserializationBenchmark();
  }
}
