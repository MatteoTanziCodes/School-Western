/**
 *
 * @author matteo
 */
public class Record {

	String cfig;
	int scr;
	
	public Record(String CFG, int score) {
		cfig = CFG;
		scr = score;
	}

	public String getConfig() {
		return cfig;	
	}

	public int getScore() {
		return scr;
	}
	
}