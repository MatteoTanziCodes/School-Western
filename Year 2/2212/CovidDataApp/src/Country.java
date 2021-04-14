/**
 * @author  Matteo Tanzi <mtanzi@uwo.ca>, Matthew Liu <mliu493@uwo.ca>, Sahebjot Bal <sbal7@uwo.ca>
 *
 *This class creates country objects that contain their names, total cases, coordinates, male/female cases, total population 
 *and Covid-19 cases from a week ago
 *
 */


public class Country {
	//class body
	

	/**
	 * Initializing variables for country object 
	 */
    private String name;
    private int CovidCases;
    private double[] Coordinates;
    private double CovidMale;
    private double CovidFemale;
    private int CovidWeekAgo;
    private int Population;

    /**
     * Constructor,creates new country object
     */
    public Country() {

        name = "";
        Coordinates = new double[]{0.0, 0.0};
        CovidCases = 0;
        CovidFemale = 0;
        CovidMale = 0;
        CovidWeekAgo = 0;
        Population = 0;

    }
    /**
     * Creates a country object with variables
     * @param Nm name of country
     * @param C19Cases country total covid 19 cases
     * @param coordinates country coordinates
     * @param covidMale country covid-19 cases for males
     * @param covidFemale country covid-19 cases for females
     * @param covidWeekAgo covid-19 cases last week
     * @param Pop total population of country
     */
    public Country(String Nm, int C19Cases, double[] coordinates, double covidMale, double covidFemale, int covidWeekAgo, int Pop) {

        name = Nm;
        CovidCases = C19Cases;
        CovidWeekAgo = covidWeekAgo;
        CovidMale = covidMale;
        CovidFemale = covidFemale;
        Coordinates = coordinates;
        Population = Pop;

    }
    
    /**
     * Set name of country 
     * @param Nm country name to be named 
     */
    public void setName(String Nm) {
        name = Nm;
    }
    
    /**
     * Finds name of country
     * @return name of country
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set covid-19 cases for a country
     * @param C19Cases number of Covid-19 cases
     */
    public void setCovidCases(int C19Cases) {

        CovidCases = C19Cases;
    }
    
    /**
     * Get covid-19 cases for a country
     * @return covid-19 cases for a country
     */
    public int getCovidCases() {
        return CovidCases;
    }
    
    /**
     * Get covid-19 female cases for a country
     * @return number of female covid-19 cases
     */
    public double getCovidFemale() {
        return CovidFemale;
    }
    
    /**
     * Get covid-19 male cases for a country
     * @return number of male covid-19 cases
     */
    public double getCovidMale() {
        return CovidMale;
    }
    
    /**
     * Get covid-19 cases from last week for a country
     * @return number of covid-19 cases from last week
     */
    public int getCovidWeekAgo() {
        return CovidWeekAgo;
    }
    
    /**
     * Finds coordinates of country
     * @return coordinates of country
     */
    public double[] getCoordinates() {
        return Coordinates;
    }
    

    /**
     * Sets coordinates for a country
     * @param coordinates coordinates of country to be set
     */
    public void setCoordinates(double[] coordinates) {
        Coordinates = coordinates;
    }
    
    /**
     * Sets number of female covid-19 cases for a country
     * @param covidFemale number of female covid-19 cases
     */
    public void setCovidFemale(double covidFemale) {
        CovidFemale = covidFemale;
    }
    
    /**
     * Set number of covid-19 cases for a country
     * @param covidMale number of male covid-19 cases 
     */
    public void setCovidMale(double covidMale) {
        CovidMale = covidMale;
    }
    
    /**
     * Set covid cases for a week ago 
     * @param covidWeekAgo number of covid-19 cases last week
     */
    public void setCovidWeekAgo(int covidWeekAgo) {
        CovidWeekAgo = covidWeekAgo;
    }
    
    /**
     * Set population for a country
     * @param population population of the country
     */
    
    public void setPopulation(int population) {
        Population = population;
    }

    /**
     * Finds population of a country
     * @return population of a country
     */
    public int getPopulation() {
        return Population;
    }
}

