package motor.notifier;

public class EmailServer {
    public static void sendTestEmail() {
        final String GACCOUNT_APP_PASSWORD = "fake-password";
        try {
            EmailUtil.sendEmail("smtp.gmail.com", "587", "mkrishna.uber@gmail.com", 
                                GACCOUNT_APP_PASSWORD, "badri.krishna@gmail.com", 
                                "Motor Monitoring Alert", "The motor XYX has broken thresholds. Plz follow SOP to get it fixed.");
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
