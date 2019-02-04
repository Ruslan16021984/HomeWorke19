package homeWorke19;

import java.io.*;
import java.util.Scanner;

public class Boss implements Runnable{
    private final DataFile dataFile;

    public Boss(DataFile dataFile) {
        this.dataFile = dataFile;
    }


    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        Scanner scanner = new Scanner(System.in);

        synchronized (dataFile){
            while (true){
                System.out.println(name + " - записывай мою волю:");
                dataFile.setData(scanner.nextLine());
                //System.out.println(name + " данные передали, можно и отдохнуть");
                dataFile.notify();
                try {
                    dataFile.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}
