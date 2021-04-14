/**
 * @authors Matteo Tanzi <mtanzi@uwo.ca>, Matthew Liu <mliu493@uwo.ca>, Sahebjot Bal <sbal7@uwo.ca>
 * 
 * This class does computation called by the user depending on
 * the countries they have selected and analysis method they want
 * 
 * 
 */


public class AnalysisEngine {
	//class body
	
	/**
	 * Initializing results array and a country object array for data 
	 */
    double[]results;
    Country[]data;
 
    
    /**
     * Constructor that creates analysis engine object
     */
    public AnalysisEngine(){

        results = new double[0];
    }
    
    
    /**
     * Creates a new country Object array to store data
     * @param dataArray data array used to compute results
     */
    public AnalysisEngine(Country[]dataArray){

        results = new double[dataArray.length];
        data = dataArray;
    }
    
    /**
     *  Finds total number of Covid-19 cases 
     *  @returns Array of Covid-19 cases for selected countries
     */
    public double[] TotalCovidCases(){

        for (int i = 0; i < results.length; i++)
        {
            results[i] = data[i].getCovidCases();
            
        }
        
        return results;
    }
    
    /**
     * Calculates Covid-19 cases per Capita
     * @returns Array of Covid-19 cases per Capita for selected countries
     */
    public double[] CasesPerCapita(){
    	for(int i = 0; i < results.length; i++)
    	{
    		results[i] = (double)data[i].getCovidCases() / data[i].getPopulation();
    	
    	}
        return results;
    }
    

    /**
     * Calculates growth rate of Covid-19 cases over last 7 days
     * @returns Array of Covid-19 cases for selected countries
     */
    public double[] GrowthRate(){
    	for (int i = 0; i < results.length; i++)
    	{
    		  results[i] = ((double) (data[i].getCovidCases()-data[i].getCovidWeekAgo())/data[i].getCovidWeekAgo() * 100);
    	}
    	
        return results;
    }
    
    /**
     * Finds total number male Covid-19 cases 
     * @returns Array of number of male Covid-19 cases for selected countries
     */
    public double[] TotalMaleCases(){
    	for(int i = 0; i < results.length; i++) {
    		results[i] = data[i].getCovidMale()/ 100 * data[i].getCovidCases();
    		
    	}
     
        return results;
    }
    
   /**
    * Finds total number female Covid-19 cases 
    * @returns Array of number of female Covid-19 cases for selected countries
    */
    public double[] TotalFemaleCases(){
    	for(int i = 0; i < results.length; i++)
    		results[i] = data[i].getCovidFemale()/ 100 * data[i].getCovidCases();

        return results;
    }
  
    /**
     * Calculates male cases per capita 
     * @returns Array of number of male cases per capita for selected countries
     */
    
    public double[] MalePerCapita() {
    	for(int i = 0; i < results.length; i++)
    	{
    		results[i] = (data[i].getCovidMale() / 100 * data[i].getCovidCases()) / data[i].getPopulation();
    	
    	}
        return results;
    	
    }
    /**
     * Calculates female cases per capita
     * @returns Array of number of male cases per capita for selected countries
     */
    public double[] FemalePerCapita() {
    	for(int i = 0; i < results.length; i++)
    	{
    		results[i] = (data[i].getCovidFemale() / 100 * data[i].getCovidCases())/ data[i].getPopulation();
    	
    	}
        return results;
    	
    }
   

}


