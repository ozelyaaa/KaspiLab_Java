import kz.lab.project1.TransactionInterface;
import kz.lab.project1.TransactionMode;
import kz.lab.project1.TransactionService;

void main() {
    IO.println("Project1...");

    // Код по умолчанию работает в режиме MODE1
    TransactionInterface transactionInterface = new TransactionService();

    String cardNumber = "4111111111111111";
    long amount = 100;
    String transactionId = transactionInterface.startTransaction(cardNumber, amount);

    IO.println(String.format("TransactionId: %s", transactionId));

    boolean success = true;

    //Calling method in Mode 1
    String result1 = transactionInterface.completeTransaction(transactionId, success, TransactionMode.MODE1);
    IO.println(String.format("Result in Mode 1: %s", result1));

    //Calling method in Mode 2
    String result2 = transactionInterface.completeTransaction(transactionId, success, TransactionMode.MODE2);
    IO.println(String.format("Result in Mode 2: %s", result2));
}
