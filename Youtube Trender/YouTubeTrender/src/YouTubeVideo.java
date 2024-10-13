import java.util.Date;

/**
 * Represents a YouTube video with relevant attributes.
 */
public class YouTubeVideo {
    // Attributes
    private String channelId;
    private String channelTitle;
    private Date publishedAt;
    private String title;
    private String description;
    private int viewCount;

    /**
     * Default constructor.
     */
    public YouTubeVideo() {}

    /**
     * Parameterized constructor.
     * @param channelId Channel ID of the video.
     * @param channelTitle Channel title of the video.
     * @param publishedAt Published date of the video.
     * @param title Title of the video.
     * @param description Description of the video.
     * @param viewCount View count of the video.
     */
    public YouTubeVideo(String channelId, String channelTitle, Date publishedAt, String title, String description, int viewCount) {
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
    }

    /**
     * @return the channel ID of the video.
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets the channel ID of the video.
     * @param channelId Channel ID to set.
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the channel title of the video.
     */
    public String getChannelTitle() {
        return channelTitle;
    }

    /**
     * Sets the channel title of the video.
     * @param channelTitle Channel title to set.
     */
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    /**
     * @return the published date of the video.
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the published date of the video.
     * @param publishedAt Published date to set.
     */
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * @return the title of the video.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the video.
     * @param title Title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of the video.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the video.
     * @param description Description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the view count of the video.
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Sets the view count of the video.
     * @param viewCount View count to set.
     */
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}