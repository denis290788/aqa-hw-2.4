import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class TransferBetweenOwnCardsTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.depositeFirstCard();
        $("[data-test-id=amount]").setValue("200");
        $("[data-test-id=from]").setValue("5559 0000 0000 0002");
        $("[data-test-id=action-transfer]").click();
        $$("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]").findBy(Condition.exactText("10200")).shouldBe(Condition.visible);
    }
}
