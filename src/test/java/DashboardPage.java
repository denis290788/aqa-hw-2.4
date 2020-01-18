import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement form = $(By.className("App_appContainer__3jRx1"));
    private SelenideElement firstCardItem = form.$("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement firstCardButton = firstCardItem.$("[data-test-id = action-deposit]");
    private SelenideElement secondCardItem = form.$("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement secondCardButton = secondCardItem.$("[data-test-id = action-deposit]");
    private static SelenideElement transactionAmountField = $("[data-test-id = amount] input");
    private SelenideElement сardNumberFromField = form.$("[data-test-id = from] input");
    private SelenideElement transferButton = form.$("[data-test-id = action-transfer]");
    private SelenideElement errorNotification = form.$("[data-test-id = error-notification]");

    public DashboardPage() {
    }

    public void transferToFirstCard() {
        firstCardButton.click();
    }

    public void transferToSecondCard() {
        secondCardButton.click();
    }

    public static void enterAmount(String amount) {
        transactionAmountField.setValue(DataHelper.Amount.getAmount(amount));
    }

    public void enterCardNumber(DataHelper.CardNumber number) {
        сardNumberFromField.setValue(number.getCardNumber());
    }

    public void clickTransferButton() {
        transferButton.click();
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(visible);
    }

    public static class CurrentBalance {
        private static SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        private static SelenideElement firstCardItem = form.$("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
        private static SelenideElement secondCardItem = form.$("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

        public static int GetFirstCardBalance() {
            String firstCardBalanceStr = firstCardItem.text();
            int firstCardBalance = Integer.parseInt(firstCardBalanceStr.substring(29, firstCardBalanceStr.length() - 13).trim());
            return firstCardBalance;
        }

        public static int GetSecondCardBalance() {
            String secondCardBalanceStr = secondCardItem.text();
            int secondCardBalance = Integer.parseInt(secondCardBalanceStr.substring(29, secondCardBalanceStr.length() - 13).trim());
            return secondCardBalance;
        }
    }
}