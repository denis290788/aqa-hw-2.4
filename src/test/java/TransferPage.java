import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement fromCardField = $("[data-test-id=from]");

    public TransferPage() {
        fromCardField.shouldBe(Condition.visible);
    }
}
