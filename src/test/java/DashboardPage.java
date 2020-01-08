import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement dashboardPageElement = $("[data-test-id=dashboard]");
    private SelenideElement depositeFirstCardButton =
            $$("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]").findBy((Condition) element("[data-test-id=action-deposit]"));
    private SelenideElement depositeSecondCardButton =
            $$("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]").findBy(exactText("Пополнить"));

    public DashboardPage() {
        dashboardPageElement.shouldBe(visible);
    }

    public TransferPage depositeFirstCard() {
        depositeFirstCardButton.click();
        return new TransferPage();
    }

    public TransferPage depositeSecondCard() {
        depositeSecondCardButton.click();
        return new TransferPage();
    }
}
