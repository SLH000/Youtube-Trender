import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.json.stream.JsonParsingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the YouTubeDataParser class.
 * Author: Sunidhi Amatya
 */
public class YouTubeDataParserTest {

    private YouTubeDataParser parser;

    @BeforeEach
    void setUp() {
        parser = new YouTubeDataParser();
    }

    /**
     * Test the parseVideosFromFile method with a valid JSON file.
     *
     * @throws YouTubeDataParserException If parsing fails.
     */
    @DisplayName("Testing the parse method")
    @Test
    public void testParse() throws YouTubeDataParserException {
        String fileName = "YouTubeTrender/data/youtubedata.json";
        YouTubeDataParser instance = new YouTubeDataParser();
        List<YouTubeVideo> result = instance.parseVideosFromFile(fileName);
        assertNotNull(result);
    }

    /**
     * Test parsing a valid JSON file and checking the parsed video's attributes.
     */
    @DisplayName("Test parsing valid JSON file")
    @Test
    void testParseValidFile() {
        String fileName = "YouTubeTrender/data/youtubedata.json";
        try {
            List<YouTubeVideo> result = parser.parseVideosFromFile(fileName);
            assertEquals(1, result.size());
            YouTubeVideo video = result.get(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date expectedDate = dateFormat.parse("Wed Apr 20 23:15:17 ACST 2016");

            assertEquals("UCehf4850q1L3ng7s7L54ATA", video.getChannelId());
            assertEquals(expectedDate, video.getPublishedAt()); // Compare dates as Date objects
            assertEquals("This should have a really useful title", video.getTitle());
            assertEquals("This should have a really useful description.  However lots of youtubers put links and other promotional material.", video.getDescription());
            assertEquals(14180950, video.getViewCount());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Parsing failed with exception: " + e.getMessage());
        }
    }

    /**
     * Test parsing a non-existent JSON file and expect an exception.
     */
    @DisplayName("Test parsing non-existent file")
    @Test
    void testParseNonExistentFile() {
        String fileName = "YouTubeTrender/data/youtubedata_singleitem.json";
        assertThrows(YouTubeDataParserException.class, () -> parser.parseVideosFromFile(fileName));
    }

    /**
     * Test parsing an invalid JSON file and expect a JSON parsing exception.
     */
    @DisplayName("Test parsing invalid JSON file")
    @Test
    void testParseInvalidFile() {
        String fileName = "YouTubeTrender/data/youtubedata_malformed.json";
        assertThrows(JsonParsingException.class, () -> parser.parseVideosFromFile(fileName));
    }

    /**
     * Test parsing a JSON file with null values and check if attributes are not null.
     *
     * @throws YouTubeDataParserException If parsing fails.
     */
    @DisplayName("Testing the parse method with null values")
    @Test
    public void testParseWithNullValues() throws YouTubeDataParserException {
        String fileName = "YouTubeTrender/data/youtubedata_15_50.json";
        List<YouTubeVideo> result = parser.parseVideosFromFile(fileName);

        // Assuming the file contains 50 videos with null values
        assertEquals(50, result.size());
        YouTubeVideo video = result.get(0);
        assertNotNull(video.getChannelId());
        assertNotNull(video.getPublishedAt());
        assertNotNull(video.getTitle());
        assertNotNull(video.getDescription());
        assertEquals(14187775, video.getViewCount());
    }
}
