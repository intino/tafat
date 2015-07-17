package tafat.engine.interoperability;

import java.util.ArrayList;
import java.util.HashMap;

import tafat.engine.Console;
import tafat.engine.tools.FileHandler;

public class CsvReader {
	
	HashMap<String, String[]> csvHash = new HashMap<String, String[]>();
	HashMap<String, Integer> headerHash = new HashMap<String, Integer>();
	ArrayList<String> orderedId = new ArrayList<String>();
	ArrayList<String> orderedHeader = new ArrayList<String>();
	
	public CsvReader (String path,String columnKey){
		String[] rows = FileHandler.readFile(path).split("\n");
		int cont = 0;
		for (String header : rows[0].split(";")){
			headerHash.put(header, cont);
			orderedHeader.add(header);
			cont++;
		}
		String id;
		for (int i=1;i<rows.length;i++){
			id = rows[i].split(";")[headerHash.get(columnKey)];
			csvHash.put(id, rows[i].split(";",-2));
			orderedId.add(id);
		}
	}
	
	public CsvReader (String path){
		String[] rows = FileHandler.readFile(path).split("\n");
		int cont = 0;
		for (String header : rows[0].split(";")){
			headerHash.put(header, cont);
			orderedHeader.add(header);
			cont++;
		}
		String id;
		for (int i=1;i<rows.length;i++){
			id = rows[i].split(";")[0];
			csvHash.put(id, rows[i].split(";",-2));
			orderedId.add(id);
		}
	}
	
	public String readCellOfRow (int rowNumber, String columnName){
		if (rowNumber >= orderedId.size() || rowNumber < 0)
			Console.error("Index out of bounds");
		return readCellOfRow(orderedId.get(rowNumber), columnName);
	}
	
	public String readCellOfRow(int rowNumber, int columnNumber) {
		if (rowNumber >= orderedId.size() || rowNumber < 0)
			Console.error("Index out of bounds");
		return readCellOfRow(orderedId.get(rowNumber), columnNumber);
	}
	
	public String readCellOfRow (String id, String columnName){
		Integer columnNumber = headerHash.get(columnName);
		if(columnNumber == null) {
			Console.error("Column name not found " + columnName); return null;}
		String[] row= csvHash.get(id);
		if(row == null) {
			Console.error("ID not found " + id); return null;}
		return row[columnNumber];
	}
	
	public String readCellOfRow(String id, int columnNumber) {
		String[] row= csvHash.get(id);
		if(row == null) {Console.error("ID not found " + id); return null;}
		return row[columnNumber];
	}

	public int getNumberOfEntries() {
		return orderedId.size();
	}
	
	
}
