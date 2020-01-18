import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferBetweenOwnCardsTest {

    @BeforeEach
    public void initEach() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferBetweenOwnCards() {
        int initialFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int initialSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        String amount = "100";
        int amountInt = Integer.parseInt(amount);
        int expectedFirstCardBalance = initialFirstCardBalance + amountInt;
        int expectedSecondCardBalance = initialSecondCardBalance - amountInt;
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.transferToFirstCard();
        DashboardPage.enterAmount(amount);
        dashboardPage.enterCardNumber(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferButton();
        int finalFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int finalSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        assertEquals(expectedFirstCardBalance, finalFirstCardBalance);
        assertEquals(expectedSecondCardBalance, finalSecondCardBalance);
    }

    @Test
    void shouldShowErrorMessageIfAmountGreaterThenBalance() {
        int initialSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        int amountGreater = initialSecondCardBalance + 100;
        String amount = Integer.toString(amountGreater);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.transferToFirstCard();
        DashboardPage.enterAmount(amount);
        dashboardPage.enterCardNumber(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferButton();
        dashboardPage.checkErrorNotification();
    }

    @Test
    void shouldShowErrorMessageIfTransferToTheSameCard() {
        String amount = "100";
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.transferToSecondCard();
        DashboardPage.enterAmount(amount);
        dashboardPage.enterCardNumber(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferButton();
        dashboardPage.checkErrorNotification();
    }

    @Test
    void shouldShowErrorMessageIfEnteredWrongCardNumber() {
        String amount = "100";
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.transferToSecondCard();
        DashboardPage.enterAmount(amount);
        dashboardPage.enterCardNumber(DataHelper.getWrongCardNumber());
        dashboardPage.clickTransferButton();
        dashboardPage.checkErrorNotification();
    }

    @Test
    void shouldTransferNonIntegerAmount() {
        int initialFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int initialSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        String amount = "20.50";
        double amountDouble = Double.valueOf(amount);
        double expectedFirstCardBalance = initialFirstCardBalance + amountDouble;
        double expectedSecondCardBalance = initialSecondCardBalance - amountDouble;
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.transferToFirstCard();
        DashboardPage.enterAmount(amount);
        dashboardPage.enterCardNumber(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferButton();
        double finalFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        double finalSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        assertEquals(expectedFirstCardBalance, finalFirstCardBalance);
        assertEquals(expectedSecondCardBalance, finalSecondCardBalance);
    }
}


