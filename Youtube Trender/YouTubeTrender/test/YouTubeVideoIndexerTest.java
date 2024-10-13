import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the YouTubeVideoIndexer class.
 * Author: Sunidhi Amatya
 */
public class YouTubeVideoIndexerTest {

    private YouTubeVideoIndexer indexer;
    private YouTubeVideo video1;
    private YouTubeVideo video2;

    @BeforeEach
    public void setUp() {
        indexer = new YouTubeVideoIndexer();

        video1 = new YouTubeVideo();
        video1.setTitle("Test Video One");
        video1.setDescription("Description for video one");

        video2 = new YouTubeVideo();
        video2.setTitle("Test Video Two");
        video2.setDescription("Description for video two with extra words");

        indexer.index(List.of(video1, video2));
    }

    /**
     * Test for getting sorted {@link YouTubeWordItem}s from the indexer.
     */
    @Test
    public void testGetSortedYouTubeWordItems() {
        List<YouTubeWordItem> sortedItems = indexer.getSortedYouTubeWordItems();
        assertEquals("video", sortedItems.get(0).getWord());
        assertEquals(4, sortedItems.get(0).getCount());
    }

    /**
     * Test for getting word counts from the indexer.
     */
    @Test
    public void testGetWordCounts() {
        Map<String, Integer> wordCounts = indexer.getWordCounts();
        assertEquals(4, wordCounts.get("video"));
        assertEquals(2, wordCounts.get("one")); // Updated count
    }

    /**
     * Test for getting videos associated with a word from the indexer.
     */
    @Test
    public void testGetVideosAssociatedWithWord() {
        Set<YouTubeVideo> videos = indexer.getVideosAssociatedWithWord("one");
        assertTrue(videos.contains(video1));
        assertFalse(videos.contains(video2));
    }

    /**
     * Test for getting the most used word from the indexer.
     */
    @Test
    public void testGetMostUsedWord() {
        String mostUsedWord = indexer.getMostUsedWord();
        assertEquals("video", mostUsedWord);
    }

    /**
     * Test for getting the count of a specific word from the indexer.
     */
    @Test
    public void testGetWordCount() {
        assertEquals(4, indexer.getWordCount("video"));
        assertEquals(0, indexer.getWordCount("nonexistent"));
    }

    /**
     * Test for getting videos using a specific word from the indexer.
     */
    @Test
    public void testGetVideosUsingWord() {
        List<YouTubeVideo> videos = indexer.getVideosUsingWord("two");
        assertTrue(videos.contains(video2));
        assertFalse(videos.contains(video1));
    }
}
