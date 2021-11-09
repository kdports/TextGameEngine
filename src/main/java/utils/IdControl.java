package utils;

public class IdControl {
    private static int id = 0;

    public static int getId() { return id; }
    public static void addOne() { id++; }
}
