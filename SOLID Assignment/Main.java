class OrderService {
    void placeOrder(String item) {
        System.out.println("Order placed: " + item);
    }
}

interface PriceStrategy {
    int calculatePrice();
}

class Pizza implements PriceStrategy {
    public int calculatePrice() {
        return 200;
    }
}

class Burger implements PriceStrategy {
    public int calculatePrice() {
        return 100;
    }
}

class BillService {
    void printBill(PriceStrategy item) {
        System.out.println("Price: " + item.calculatePrice());
    }
}

interface Payment {
    void pay(int amount);
}

class UpiPayment implements Payment {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class CardPayment implements Payment {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Card");
    }
}

class PaymentService {
    Payment payment;

    PaymentService(Payment payment) {
        this.payment = payment;
    }

    void processPayment(int amount) {
        payment.pay(amount);
    }
}

interface Notification {
    void send();
}

class EmailNotification implements Notification {
    public void send() {
        System.out.println("Email sent");
    }
}

public class Main {
    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        orderService.placeOrder("Pizza");

        PriceStrategy item = new Pizza();

        BillService billService = new BillService();
        billService.printBill(item);

        Payment payment = new UpiPayment();
        PaymentService paymentService = new PaymentService(payment);
        paymentService.processPayment(item.calculatePrice());

        Notification notification = new EmailNotification();
        notification.send();
    }
}