import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import net.casper.data.model.*;
import net.casper.data.model.filters.CDataFilterClause;
import net.casper.data.model.filters.RangeFilter;
import au.com.bytecode.opencsv.*;

//TO DO: Cecilia
/**
 * Takes a csv file as input and reads it. It should create a table for each attribute 
 * and its row in each table should correspond to the related row to the other tables
 */
public class CSVDataHandler {
	static HashSet accidents_set = new HashSet();
	public CSVDataHandler(String filename){
		try {
			readFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Uses CSVReader to read the file, creates an Accident object for each row. (where parsing happens)
	 * @param filename
	 * @throws CDataGridException
	 * @throws IOException
	 */
	public void readFile(String filename) throws CDataGridException, IOException {
		CSVReader reader = new CSVReader(new FileReader(filename));
		String[] nextLine;
		String[] headers = reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			Accident a = new Accident(nextLine);
			accidents_set.add(a);
		}
	}
	
	public HashSet getAccidents(){
		return this.accidents_set;
	}
	
	// for testing
	public static void main(String[] args){
		String filename = "datafile_trunc.csv";
		CSVDataHandler c = new CSVDataHandler(filename);
	}
}
