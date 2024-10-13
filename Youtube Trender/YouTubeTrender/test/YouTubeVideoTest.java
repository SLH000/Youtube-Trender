import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *Tests for the YouTubeVideo class.
 * Author: Ashley Ho
 */
public class YouTubeVideoTest {
    /**
     * Test the default constructor
     */
    @Test
    public void testDefaultConstructor() {
        YouTubeVideo video = new YouTubeVideo();
        assertNotNull(video);
    }

    /**
     * Test the parameterized constructor and getters
     */
    @Test
    public void testParameterizedConstructorAndGetters() {
        String channelId = "testChannel";
        String channelTitle = "Test Channel";
        Date publishedAt = new Date();
        String title = "Test Video";
        String description = "This is a test video.";
        int viewCount = 1000;

        YouTubeVideo video = new YouTubeVideo(channelId, channelTitle, publishedAt, title, description, viewCount);

        assertEquals(channelId, video.getChannelId());
        assertEquals(channelTitle, video.getChannelTitle());
        assertEquals(publishedAt, video.getPublishedAt());
        assertEquals(title, video.getTitle());
        assertEquals(description, video.getDescription());
        assertEquals(viewCount, video.getViewCount());
    }

    /**
     * Test the setters
     */
    @Test
    public void testSetters() {
        YouTubeVideo video = new YouTubeVideo();
        String channelId = "newChannel";
        String channelTitle = "New Channel";
        Date publishedAt = new Date();
        String title = "New Video";
        String description = "This is a new video.";
        int viewCount = 2000;

        video.setChannelId(channelId);
        video.setChannelTitle(channelTitle);
        video.setPublishedAt(publishedAt);
        video.setTitle(title);
        video.setDescription(description);
        video.setViewCount(viewCount);

        assertEquals(channelId, video.getChannelId());
        assertEquals(channelTitle, video.getChannelTitle());
        assertEquals(publishedAt, video.getPublishedAt());
        assertEquals(title, video.getTitle());
        assertEquals(description, video.getDescription());
        assertEquals(viewCount, video.getViewCount());
    }
}
