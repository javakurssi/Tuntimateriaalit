package tunti8.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tunti8.thread.apuluokat.IDConsumerCallable;
import tunti8.thread.apuluokat.IDGenerator;
import tunti8.thread.apuluokat.IDGeneratorSync;

/**
 * Esimerkki ExecutorService ja Callable, jossa threadi palauttaa tuloksen.
 */
public class CallableEsimerkki {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	
        int threadCount = 5;
        
        List<Callable<Integer>> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(new IDConsumerCallable(10));
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        List<Future<Integer>> futures = executor.invokeAll(threads); // Palautetaan futureja, eli lupaus arvosta tulevaisuudessa.
        		
        int resultId = 0;
        for (Future<Integer> future : futures) {
            resultId = future.get(); // Future palautuu vasta kun Threadin suoritus on valmis, blokkaa pääthreadia siihen asti.
            System.out.println("ID säikeen päättyessä: " + resultId);
        }
        // Executorin sulkeminen
        executor.shutdown();
        
        //Tarkistetaan Singleton IDGeneratorin lopputulos, jota eri säikeet ovat muokanneet.
        IDGeneratorSync idGenerator = IDGeneratorSync.getIDGenerator();
        int finalId = idGenerator.getLastId();
        System.out.println("Lopullinen ID: " + finalId);
    }
}
