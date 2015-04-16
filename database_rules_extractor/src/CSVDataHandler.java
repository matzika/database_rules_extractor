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
	String filename;
	static CDataCacheContainer container;

	public CSVDataHandler(String filename){
		this.filename = filename;
		try {
			createTable();
			readFile(filename);
			printTable();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void createTable() throws CDataGridException{
		String[] columnNames = new String[]{"date", "borough", "zipcode", "persons_injured", "persons_killed", "pedestrians_injured", "pedestrians_killed",
				"cyclicsts_injured", "cyclists_killed", "motorists_injured", "motorists_killed", "factor1", "factor2", "factor3", "factor4", "factor5",
				"vehicle1", "vehicle2", "vehicle3", "vehicle4", "vehicle5"};
		Class[] columnTypes = new Class[] {Date.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class};
		String[] primaryKeys = new String[]{"borough"};
		CRowMetaData metaDef = new CRowMetaData(columnNames, columnTypes, primaryKeys);
		container = new CDataCacheContainer("mytest", metaDef, new HashMap());
	}
	
	

	public static void readFile(String filename) throws CDataGridException, IOException {
		CSVReader reader = new CSVReader(new FileReader(filename));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			Accident a = new Accident(nextLine);
			addRow(a);
		}
	}
	
	public static void addRow(Accident a) throws CDataGridException {
		CDataRow row = new CDataRow();
		row.setRawData(a.toRow());
		container.addData(new CDataRow[] {row});
	}
	
	public static void printTable() throws CDataGridException {
		CDataRowSet results = container.getAll();
		System.out.println(results.toString());
	}

	// for testing
	public static void main(String[] args){
		String filename = "datafile_trunc.csv";
		CSVDataHandler c = new CSVDataHandler(filename);
	}
}
