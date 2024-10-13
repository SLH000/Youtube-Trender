import javax.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * A utility class to parse YouTube video data from a JSON file and provide sorting functionality.
 */
public class YouTubeDataParser {

    /**
     * Parses YouTube video data from the specified JSON file.
     *
     * @param fileName The path to the JSON file.
     * @return A list of YouTubeVideo objects.
     * @throws YouTubeDataParserException If there's an issue reading or parsing the file.
     */
    public static List<YouTubeVideo> parseVideosFromFile(String fileName) throws YouTubeDataParserException {
        List<YouTubeVideo> videos = new ArrayList<>();

        try (InputStream fileStream = new FileInputStream(fileName)) {
            final JsonReader reader = Json.createReader(fileStream);
            final JsonObject jsonRoot = reader.readObject();
            final JsonArray items = jsonRoot.getJsonArray("items");

            if (items == null || items.isEmpty()) {
                throw new YouTubeDataParserException("No video data found in the JSON file.");
            }

            for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                YouTubeVideo video = new YouTubeVideo();
                JsonObject snippet = item.getJsonObject("snippet");

                video.setChannelId(getStringValue(snippet, "channelId"));
                video.setChannelTitle(getStringValue(snippet, "channelTitle"));
                video.setTitle(getStringValue(snippet, "title"));
                video.setDescription(getStringValue(snippet, "description"));
                video.setViewCount(getIntValue(item.getJsonObject("statistics"), "viewCount"));
                video.setPublishedAt(parseDate(getStringValue(snippet, "publishedAt")));

                videos.add(video);
            }
        } catch (IOException | ParseException err) {
            throw new YouTubeDataParserException("Error parsing the YouTube data", err);
        }
        return videos;
    }
    /**
     * Extracts a string value from a JsonObject based on the provided key.
     *
     * @param object The JsonObject.
     * @param key    The key to extract the value for.
     * @return The extracted string value or null if not found.
     */
    private static String getStringValue(JsonObject object, String key) {
        if (!object.containsKey(key)) {
            return null; // Return null if key doesn't exist
        }

        JsonValue value = object.get(key);
        return switch (value.getValueType()) {
            case STRING -> object.getString(key);
            case NULL -> null;
            default -> value.toString(); // Return the raw string representation for other types
        };
    }
    /**
     * Extracts an integer value from a JsonObject based on the provided key.
     *
     * @param jsonObject The JsonObject.
     * @param key        The key to extract the value for.
     * @return The extracted integer value or 0 if not found or invalid.
     */
    private static int getIntValue(JsonObject jsonObject, String key) {
        if (!jsonObject.containsKey(key)) {
            return 0; // Default value if key doesn't exist
        }

        JsonValue value = jsonObject.get(key);
        switch (value.getValueType()) {
            case NUMBER:
                return jsonObject.getInt(key);
            case STRING:
                try {
                    return Integer.parseInt(jsonObject.getString(key));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing integer from string value for key " + key + ": " + jsonObject.getString(key));
                    return 0; // Default value for invalid string
                }
            default:
                return 0; // Default value for other types
        }
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param dateString The date string in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     * @return The parsed Date object.
     * @throws ParseException If there's an error parsing the date string.
     */
    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.parse(dateString);
    }
}
