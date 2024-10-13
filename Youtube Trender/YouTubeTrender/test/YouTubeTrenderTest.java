import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for the YouTubeTrender class.
 * Author: Sunidhi Amatya
 */
public class YouTubeTrenderTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayInputStream testIn;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Test the handleUserChoices method when the user chooses to exit.
     */
    @Test
    public void testHandleUserChoicesExit() {
        String input = "5\n"; // User chooses to exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        List<YouTubeVideo> videos = new ArrayList<>();
        YouTubeTrender.handleUserChoices(scanner, videos);
        assertTrue(outContent.toString().contains("Exiting..."));
    }

    /**
     * Test the handleUserChoices method when the user enters an invalid choice and then exits.
     */
    @Test
    public void testHandleUserChoicesInvalidChoice() {
        String input = "7\n5\n"; // User enters an invalid choice, then exits
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        List<YouTubeVideo> videos = new ArrayList<>();
        YouTubeTrender.handleUserChoices(scanner, videos);
        assertTrue(outContent.toString().contains("Invalid choice. Please try again."));
    }

    /**
     * Test the loadJSONFile method by providing a valid file path.
     */
    @Test
    public void testLoadJSONFile() {
        // Prepare the test input
        String input = "YouTubeTrender/data/youtubedata_15_50.json";
        provideInput(input);

        // Call the method under test
        YouTubeTrender.loadJSONFile();

        // Capture the output
        String capturedOutput = outContent.toString();

        // Assertions
        assertEquals("YouTubeTrender/data/youtubedata_15_50.json", YouTubeTrender.filePath);
        assertTrue(capturedOutput.contains("Videos parsed successfully!"));
        assertTrue(capturedOutput.contains("Total videos: ")); // Check if it displays the total videos
    }

    /**
     * Test the displayVideo method when there are mock videos in the list.
     */
    @Test
    public void testDisplayVideoWithVideos() {
        // Prepare mock videos
        YouTubeVideo video1 = mock(YouTubeVideo.class);
        YouTubeVideo video2 = mock(YouTubeVideo.class);
        when(video1.getTitle()).thenReturn("Title 1");
        when(video2.getTitle()).thenReturn("Title 2");

        YouTubeTrender.videos.add(video1);
        YouTubeTrender.videos.add(video2);

        YouTubeTrender.displayVideo();

        String capturedOutput = outContent.toString();
        assertEquals("\nDisplaying titles of the first 5 videos:\n" +
                "1. Guy cuts down tree, but there's a surprise inside\n" +
                "2. Everything Wrong With The Good Dinosaur In 12 Minutes Or Less\n" +
                "3. Can This Australian Animal Actually Kill You?\n" +
                "4. Idk idk idk ♡ Follow Me Day 276\n" +
                "5. CATTERBOX™ - The world’s first talking cat collar (60)\n", capturedOutput);
    }
}
