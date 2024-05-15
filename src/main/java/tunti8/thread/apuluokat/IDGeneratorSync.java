package tunti8.thread.apuluokat;

public class IDGeneratorSync {
    private static int id = 0;
    private static final IDGeneratorSync idg = new IDGeneratorSync();

    private IDGeneratorSync() { }

    public static IDGeneratorSync getIDGenerator() {
        return idg;
    }

    public synchronized int nextID() {
        return ++id;
    }

    public int getLastId() {
        return id;
    }
}
