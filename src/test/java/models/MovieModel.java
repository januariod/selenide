package models;

import java.io.File;
import java.util.List;

public class MovieModel {

    public String title;
    public String status;
    public Integer year;
    public String releaseDate;
    public String overview;
    public List<String> cast;
    public File cover;

    public MovieModel(String title, String status, Integer year, String releaseDate, String overview, List<String> cast, String cover) {
        this.title = title;
        this.status = status;
        this.year = year;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.cast = cast;
        this.cover = new File(this.coverPath() + cover);

    }

    private String coverPath() {
        String executionPath = System.getProperty("user.dir");
        String operationalSystem = System.getProperty("os.name");
        String target;

        if (operationalSystem.contains("Windows")) {
            target = executionPath + "\\src\\main\\resources\\cover\\";
        } else {
            target = executionPath + "/src/main/resources/cover/";
        }
        return target;
    }
}
