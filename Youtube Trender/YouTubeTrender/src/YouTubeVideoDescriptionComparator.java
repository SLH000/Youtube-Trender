import java.util.Comparator;

public class YouTubeVideoDescriptionComparator implements Comparator<YouTubeVideo> {
    /**
     * Compare two YouTube videos by the length of their description
     * @param video1 the first object to be compared.
     * @param video2 the second object to be compared.
     * @return zero, negative or positive integer as video 1 is equal, less or greater than video 2
     */
    @Override
    public int compare(YouTubeVideo video1, YouTubeVideo video2) {
        return Integer.compare(video1.getDescription().length(), video2.getDescription().length());
    }
}
