package mazegamecoursework.Objects;

public class Settings {
    private static String email;
    private static double volume = 50;
    private static String colour = "White";
    private static String name;
    private static double score;

    public static double getVolume() {
        return volume;
    }

    public static String getColour() {
        return colour;
    }

    public static void setColour(String colour) {
        Settings.colour = colour;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Settings.email = email;
    }

    public static void setVolume(double volume) {
        Settings.volume = volume;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Settings.name = name;
    }

    public static double getScore() {
        return score;
    }

    public static void setScore(double score) {
        Settings.score = score;
    }
}
