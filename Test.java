
public class Test{

    public Test(){};
    public void input(){
        SavingsAccount objectA = new SavingsAccount("Act-001","Mary Joy Torcende",10000,0.25);
        SavingsAccount objectB = new SavingsAccount("Act-002","Matt Plaza",5000,0.20);
        CheckingAccount objectC = new CheckingAccount("Act-003","David Andrew",20000,10000,200);

        objectA.withdraw(1000);
        objectB.deposit(5500);
        objectC.cashCheck(objectC.getAccountName(),5000);
        objectA.fundTransfer(objectB, 2000);

        System.out.println(objectA.toString());
        System.out.println(objectB.toString());
        System.out.print(objectC.toString());
    }

    public static void main(String args[]){
        Test test = new Test();
        test.input();
    }
}

class BankAccount {
    private String AccountNumber;
    private String AccountName;
    public double balance;

    public BankAccount(){}
    public BankAccount(String acctNum, String acctName, double balance){AccountNumber = acctNum; this.AccountName = countWord(acctName) > 0 ? acctName : "John Doe"; this.balance = balance;}

    public void setAccountNumber(String AccountNumber){this.AccountNumber = AccountNumber;}
    public void setAccountName(String AccountName){
        this.AccountName = countWord(AccountName) > 0 ? AccountName : "John Doe";
    }

    public int countWord(String input){
        int count = 0;

        for(int i =0;i<input.length();i++)
            if(input.charAt(i) == ' ')
                count++;
        return count;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getAccountNumber(){
        return AccountNumber;
    }

    public String getAccountName(){
        return  AccountName;
    }

    public double getBalance(){
        return balance;
    }

    public double deposit(double depositAmmount){
        return (depositAmmount < 0) ? balance : (balance += depositAmmount);
    }

    public boolean withdraw(double amount){
        if(balance < amount)
            return false;
        else{
            balance =balance - amount;
            return true;
        }
    }

    public void fundTransfer(BankAccount acc, double amount){
        balance-=amount;
        acc.balance+= amount;

    }

    public String toString(){
        return AccountNumber + ", "+ AccountName + ", "+ balance;
    }
}


class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(){}
    public SavingsAccount(String acctNum, String acctName, double balance, double interest){
        super(acctNum, acctName, balance);
        this.interestRate = interest;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return  interestRate;
    }
    public String toString(){
        return super.toString() + ", " + interestRate;
    }

    public void calculateInterest(){
        super.setBalance( getBalance() +(getBalance() * interestRate));
    }
}

class CheckingAccount extends BankAccount{
    private double minimum;
    private double charge;

    public CheckingAccount(){}
    public CheckingAccount(String acctNum, String acctName, double balance, double minimum, double charge){
        super(acctNum,acctName,balance);
        this.setMinimum(minimum);
        this.setCharge(charge);
    }

    public void setMinimum(double minimum){
        this.minimum = minimum;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public void cashCheck(String payee, double amount){
        if(amount<=super.getBalance()){
            super.setBalance(getBalance() - amount);
            if(getBalance()<minimum)
                super.setBalance(getBalance() - charge);
        }
    }

    public String toString(){
        return super.toString() + ", " + minimum + ", " + charge;
    }
}

