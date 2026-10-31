package PracticeProblems.PPWeek7;


interface Week7P4Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class Week7P4MediaFile {
    private static int counter = 1000;
    private final String fileId;

    Week7P4MediaFile() {
        fileId = "MF-" + (++counter);
    }

    abstract String getFormatInfo();

    String getFileId() {
        return fileId;
    }
}

class Week7P4AudioFile extends Week7P4MediaFile
    implements Week7P4Playable {

    String title;

    Week7P4AudioFile(String title) {
        this.title = title;
    }

    public String play() {
        return "Playing audio: " + title;
    }

    public String play(int fromSecond) {
        return "Playing audio: " + title + " from " +
            (fromSecond / 60) + ":" +
            String.format("%02d", fromSecond % 60);
    }

    public String pause() {
        return "Audio paused";
    }

    String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Week7P4Podcast implements Week7P4Playable {
    String showName;
    int episodeNumber;

    Week7P4Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    public String play() {
        return "Streaming episode " + episodeNumber +
            " of " + showName;
    }

    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber +
            " of " + showName + " from " + fromSecond + " seconds";
    }

    public String pause() {
        return "Podcast paused";
    }
}

public class Prob4 {
    static void launchAll(Week7P4Playable[] items) {
        for (Week7P4Playable p : items)
            System.out.println(p.play());
    }

    public static void main(String[] args) {
        Week7P4AudioFile a =
            new Week7P4AudioFile("Morning Jazz");

        Week7P4Podcast p =
            new Week7P4Podcast("Tech Talk", 12);

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());
        System.out.println(p.play());

        Week7P4Playable ref = a; // Upcasting
        launchAll(new Week7P4Playable[]{ref, p});
    }
}