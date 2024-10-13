import java.util.HashSet;
import java.util.Set;

/**
 * Represents an indexed word from YouTube video titles and descriptions.
 * It encapsulates the word itself, its count of occurrences, the videos where it appears,
 * and the associated record IDs.
 */
public class YouTubeWordItem {
    private final String word;
    private int count;
    private final Set<YouTubeVideo> videos;

    /**
     * Constructor for the YouTubeWordItem.
     *
     * @param word The word being indexed.
     */
    public YouTubeWordItem(String word) {
        this.word = word;
        this.count = 0;
        this.videos = new HashSet<>();
    }

    /**
     * Increases the count of occurrences for this word.
     */
    public void increaseCount() {
        count++;
    }

    /**
     * Adds a video to the set of videos where this word appears and updates the count.
     *
     * @param video The video where this word appears.
     */
    public void addVideo(YouTubeVideo video) {
        videos.add(video);
    }

    /**
     * Compares this YouTubeWordItem to another based on their counts.
     *
     * @param word The other YouTubeWordItem to compare to.
     * @return A negative value if this item's count is less than the other item's count,
     * a positive value if this item's count is greater than the other item's count,
     * or 0 if both counts are equal.
     */
    public int compareTo(YouTubeWordItem word) {
        return Integer.compare(word.count, this.count);
    }



    /**
     * Get the word being indexed.
     *
     * @return The word being indexed.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the count of occurrences of this word.
     *
     * @return The count of occurrences.
     */
    public int getCount() {
        return count;
    }

    /**
     * Get the set of videos where this word appears.
     *
     * @return The set of videos.
     */
    public Set<YouTubeVideo> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "YouTubeWordItem [word=" + word + ", count=" + count + ", videos=" + videos.size() + "]";
    }
}
