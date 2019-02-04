package homeWorke19;

import java.io.*;

public class Writer implements Runnable{
    private final DataFile dataFile;
    private String path = "a.txt";

    public Writer(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
       synchronized (dataFile){
           System.out.println(name + " - я жду сообжение");
           while (true){
               System.out.println(name + " - данных нет, я подожду, что то появится");
               while (dataFile.getData()==null){
                   try {
                       dataFile.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println(name + "- ваше величество минуточчччку я записываю: "   +  dataFile.getData());
               fileWriter();
               System.out.println(name + " - я записал в файл");
               dataFile.setData(null);
               dataFile.notify();

           }

       }


    }
    private void fileWriter(){
        try (FileWriter printWriter = new FileWriter(path, true)){
            printWriter.write(dataFile.getData());
            printWriter.write(System.lineSeparator());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
