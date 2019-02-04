package homeWorke19;

public class Main  {
    public static void main(String[] args) {
        DataFile dataFile = new DataFile();
       new Thread(new Boss(dataFile), "Король").start();
      new Thread(new Writer(dataFile), "Слуга").start();
    }
}
