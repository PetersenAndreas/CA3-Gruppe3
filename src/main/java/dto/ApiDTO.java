package dto;

public class ApiDTO {
    
    private String chuckJoke;
    private String chuckJokeID;
    private final String chuckURL = ChuckJokeDTO.getRANDOM_URL();
    private String dadJoke;
    private String dadJokeID;
    private final String dadURL = DadJokeDTO.getRANDOM_URL();
    private String countryName;
    private String countryArea;
    private final String countryURL = CountryDTO.getCOUNTRY_URL();
    private String copenhagenTime;
    private String weekNumber;
    private final String timeURL = CopenhagenTimeDTO.getTIME_URL();

    public ApiDTO() {
    }

    public ApiDTO(ChuckJokeDTO chuck, DadJokeDTO dad, CountryDTO country, CopenhagenTimeDTO time) {
        this.chuckJoke = chuck.getValue();
        this.chuckJokeID = chuck.getId();
        this.dadJoke = dad.getJoke();
        this.dadJokeID = dad.getId();
        this.countryName = country.getName();
        this.countryArea = country.getArea();
        this.copenhagenTime = time.getDatetime();
        this.weekNumber = time.getWeek_number();
    }

    public String getChuckJoke() {
        return chuckJoke;
    }

    public void setChuckJoke(String chuckJoke) {
        this.chuckJoke = chuckJoke;
    }

    public String getChuckJokeID() {
        return chuckJokeID;
    }

    public void setChuckJokeID(String chuckJokeID) {
        this.chuckJokeID = chuckJokeID;
    }

    public String getDadJoke() {
        return dadJoke;
    }

    public void setDadJoke(String dadJoke) {
        this.dadJoke = dadJoke;
    }

    public String getDadJokeID() {
        return dadJokeID;
    }

    public void setDadJokeID(String dadJokeID) {
        this.dadJokeID = dadJokeID;
    }

    public String getChuckUrl() {
        return chuckURL;
    }

    public String getDadUrl() {
        return dadURL;
    }

    public String getChuckURL() {
        return chuckURL;
    }

    public String getDadURL() {
        return dadURL;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public String getCountryURL() {
        return countryURL;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }
    
}
