package uj.wmii.pwj.kindergarten;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Kindergarten {

    private static ChildImpl children[];

    public static void main(String[] args) throws IOException {
        init();
        final var fileName = args[0];
        System.out.println("File name: " + fileName);
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        final int N = Integer.parseInt(reader.readLine());
        children = new ChildImpl[N];
        for (int i=0; i<N; i++){
            String[] line = reader.readLine().split("\\s");
            children[i] = new ChildImpl(line[0], Integer.parseInt(line[1]));
        }
        manageChildren();
    }

    private static void init() throws IOException {
        Files.deleteIfExists(Path.of("out.txt"));
        System.setErr(new PrintStream(new FileOutputStream("out.txt")));
        new Thread(Kindergarten::runKindergarden).start();
    }

    private static void runKindergarden() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<String> errLines = Files.readAllLines(Path.of("out.txt"));
            System.out.println("Children cries count: " + errLines.size());
            errLines.forEach(System.out::println);
            System.exit(errLines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method for managing children eating
     */
    private static void manageChildren(){
        while(true){
            // find the most unhappy child that is able to eat at the moment
            AtomicInteger hungriestChildIndex = new AtomicInteger(-1);
            // this cycle is only for picking first available child as the hungriest
            for(int i=0; i<children.length; i++){
                if(!children[i].isEating().get() && !getLeftNeighbor(i).isEating().get() && !getRightNeighbor(i).isEating().get()){
                    hungriestChildIndex.set(i);
                    break;
                }
            }
            // this is the actual cycle
            if(hungriestChildIndex.get()!=-1) {
                for (int i = 0; i < children.length; i++) {
                    if(!children[i].isEating().get() && !getLeftNeighbor(i).isEating().get() && !getRightNeighbor(i).isEating().get()){
                        if(children[i].happiness() < children[hungriestChildIndex.get()].happiness()){
                            hungriestChildIndex.set(i);
                        }
//                        else if(children[i].happiness() == children[hungriestChildIndex.get()].happiness()){
//                            if(children[i].hungerSpeed() < children[hungriestChildIndex.get()].hungerSpeed()){
//                                hungriestChildIndex.set(i);
//                            }
//                        }
                    }
                }
                // command this child to eat
                try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                new Thread(()->children[hungriestChildIndex.get()].eatImpl()).start();
            }

        }
    }

    private static ChildImpl getLeftNeighbor(int i){
        if(i==0){
            return children[children.length-1];
        }
        else{
            return children[i-1];
        }
    }

    private static ChildImpl getRightNeighbor(int i){
        if(i==children.length-1){
            return children[0];
        }
        else{
            return children[i+1];
        }
    }
}
