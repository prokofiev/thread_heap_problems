package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemoryProblems {
  public static void main(String[] args) throws InterruptedException {
    new MemoryProblems().execute();
  }

  public void execute() throws InterruptedException {
    List<DataItem> full = new LinkedList<>();
    Provider provider = new Provider(100);
    while(provider.hasNext()) {
      List<DataItem> data = provider.getNext();
      for(DataItem s : data) {
        System.out.println(s);
      }
      full.addAll(data);
//      Thread.sleep(10);
    }
  }

  private class Provider {
    private static final int ITEMS_COUNT = 1000000;
    private final int partSize;
    private int currentCount = 0;

    public Provider(int partSize) {
      this.partSize = partSize;
    }

    public boolean hasNext() {
      return currentCount < ITEMS_COUNT;
    }
    public List<DataItem> getNext() {
      ArrayList<DataItem> list = new ArrayList<>(partSize);
      for(int i = 0; i < partSize; i++) {
        list.add(new DataItem(currentCount + i, new byte[100]));
      }
      currentCount += partSize;
      return list;
    }
  }
  private class DataItem {
    Integer number;
    byte[] data;

    public DataItem(Integer number, byte[] data) {
      this.number = number;
      this.data = data;
    }

    public Integer getNumber() {
      return number;
    }

    public byte[] getData() {
      return data;
    }

    @Override
    public String toString() {
      return "DataItem{" +
          "number=" + number +
          '}';
    }
  }
}
