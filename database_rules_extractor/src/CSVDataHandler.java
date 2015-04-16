import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

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
	static CDataCacheContainer accidents_table;

	public CSVDataHandler(String filename){
		try {
			createTable();
			readFile(filename);
			printTable();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Just creates a table with the desired column names, their appropriate types.
	 * @throws CDataGridException
	 */
	public void createTable() throws CDataGridException{
		String[] columnNames = new String[]{"date", "borough", "zipcode", "persons_injured", "persons_killed", "pedestrians_injured", "pedestrians_killed",
				"cyclicsts_injured", "cyclists_killed", "motorists_injured", "motorists_killed", "factor1", "factor2", "factor3", "factor4", "factor5",
				"vehicle1", "vehicle2", "vehicle3", "vehicle4", "vehicle5"};
		Class[] columnTypes = new Class[] {Date.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class};
		//primary key for now is date, there's probably a better option
		String[] primaryKeys = new String[]{"date"};
		CRowMetaData metaDef = new CRowMetaData(columnNames, columnTypes, primaryKeys);
		accidents_table = new CDataCacheContainer("mytest", metaDef, new HashMap());
	}
	
	/**
	 * Uses CSVReader to read the file, creates an Accident object for each row. (where parsing happens)
	 * Calls addRow on the created Accident.
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
			addRow(a);
		}
	}
	
	/**
	 * Adds an accident's toRow method to the container.
	 * @param a
	 * @throws CDataGridException
	 */
	public void addRow(Accident a) throws CDataGridException {
		CDataRow row = new CDataRow();
		row.setRawData(a.toRow());
		accidents_table.addData(new CDataRow[] {row});
	}
	
	//this is mostly for checking stuff
	public void printTable() throws CDataGridException {
		CDataRowSet results = accidents_table.getAll();
		System.out.println(results.toString());
	}

	/**
	 * to get the accidents_table
	 * @return
	 */
	public CDataCacheContainer getAccidentsTable(){
		return accidents_table;
	}
	
	// for testing
	public static void main(String[] args){
		String filename = "datafile_trunc.csv";
		CSVDataHandler c = new CSVDataHandler(filename);
	}
}
