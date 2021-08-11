package tests;

import common.BaseTest;
import libs.DataBase;
import models.MovieModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MovieTests extends BaseTest {

    @BeforeSuite
    public void resetMovies() {
        DataBase db = new DataBase();
        db.restartTable();
    }

    @BeforeMethod
    public void userLogin() {
        login
                .open()
                .with("daniel@ninjaplus.com", "123456abc@");
        side.userName().shouldHave(text("daniel"));
    }

    @Test
    public void shouldRegisterNewMovie() {
        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima Fase",
                "Pré-venda",
                2020,
                "16/01/2020",
                "Spencer volta ao mundo fantástico de Jumanji. Os amigos Martha, "
                        + "Fridge e Bethany entram no jogo e tentam trazê-lo para casa. Mas eles logo "
                        + "descobrem mais obstáculos e perigos a serem superados.",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Denny DeVito"),
                "jumanji.jpg"
        );

        movie
                .addMovie()
                .createMovie(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }

    @Test
    public void shoulSearchTwoMovies() {
        movie.search("Batman").items().shouldHaveSize(2);
    }
}
