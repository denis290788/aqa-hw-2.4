import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount]");
    private SelenideElement fromCardField = $("[data-test-id=from]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        fromCardField.shouldBe(Condition.visible);
    }
}
