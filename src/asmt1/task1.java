package asmt1;


interface PaymentMethods {
    void processPayment(int amount);
}

class CreditCardPayment implements PaymentMethods {
    public void processPayment(int amount) {
        System.out.println("Purchased by Credit card at a price of: " + amount +"$");
    }
}
class PayPalPayment implements PaymentMethods {
    public void processPayment(int amount) {
        System.out.println("Purchased by PayPal at a price of: " + amount +"$");
    }
}
class BankTransferPayment implements PaymentMethods {
    public void processPayment(int amount) {
        System.out.println("Purchased by Bank Transfer Payment at a price of: " + amount +"$");
    }
}


class PaymentProcessor {
    private PaymentMethods paymentMethod;
    public PaymentProcessor(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void process(int amount) {
        paymentMethod.processPayment(amount);
    }
}

public class task1 {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor(new CreditCardPayment());
        processor.process(1000);

        processor.setPaymentMethod(new PayPalPayment());
        processor.process(2000);

        processor.setPaymentMethod(new BankTransferPayment());
        processor.process(3000);
    }
}
