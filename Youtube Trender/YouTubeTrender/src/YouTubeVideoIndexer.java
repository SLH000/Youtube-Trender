import java.util.*;

/**
 * The YouTubeVideoIndexer class is responsible for indexing YouTube video objects based on their titles and descriptions,
 * and providing various methods to retrieve information about indexed words.
 */
public class YouTubeVideoIndexer {
    private final List<YouTubeWordItem> items = new ArrayList<>();
    private final Map<String, YouTubeWordItem> words = new HashMap<>();

    /**
     * Indexes the words from YouTube video titles and descriptions and associates them with the corresponding videos.
     *
     * @param videos The list of YouTubeVideo objects to be indexed.
     */
    public void index(List<YouTubeVideo> videos) {
        for (YouTubeVideo video : videos) {
            String[] titleWords = video.getTitle().split("\\W+");
            String[] descriptionWords = video.getDescription().split("\\W+");

            indexWords(titleWords, video);
            indexWords(descriptionWords, video);
        }
    }

    private void indexWords(String[] wordArray, YouTubeVideo video) {
        for (String word : wordArray) {
            word = word.toLowerCase(); // Convert to lowercase for consistent indexing
            YouTubeWordItem wordItem = words.get(word);
            if (wordItem == null) {
                wordItem = new YouTubeWordItem(word);
                words.put(word, wordItem);
                items.add(wordItem);
            }
            wordItem.increaseCount();
            wordItem.addVideo(video);
        }
    }
    /**
     * Gets a list of YouTubeWordItem objects sorted by their word counts in descending order.
     *
     * @return A sorted list of YouTubeWordItem objects.
     */
    public List<YouTubeWordItem> getSortedYouTubeWordItems() {
        items.sort(Comparator.comparingInt(YouTubeWordItem::getCount).reversed());
        return items;
    }

    /**
     * Gets a map of words and their associated counts.
     *
     * @return A map where keys are words, and values are their counts.
     */
    public Map<String, Integer> getWordCounts() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (YouTubeWordItem item : items) {
            wordCountMap.put(item.getWord(), item.getCount());
        }
        return wordCountMap;
    }
    /**
     * Gets a set of YouTubeVideo objects associated with a specific word.
     *
     * @param word The word to search for.
     * @return A set of YouTubeVideo objects associated with the given word.
     */
    public Set<YouTubeVideo> getVideosAssociatedWithWord(String word) {
        YouTubeWordItem wordItem = words.get(word.toLowerCase());
        if (wordItem != null) {
            return wordItem.getVideos();
        }
        return new HashSet<>(); // Return empty set if word not found
    }
    /**
     * Gets the most frequently used word based on word counts.
     *
     * @return The most frequently used word.
     */
    public String getMostUsedWord() {
        YouTubeWordItem mostUsedItem = Collections.max(items, Comparator.comparingInt(YouTubeWordItem::getCount));
        return mostUsedItem.getWord();
    }
    /**
     * Gets the count of occurrences of a specific word.
     *
     * @param word The word to get the count for.
     * @return The count of occurrences of the word.
     */
    public int getWordCount(String word) {
        YouTubeWordItem wordItem = words.get(word.toLowerCase());
        if (wordItem != null) {
            return wordItem.getCount();
        }
        return 0; // Return 0 if word not found
    }
    /**
     * Gets a list of YouTubeVideo objects that use a specific word.
     *
     * @param word The word to search for in video titles and descriptions.
     * @return A list of YouTubeVideo objects that use the given word.
     */
    public List<YouTubeVideo> getVideosUsingWord(String word) {
        YouTubeWordItem wordItem = words.get(word.toLowerCase());
        if (wordItem != null) {
            return new ArrayList<>(wordItem.getVideos());
        }
        return new ArrayList<>(); // Return empty list if word not found
    }
}
