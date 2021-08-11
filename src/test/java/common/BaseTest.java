package common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;
import pages.views.SideBar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {

    protected static MoviePage movie;
    protected static LoginPage login;
    protected static SideBar side;

    @BeforeMethod
    public void start() {
        Properties prop = new Properties();
        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(inputFile);
        } catch (Exception e) {
            System.out.println("Deu ruim ao carregar o config.properties. Trace => " + e.getMessage());
        }

        Configuration.browser = prop.getProperty("browser");
        Configuration.baseUrl = prop.getProperty("url");
        Configuration.timeout = Long.parseLong(prop.getProperty("timeout"));

        movie = new MoviePage();
        login = new LoginPage();
        side = new SideBar();
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }

    @AfterMethod
    public void takeScreenshot() {
        String tempShot = screenshot("temp_shot");

        try {
            BufferedImage bimage = ImageIO.read(new File(tempShot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "png", baos);
            byte[] finalShot = baos.toByteArray();
            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalShot));
        } catch (Exception e) {
            System.out.println("Screenshot não gravada. Trace =>" + e.getMessage());
        }
    }
}
