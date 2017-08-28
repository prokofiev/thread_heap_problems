package sample;

public class Deadlock {

  public static void main(String args[]) {
    new Deadlock().execute();
  }

  public void execute() {
    final Wallet firstWallet = new Wallet(1);
    final Wallet secondWallet = new Wallet(2);
    FundsTransfer T1 = new FundsTransfer(firstWallet, secondWallet);
    FundsTransfer T2 = new FundsTransfer(secondWallet, firstWallet);
    T1.start();
    T2.start();
  }

  private static class FundsTransfer extends Thread {
    private final Wallet source;
    private final Wallet target;

    public FundsTransfer(Wallet source, Wallet target) {
      this.source = source;
      this.target = target;
    }

    public void run() {
      synchronized (source) {
        System.out.printf("FundsTransfer: Заблокировали кошелек отправителя %d...\n", source.getNumber());
        try {
          Thread.sleep(10);
        } catch (InterruptedException ignored) {
        }
        System.out.printf("FundsTransfer: Ждем блокировки кошелька получателя %d...\n", target.getNumber());
        synchronized (target) {
          System.out.printf("FundsTransfer: Заблокировали оба кошелька %d и %d...\n", source.getNumber(), target.getNumber());
        }
      }
    }
  }

  private class Wallet {
    private final int number;

    public Wallet(int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }
  }
}
