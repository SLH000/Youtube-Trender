# YouTube Trender

YouTube Trender is a command-line application designed to analyze YouTube search results and detect trending topics using the YouTube Data API. The application retrieves a list of videos from YouTube based on a search query, parses the video data, sorts videos by different attributes, and identifies trending topics by analyzing word usage in video titles and descriptions.

## Project Overview

YouTube is a video-sharing platform that allows users to upload, view, and interact with video content. This project uses the YouTube Data API to extract metadata (e.g., titles, descriptions, views) from videos and identify trending topics based on frequently appearing words.

## Key features of the YouTube Trender include:

Parsing YouTube video data into structured objects.
Sorting videos by attributes like views, upload date, and title.
Analyzing and displaying trending topics based on word frequency.
Offering a command-line interface (CLI) for interacting with the application.
Features

### 1. Parse YouTube Video Data
The application parses raw data from the YouTube Data API into structured YouTube Video objects. These objects contain relevant metadata such as:

Title of the video
Channel Title
Number of Views
Upload Date
Description
### 2. Sort Videos
Users can sort the video objects by the following attributes:

Views: Sort videos based on the number of views.
Upload Date: Sort by the date the video was uploaded.
Title: Sort alphabetically by the video title.
Channel Title: Sort alphabetically by the channel name.
### 3. Detect Trending Topics
The application analyzes the video titles and descriptions to identify the most frequently occurring words, which represent trending topics. It ignores common stopwords to focus on more meaningful terms.

### 4. Command-Line Interface (CLI)
The CLI allows users to:

Load YouTube video data based on a search query.
Sort videos by different attributes (e.g., views, upload date).
Display trending topics based on word usage in titles and descriptions.