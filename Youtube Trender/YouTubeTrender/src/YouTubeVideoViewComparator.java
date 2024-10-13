import java.util.Comparator;

public class YouTubeVideoViewComparator implements Comparator<YouTubeVideo> {
    /**
     * Compare two YouTube videos by the view count
     * @param video1 the first object to be compared.
     * @param video2 the second object to be compared.
     * @return zero, negative or positive integer as video 1 is equal, less or greater than video 2
     */
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return Integer.compare( video2.getViewCount(), video1.getViewCount());
    }
}
