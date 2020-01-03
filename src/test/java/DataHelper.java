import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstCard{
        private String firstCardNumber;
    }

    public static FirstCard getFirstCardNumber() {
        return new FirstCard("5559 0000 0000 0001");
    }

    @Value
    public static class SecondCard{
        private String secondCardNumber;
    }

    public static SecondCard getSecondCardNumber() {
        return new SecondCard("5559 0000 0000 0002");
    }
}
