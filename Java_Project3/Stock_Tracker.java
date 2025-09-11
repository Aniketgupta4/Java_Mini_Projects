package Java_Project3;
import java.util.*;
import java.io.*;

class Stock implements Serializable{
    String name;
    double buyPrice;
    int quantity;
    double currentPrice;

    public Stock(String name,double buyPrice,int quantity){
        this.name = name;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.currentPrice = buyPrice;
    }

    public void updatePrice(double price){
        this.currentPrice = price;
    }

    public double getInvestmentValue(){
        return buyPrice * quantity;
    }

    public double getCurrentValue(){
        return currentPrice * quantity;
    }

    public double getProfitLoss(){
        return getCurrentValue() - getInvestmentValue();
    }

    @Override 
    public String toString(){
        return name + " | Qty: " + quantity +
               " | Buy@" + buyPrice +
               " | Current@" + currentPrice +
               " | P/L: " + String.format("%.2f", getProfitLoss());
    }
}


public class Stock_Tracker {
    
   private static final String FILE_NAME = "portfolio.dat";
   private static Map<String,Stock> portfolio = new HashMap<>();
   
   public static void main(String[] args) {
        
        loadPortfolio();
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("\n==== STOCK PORTFOLIO TRACKER ====");
            System.out.println("1. Add Stock");
            System.out.println("2. Remove Stock");
            System.out.println("3. Update Stock Price");
            System.out.println("4. View Portfolio");
            System.out.println("5. Save & Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStock(sc); break;
                case 2: removeStock(sc); break;
                case 3: updatePrice(sc); break;
                case 4: showPortfolio(); break;
                case 5: savePortfolio(); 
                        System.out.println("💾 Portfolio saved. Exiting...");
                        return;
                default: System.out.println("❌ Invalid choice!");
        }
   }

}

   private static void addStock(Scanner sc) {
        System.out.print("Enter stock name: ");
        String name = sc.nextLine();
        System.out.print("Enter buy price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        portfolio.put(name, new Stock(name, price, qty));
        System.out.println("✅ Stock added!");
    }


    
    private static void removeStock(Scanner sc) {
        System.out.print("Enter stock name to remove: ");
        String name = sc.nextLine();
        if (portfolio.containsKey(name)) {
            portfolio.remove(name);
            System.out.println("✅ Stock removed!");
        } else {
            System.out.println("❌ Stock not found!");
        }
    }



  private static void updatePrice(Scanner sc) {
        System.out.print("Enter stock name: ");
        String name = sc.nextLine();
        if (portfolio.containsKey(name)) {
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            portfolio.get(name).updatePrice(price);
            System.out.println("✅ Price updated!");
        } else {
            System.out.println("❌ Stock not found!");
        }
    }  


  private static void showPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("📭 No stocks in portfolio.");
            return;
        }

        double totalInvestment = 0, totalCurrent = 0;

        System.out.println("\n---- Your Portfolio ----");
        for (Stock s : portfolio.values()) {
            System.out.println(s);
            totalInvestment += s.getInvestmentValue();
            totalCurrent += s.getCurrentValue();
        }  
        double profitLoss = totalCurrent - totalInvestment;
        System.out.println("\nTotal Investment: " + totalInvestment);
        System.out.println("Current Value   : " + totalCurrent);
        System.out.println("Net Profit/Loss : " + profitLoss);
    }

     private static void savePortfolio() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(portfolio);
        } catch (IOException e) {
            System.out.println("❌ Error saving portfolio: " + e.getMessage());
        }
    }

    private static void loadPortfolio() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            portfolio = (Map<String, Stock>) ois.readObject();
            System.out.println("💾 Portfolio loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("📭 No saved portfolio found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("❌ Error loading portfolio: " + e.getMessage());
        }
    }
}
