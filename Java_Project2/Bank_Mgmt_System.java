package Java_Project2;
import java.util.*;

// BankAccount class
class BankAccount{
    private int accountNumber;
    private int accountHolder;
    private double balance;

    public BankAccount(int accountNumber,int accountHolder,int balance){
       this.accountNumber = accountNumber;
       this.accountHolder = accountHolder;
       this.balance = balance;
    }

    // deposit
    public void deposit(double amount){
        if(amount>0){
            balance += amount;
            System.out.println("Deposited :" + amount);
        }else{
            System.out.println("Invalid deposit amount!");
        }
    }


    // withdraw
    public void withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance -= amount;
            System.out.println("Withdrawn :" + amount);
        }else{
            System.out.println("Invalid or Insufficient funds!");
        }
    }


    // getbalance
    public double getBalance(){
        return balance;
    }


    // display balance
    public void displayAccount(){
        System.out.println("Account Number :" + accountNumber);
        System.out.println("Account Holder :" + accountHolder);
        System.out.println("Balance :" + balance);
    }

// get account number
    public int getAccountNumber(){
        return accountNumber;
    }

}




// Bank Class
class Bank{
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    
    // create bank account
    public void createAccount(int accNo, String Holder,double balance){
        BankAccount acc = new BankAccount(accNo, accNo, accNo);
        accounts.add(acc);
        System.out.println("Account created Successfully");
    }

    // find account
    private BankAccount findAccount(int accNo){
        for(BankAccount acc:accounts){
            if(acc.getAccountNumber() == accNo){
                return acc;
            }
        }
        return null;
    }

    
    // deposit

    public void deposit(int accNo,double amount){
        BankAccount acc = findAccount(accNo);
        if(acc!=null){
            acc.deposit(amount);
        }else{
            System.out.println("Account not found!");
        }
    }


    // withdraw
    public void withdraw(int accNo,double amount){
        BankAccount acc = findAccount(accNo);
        if(acc!=null){
            acc.withdraw(amount);
        }else{
            System.out.println("Account not found!");
        }
    }


    // check balance
    public void checkbalance(int accNo){
        BankAccount acc = findAccount(accNo);
        if(acc!=null){
            System.out.println("Current Balance: "+ acc.getBalance());
        }else{
            System.out.println("Account not found!");
        }
    }


    // display account details
    public void displayAccount(int accNo){
        BankAccount acc = findAccount(accNo);
        if(acc!=null){
            acc.displayAccount();
        }else{
            System.out.println("Account not found!");
        }
    }
}




public class Bank_Mgmt_System{
    
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do{
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Display Account");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                System.out.println("Enter Account Number");
                int accNo = sc.nextInt();
                sc.nextLine(); // newline
                System.out.print("Enter Account Holder: ");
                String holder = sc.nextLine();
                System.out.print("Enter Initial Balance: ");
                double balance = sc.nextDouble();
                bank.createAccount(accNo, holder, balance);
                break;

                   case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double depositAmt = sc.nextDouble();
                    bank.deposit(accNo, depositAmt);
                    break;

                  case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double withdrawAmt = sc.nextDouble();
                    bank.withdraw(accNo, withdrawAmt);
                    break;
                    
                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.checkbalance(accNo);
                    break;
                 
                case 5:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.displayAccount(accNo);
                    break;
                    
                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;
               
                    default:
                    System.out.println("Invalid choice!");    
                    
            }
        }while(choice!=6);
   }

}
