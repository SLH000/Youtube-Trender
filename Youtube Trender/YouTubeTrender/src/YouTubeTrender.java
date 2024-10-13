import java.util.*;

/**
 * Main application class for the YouTube Trender.
 * Provides a menu-driven interface for users to interact with YouTube video data.
 */
public class YouTubeTrender {

    static List<YouTubeVideo> videos = null;
    static String filePath;

    public static void main(String[] args) {
        displayWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        List<YouTubeVideo> videos = new ArrayList<>();
        handleUserChoices(scanner, videos);
    }

    /**
     * Displays a welcome message to the user.
     */
    static void displayWelcomeMessage() {
        System.out.println("====================================================================================");
        System.out.println("*                                                                                  *");
        System.out.println("*                          YouTube Trender Application                             *");
        System.out.println("*                                                                                  *");
        System.out.println("====================================================================================");
        System.out.println("Welcome to YouTube Trender! \nThis application allows you to load, view, and sort YouTube video data from a JSON file. \nNavigate through the menu options to explore the features.");
    }

    /**
     * Handles user choices from the main menu.
     */
    static void handleUserChoices(Scanner scanner, List<YouTubeVideo> videos) {
        int choice = 0;

        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            if (videos == null && choice >= 2 && choice <= 4) {
                System.out.println("You must first load the YouTube json file path.");
                loadJSONFile();
            }

            switch (choice) {
                case 1:
                    loadJSONFile();
                    break;
                case 2:
                    displayVideo();
                    break;
                case 3:
                    sortVideos();
                    break;
                case 4:
                    trendAnalysis();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\nYouTube Trender Menu:");
        System.out.println("1. Load JSON file");
        System.out.println("2. Display title of the first 5 videos");
        System.out.println("3. Sort videos");
        System.out.println("4. Trending topic Analysis");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Loads YouTube video data from a specified JSON file.
     */
    static void loadJSONFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the JSON file:");
        filePath = scanner.nextLine();

        try {
            videos = YouTubeDataParser.parseVideosFromFile(filePath);
            System.out.println("\nVideos parsed successfully!");
            System.out.println("Total videos: " + videos.size());
        } catch (YouTubeDataParserException e) {
            System.out.println("Error: " + e.getMessage());
            videos = null;
        }
    }

    /**
     * Displays the titles of the first five videos from the loaded data.
     */
    static void displayVideo() {
        if (videos == null) {
            System.out.println("Please load the JSON file first.");
            return;
        }

        System.out.println("\nDisplaying titles of the first 5 videos:");
        for (int i = 0; i < 5 && i < videos.size(); i++) {
            System.out.println((i + 1) + ". " + videos.get(i).getTitle());
        }
    }

    /**
     * Provides sorting options for the videos.
     */
    static void sortVideos() {
        int sortChoice;

        do {
            displayVideosSortMenu();
            Scanner scanner = new Scanner(System.in);
            sortChoice = scanner.nextInt();
            scanner.nextLine();

            switch (sortChoice) {
                case 1:
                    videos.sort(new YouTubeVideoChannelComparator());
                    break;
                case 2:
                    videos.sort(new YoutubeVideoDateComparator());
                    break;
                case 3:
                    videos.sort(new YouTubeVideoViewComparator());
                    break;
                case 4:
                    videos.sort(new YouTubeVideoDescriptionComparator());
                    break;
                case 5:
                    videos.sort(new YouTubeVideoTitleComparator());
                    break;
                case 6:
                    System.out.println("Redirecting...");
                    break;
                default:
                    System.out.println("Invalid sorting choice.");
                    return;
            }

            if (sortChoice >= 1 && sortChoice <= 5) {
                displaySortedResults(sortChoice, scanner);
            }
        } while (sortChoice != 6);
    }

    /**
     * Displays the sorted results based on user's choice.
     *
     * @param sortChoice The sorting choice made by the user.
     * @param scanner    The scanner object for user input.
     */
    private static void displaySortedResults(int sortChoice, Scanner scanner) {
        System.out.print("Do you want to view sorted result? (Y/N): ");
        char viewChoice = scanner.nextLine().charAt(0);
        if (viewChoice == 'Y' || viewChoice == 'y') {
            displaySortHeader(sortChoice);

            for (YouTubeVideo video : videos) {
                System.out.println(video.getTitle());
                System.out.println(video.getPublishedAt());
                System.out.println(video.getViewCount());
                System.out.println();
            }
        }
    }

    /**
     * Displays the header for the sorted results.
     *
     * @param sortChoice The sorting choice made by the user.
     */
    private static void displaySortHeader(int sortChoice) {
        System.out.println("\n=======================================================================");
        switch (sortChoice) {
            case 1:
                System.out.println("*                       Sorted by channel title                       *");
                break;
            case 2:
                System.out.println("*                       Sorted by published date                      *");
                break;
            case 3:
                System.out.println("*                       Sorted by views                               *");
                break;
            case 4:
                System.out.println("*                       Sorted by description length                  *");
                break;
            case 5:
                System.out.println("*                       Sorted by video title                         *");
                break;
        }
        System.out.println("=======================================================================\n");
    }

    /**
     * Displays the sorting menu options to the user.
     */
    private static void displayVideosSortMenu() {
        System.out.println("\nSort Videos Menu");
        System.out.println("1. Sort videos by Channel's Title");
        System.out.println("2. Sort videos by Published Date");
        System.out.println("3. Sort videos by View Count");
        System.out.println("4. Sort videos by Description Length");
        System.out.println("5. Sort videos by Video's title");
        System.out.println("6. Redirect to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the trend analysis menu to the user.
     */
    private static void displayTrendMenu() {
        System.out.println("\nAnalyze Trending Topics");
        System.out.println("1. Find word and its count");
        System.out.println("2. Find all videos that use a specific word");
        System.out.println("3. Find the word that is used the most");
        System.out.println("4. Display list of words sorted by their counts");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Placeholder method for trending topic analysis.
     */
    static void trendAnalysis() {
        int trendChoice;
        // Create an instance of YouTubeIndexer
        YouTubeVideoIndexer indexer = new YouTubeVideoIndexer();

        // Index the word usage from your list of YouTubeVideo objects
        indexer.index(videos);

        do {
            displayTrendMenu();
            Scanner scanner = new Scanner(System.in);
            trendChoice = scanner.nextInt();
            scanner.nextLine();

            switch (trendChoice) {
                case 1 -> {
                    System.out.print("Enter your desired word: ");
                    String word = scanner.nextLine().toUpperCase();
                    int wordCount = indexer.getWordCount(word);
                    if (wordCount > 0) {
                        System.out.println("The word '" + word + "' is used for " + wordCount + " times.");
                    } else {
                        System.out.println("Sorry, word not found.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter your desired word: ");
                    String word = scanner.nextLine().trim();
                    List<YouTubeVideo> videosUsingWord = indexer.getVideosUsingWord(word);

                    if (!videosUsingWord.isEmpty()) {
                        System.out.println("Videos containing the word '" + word + "':");
                        for (YouTubeVideo video : videosUsingWord) {
                            System.out.println(video.getTitle());
                        }
                    } else {
                        System.out.println("Word not found.");
                    }
                }
                case 3 -> {
                    String mostUsedWord = indexer.getMostUsedWord();
                    System.out.println("The word used the most is: " + mostUsedWord);
                }
                case 4 -> {
                    List<YouTubeWordItem> sortedWords = indexer.getSortedYouTubeWordItems();
                    System.out.println("List of words sorted by their counts:");
                    for (YouTubeWordItem wordItem : sortedWords) {
                        System.out.println(wordItem.getWord() + ": " + wordItem.getCount());
                    }
                }
                case 5 -> System.out.println("Redirecting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (trendChoice != 5);
    }
}
