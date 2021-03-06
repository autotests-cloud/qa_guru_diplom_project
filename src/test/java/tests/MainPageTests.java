package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Main page")
@Tag("web")
public class MainPageTests extends TestBase {
    @Test
    @DisplayName("Logo should be visible on main page")
    void logoShouldBeVisible() {
        step("Open main page", () ->
                open("/"));

        step("Verify, logo is loaded", () ->
            $(".l-liberica")
                .$(byTitle("Liberica JDK, Committed to Freedom, 100% OpenJDK based, TCK verified"))
                .shouldBe(visible));
    }

    @Test
    @DisplayName("Downloads block should have blocks and content")
    void downloadsBlockShouldHaveContent() {
        step("Open main page", () ->
                open("/"));

        step("Verify, downloads section should have 3 blocks", () ->
                $(".l-liberica-downloads").$$(".l-liberica-download").shouldHaveSize(3));
        step("Verify, download blocks should have content", () -> {
            $(".l-liberica-download").shouldHave(text("LTS versions"), text("(long-term support)"));
            $(".l-liberica-download", 1).shouldHave(text("Current release"));
            $(".l-liberica-download", 2).shouldHave(text("LTS versions for Embedded"));
        });
    }

    @Test
    @DisplayName("Benefits block should have blocks and content")
    void benefitsBlockShouldHaveContent() {
        open("/");

        $(".l-benefits").$$(".l-benefit").shouldHaveSize(4);
        $(".l-benefit").shouldHave(text("Java SE standard compliance"),
                text("Each binary is verified by Technology Compatibility Kit " +
                        "which guarantees 100% compatibility with Java SE specification"));
        // todo for .l-benefit 2, 3, 4
    }

}
