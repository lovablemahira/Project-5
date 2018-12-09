/**
 * <h1>Profitable</h1>
 * 
 * This interface represents something that can be used to make a profit. Along
 * with returning total profits it must also be able to provide a report.
 *
 * @author (jacks668, David Jackson), (morri417, Mahira Morris)
 *
 * @version 12/9/18
 */
public interface Profitable {
	
	public double getProfit();

	public String report();

}