import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the YouTubeVideoComparators class.
 * Author: Sunidhi Amatya
 */
public class YouTubeVideoComparatorsTest {

    private List<YouTubeVideo> videos;
    private Date date1, date2, date3;

    @BeforeEach
    public void setUp() throws ParseException {
        // Create sample date objects for testing
         date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2016-04-20T23:15:17.000Z");
         date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2016-04-21T12:30:45.000Z");
         date3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2016-04-22T08:45:00.000Z");

        // Create a list of YouTubeVideo objects with mock data
        videos = new ArrayList<>();
        videos.add(new YouTubeVideo("channel1", "ChannelTitle 1", date1, "Title 1", "Description 1", 1000));
        videos.add(new YouTubeVideo("channel2", "ChannelTitle 2", date2, "Title 2", "Description 2", 500));
        videos.add(new YouTubeVideo("channel3", "ChannelTitle 3", date3, "Title 3", "Description 3", 800));
    }

    /**
     * Test the YouTubeVideoChannelComparator to ensure videos are sorted by channel title in ascending order.
     */
    @Test
    public void testYouTubeVideoChannelComparator() {
        // Sort the videos using YouTubeVideoChannelComparator
        Collections.sort(videos, new YouTubeVideoChannelComparator());

        // Verify that videos are sorted by channel title in ascending order
        assertEquals("ChannelTitle 1", videos.get(0).getChannelTitle());
        assertEquals("ChannelTitle 2", videos.get(1).getChannelTitle());
        assertEquals("ChannelTitle 3", videos.get(2).getChannelTitle());
    }

    /**
     * Test the YoutubeVideoDateComparator to ensure videos are sorted by published date in ascending order.
     */
    @Test
    public void testYouTubeVideoDateComparator() {

        // Sort the videos using YoutubeVideoDateComparator
        Collections.sort(videos, new YoutubeVideoDateComparator());

        // Verify that videos are sorted by published date in ascending order
        assertEquals(date1, videos.get(0).getPublishedAt());
        assertEquals(date2, videos.get(1).getPublishedAt());
        assertEquals(date3, videos.get(2).getPublishedAt());
    }

    /**
     * Test the YouTubeVideoViewComparator to ensure videos are sorted by view count in descending order.
     */
    @Test
    public void testYouTubeVideoViewComparator() {
        // Sort the videos using YouTubeVideoViewComparator
        Collections.sort(videos, new YouTubeVideoViewComparator());

        // Verify that videos are sorted by view count in descending order
        assertEquals(1000, videos.get(0).getViewCount());
        assertEquals(800, videos.get(1).getViewCount());
        assertEquals(500, videos.get(2).getViewCount());
    }

    /**
     * Test the YouTubeVideoDescriptionComparator to ensure videos are sorted by description length in ascending order.
     */
    @Test
    public void testYouTubeVideoDescriptionComparator() {
        // Sort the videos using YouTubeVideoDescriptionComparator
        Collections.sort(videos, new YouTubeVideoDescriptionComparator());

        // Verify that videos are sorted by description length in ascending order
        assertEquals("Description 1", videos.get(0).getDescription());
        assertEquals("Description 2", videos.get(1).getDescription());
        assertEquals("Description 3", videos.get(2).getDescription());
    }

    /**
     * Test the YouTubeVideoTitleComparator to ensure videos are sorted by video title in ascending order.
     */
    @Test
    public void testYouTubeVideoTitleComparator() {
        // Sort the videos using YouTubeVideoTitleComparator
        Collections.sort(videos, new YouTubeVideoTitleComparator());

        // Verify that videos are sorted by video title in ascending order
        assertEquals("Title 1", videos.get(0).getTitle());
        assertEquals("Title 2", videos.get(1).getTitle());
        assertEquals("Title 3", videos.get(2).getTitle());
    }
}
