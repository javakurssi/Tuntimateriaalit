package tunti8.thread;

public class IDGenerator {
    private static int id = 0;
    private static final IDGenerator idg = new IDGenerator();

    private IDGenerator() { }

    public static IDGenerator getIDGenerator() {
        return idg;
    }

    public int nextID() {
        return ++id;
    }

    public int getLastId() {
        return id;
    }
}
