package pages;

import com.codeborne.selenide.ElementsCollection;
import models.MovieModel;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoviePage {

    public MoviePage addMovie() {
        $(".movie-add").click();
        return this;
    }

    public MoviePage search(String movie) {
        $("input[placeholder^=Pesquisar]").setValue(movie);
        $("#search-movie").click();
        return this;
    }

    public MoviePage createMovie(MovieModel movie) {
        this.fillTitle(movie.title);
        this.selectStatus(movie.status);
        this.fillYear(movie.year.toString());
        this.fillReleaseDate(movie.releaseDate);
        this.fillOverview(movie.overview);
        this.fillCast(movie.cast);
        this.uploadCover(movie.cover);

        $("#create-movie").click();
        return this;
    }

    public ElementsCollection items() {
        return $$("table tbody tr");
    }

    private void fillTitle(String title) {
        $("input[name=title]").setValue(title);
    }

    private void selectStatus(String status) {
        $("input[placeholder=Status]").click();
        $$("ul li span").findBy(text(status)).click();
    }

    private void fillYear(String year) {
        $("input[name=year]").setValue(year);
    }

    private void fillReleaseDate(String releaseDate) {
        $("input[name=release_date]").setValue(releaseDate);
    }

    private void fillOverview(String overview) {
        $("textarea[name=overview]").setValue(overview);
    }

    private void fillCast(List<String> cast) {
        for (String actor : cast) {
            $(".cast").setValue(actor).sendKeys(Keys.TAB);
        }
    }

    private void uploadCover(File cover) {
//        executeJavaScript("document.getElementById('upcover').classList.remove('el-upload__input');");
        $("#upcover").uploadFile(cover);
    }
}
