package asmt1;


interface NotificationSystem {
    void sendNotification(String message, String recipient);

    default void sendBulkNotification(String message, String[] recipients) {
        for (String recipient : recipients) {
            sendNotification(message, recipient);
        }
    }
}

class SMS implements NotificationSystem {
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

class Email implements NotificationSystem {
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}

class PushNotification implements NotificationSystem {
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending Push Notification to " + recipient + ": " + message);
    }
}


public class task2 {
    public static void main(String[] args) {
        SMS smsNotification = new SMS();
        smsNotification.sendNotification("SMS notification", "");

        Email emailNotification = new Email();
        emailNotification.sendNotification("Email notification", "");

        PushNotification pushNotification = new PushNotification();
        pushNotification.sendNotification("Push notification", "");

        smsNotification.sendBulkNotification("Bulk SMS", new String[] {});
    }
}