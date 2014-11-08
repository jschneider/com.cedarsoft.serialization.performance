package com.cedarsoft.serialization.performance.small;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
public class PullApiBookSerializerRunner extends AbstractBookSerializeRunner {
  public PullApiBookSerializerRunner() {
    super( ScenarioSize.MEDIUM, new Deserializer() {
      @Nonnull
      private final JsonFactory factory = new JsonFactory();

      @Nonnull
      @Override
      public Book deserializeBook( @Nonnull byte[] serialized ) throws IOException {
        JsonParser parser = factory.createParser( serialized );
        assertThat( parser.nextToken() ).isSameAs( JsonToken.START_OBJECT );

        String title = null;
        ImmutableList.Builder<String> authors = ImmutableList.<String>builder();
        double price = 0;
        Book.Rating rating = null;

        while ( parser.nextToken() != JsonToken.END_OBJECT ) {
          String currentName = parser.getCurrentName();

          switch ( currentName ) {
            case "title":
              assertThat( parser.nextToken() ).isSameAs( JsonToken.VALUE_STRING );
              title = parser.getText();
              break;
            case "authors":
              assertThat( parser.nextToken() ).isSameAs( JsonToken.START_ARRAY );

              while ( parser.nextToken() != JsonToken.END_ARRAY ) {
                assertThat( parser.nextToken() ).isSameAs( JsonToken.VALUE_STRING );
                authors.add( parser.getText() );
              }

              break;
            case "price":
              assertThat( parser.nextToken() ).isSameAs( JsonToken.VALUE_NUMBER_FLOAT );
              price = parser.getDoubleValue();
              break;
            case "rating":
              assertThat( parser.nextToken() ).isSameAs( JsonToken.VALUE_STRING );
              rating = Book.Rating.valueOf( parser.getText() );
              break;
          }
        }

        return new Book( title, authors.build(), price, rating );
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
    new PullApiBookSerializerRunner().runDeserializationBenchmark();
  }
}
