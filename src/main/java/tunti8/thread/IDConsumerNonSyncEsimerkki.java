package tunti8.thread;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import tunti8.thread.apuluokat.IDGenerator;

/**
 *  Esimerkki epäturvallisesta threadittamisesta, jossa useampi säie pääsee mahdollisesti muokkaamaan samaa
 *  muistialuetta ja syntyy yllättäviä tuloksia.
 *  
 *  Oikea tapa on käyttää koodilohkon lukitsemista Syncronized avainsanalla, 
 *  tämä tehdään IDGeneratorSync-luokan avulla tämän esimerkin kommentoiduissa osissa.
 */

public class IDConsumerNonSyncEsimerkki implements Runnable {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		IDConsumerNonSyncEsimerkki idc1 = new IDConsumerNonSyncEsimerkki(5, false); //Jos nämä kasvattaa 5 -> 50, niin alkaa tulla virheitä,
		IDConsumerNonSyncEsimerkki idc2 = new IDConsumerNonSyncEsimerkki(5, false); //eli ei tulekkaan välttämättä 100 viimeiseksi ID:ksi
		Thread t1 = new Thread(idc1);
		Thread t2 = new Thread(idc2);
		t1.start();
		t2.start();
		try {
		    t1.join();
		    t2.join();
		} catch (InterruptedException e) {
		}
		IDGenerator idg = IDGenerator.getIDGenerator();
		//IDGeneratorSync idg = IDGeneratorSync.getIDGenerator();

		// seuraava id pitäisi olla 2 kertaa 5 eli 10
		int id = idg.getLastId();
        
		System.out.println("ID: " + id); // tulostaa ID: 10
	}

    private int counter; // montako kertaan pyydetään uusi ID
    private boolean useDelay; // simuloidaanko kuormaa viiveellä

    public IDConsumerNonSyncEsimerkki(int counter, boolean useDelay) {
        this.counter = counter;
        this.useDelay = useDelay;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        IDGenerator idg = IDGenerator.getIDGenerator();
        // Oikea tapa on käyttää koodilohkon lukitsemista Syncronized avainsanalla, tämä tehdään IDGeneratorSync-luokassa
        // IDGeneratorSync idg = IDGeneratorSync.getIDGenerator();
        int id = 0; 
        for (int i = 0; i < counter; i++) {
            id = idg.nextID();
            if (useDelay) {
                try {
                    Thread.sleep(rnd.nextInt(50));
                } catch (InterruptedException e) {
                    // just do nothing...
                }
            }
        }
    }
}
