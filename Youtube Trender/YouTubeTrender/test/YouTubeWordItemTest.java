import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YouTubeWordItemTest {

    @Test
    public void testDefaultConstructor() {
        YouTubeWordItem wordItem = new YouTubeWordItem("testWord");
        assertNotNull(wordItem);
        assertEquals("testWord", wordItem.getWord());
        assertEquals(0, wordItem.getCount());
        assertTrue(wordItem.getVideos().isEmpty());
    }

    @Test
    public void testIncreaseCount() {
        YouTubeWordItem wordItem = new YouTubeWordItem("testWord");
        wordItem.increaseCount();
        assertEquals(1, wordItem.getCount());
    }
    @Test
    public void testAddVideo() {
        // Create a YouTubeWordItem with a test word
        YouTubeWordItem wordItem = new YouTubeWordItem("testWord");
        // Create a YouTubeVideo
        YouTubeVideo video = new YouTubeVideo();
        // Add the video to the word item
        wordItem.addVideo(video);
        // Check if the set of videos in the word item contains the added video
        assertTrue(wordItem.getVideos().contains(video));
    }

    @Test
    public void testToString() {
        YouTubeWordItem wordItem = new YouTubeWordItem("testWord");
        wordItem.increaseCount();
        wordItem.addVideo(new YouTubeVideo());

        String expected = "YouTubeWordItem [word=testWord, count=1, videos=1]";
        assertEquals(expected, wordItem.toString());
    }
}
