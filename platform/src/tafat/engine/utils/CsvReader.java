package tafat.engine.utils;

import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class CsvReader {

	private static final Logger LOG = Logger.getLogger(CsvReader.class.getName());

	public HashMap<String, String[]> csvHash = new HashMap<>();
	public HashMap<String, Integer> headerHash = new HashMap<>();
	public ArrayList<String> orderedId = new ArrayList<>();
	public ArrayList<String> orderedHeader = new ArrayList<>();

	public CsvReader(String path, String columnKey) {
		String[] rows = FileHandler.readFile(path).replace("\r", "").split("\n");
		int cont = 0;
		for (String header : rows[0].split(";")) {
			headerHash.put(header, cont);
			orderedHeader.add(header);
			cont++;
		}
		String id;
		for (int i = 1; i < rows.length; i++) {
			id = rows[i].split(";")[headerHash.get(columnKey)];
			csvHash.put(id, rows[i].split(";", -2));
			orderedId.add(id);
		}
	}

	public CsvReader(String path) {
		process(FileHandler.readFile(path).replace("\r", "").split("\n"));
	}

	public CsvReader(InputStream inputStream) {
		try {
			process(IOUtils.toString(inputStream).replace("\r", "").split("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(String[] rows) {
		int cont = 0;
		for (String header : rows[0].split(";")) {
			headerHash.put(header, cont);
			orderedHeader.add(header);
			cont++;
		}
		String id;
		for (int i = 1; i < rows.length; i++) {
			id = rows[i].split(";")[0];
			csvHash.put(id, rows[i].split(";", -2));
			orderedId.add(id);
		}
	}

	public String readCellOfRow(int rowNumber, String columnName) {
		if (rowNumber >= orderedId.size() || rowNumber < 0)
			LOG.severe("Index out of bounds");
		return readCellOfRow(orderedId.get(rowNumber), columnName);
	}

	public String readCellOfRow(int rowNumber, int columnNumber) {
		if (rowNumber >= orderedId.size() || rowNumber < 0)
			LOG.severe("Index out of bounds");
		return readCellOfRow(orderedId.get(rowNumber), columnNumber);
	}

	public String readCellOfRow(String id, String columnName) {
		Integer columnNumber = headerHash.get(columnName);
		if (columnNumber == null) {
			LOG.severe("Column name not found");
			return null;
		}
		String[] row = csvHash.get(id);
		if (row == null) {
			LOG.severe("ID not found " + id);
			return null;
		}
		return row[columnNumber];
	}

	public String readCellOfRow(String id, int columnNumber) {
		String[] row = csvHash.get(id);
		if (row == null) {/*Console.error("ID not found " + id);*/
			return null;
		}
		return row[columnNumber];
	}

	public int indexOf(String id) {
		for (int i = 0; i < orderedId.size(); i++) {
			if (orderedId.get(i).equals(id))
				return i;
		}
		LOG.warning("Entry not found");
		return -1;
	}

	public int getNumberOfEntries() {
		return orderedId.size();
	}


}
