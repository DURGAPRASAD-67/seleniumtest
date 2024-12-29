import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("MayaAI Functional Tests")
@Feature("Database Status Verification")
public class MayaAITest {

    @Test
    @Story("Check Active Database Status")
    @Description("Verifies if the database is active for valid roll number and password.")
    @Severity(SeverityLevel.CRITICAL)
    public void testDatabaseStatus() {
        MayaAI mayaAI = new MayaAI();
        boolean isActive = mayaAI.checkDatabaseStatus("2000000018", "Thub@123");
        Assert.assertTrue(isActive, "Database should be active");
    }
}
