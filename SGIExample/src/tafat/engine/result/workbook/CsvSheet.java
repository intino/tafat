package tafat.engine.result.workbook;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import tafat.engine.Time;

public class CsvSheet extends Sheet{
	private static final String ENCODING = "ISO-8859-1";
	
	String name;
	private byte[] content = new byte [4 * 1024 * 1024];
	int contentIndex = 0;
	final String separator = ";";
	long stopRegisterTime;
	long startRegisterTime = Time.getInstance().getSimulationDate().getTime();
	
	public CsvSheet(String name, Date to) {
		this.name = name;
		if (to != null)
			stopRegisterTime = to.getTime();
		else
			stopRegisterTime = Time.getInstance().getFinishSimulationDate().getTime();
	}
	
	@Override
	public void write (String value) {
		if (value.contains(separator))
			value = "\"" + value + "\"";
		
		value+=separator;
		try {
			byte[] toCopy = value.replace(".",",").getBytes(ENCODING);
			if ((contentIndex + toCopy.length) > content.length)
				resizeContent();
			for (int i = 0; i < toCopy.length ; i++)
				content[contentIndex++] = toCopy[i];
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void resizeContent() {
		long totalMilisecondsForThisSheet = stopRegisterTime - startRegisterTime;
		long currentMilisecondsForThisSheet = Time.getInstance().getSimulationDate().getTime() - startRegisterTime;
		double newSize = ((double)contentIndex * (double)totalMilisecondsForThisSheet) / (double)currentMilisecondsForThisSheet;
		newSize += 2 * 1024 * 1024; // Extra MB. Just in case
		
//		Console.out("Resizing " + this.name + " to: " + newSize/(1024*1024) + " MB");
		
		byte[] aux = content;
		content = null;
		content = new byte [(int)newSize];
		for (int i = 0; i < contentIndex; i++)
			content[i] = aux[i];
	}

	public String getName () {
		return name;
	}
	
	public byte[] getContent () {
		byte[] toReturn = new byte[contentIndex];
		for (int i = 0; i < contentIndex; i++)
			toReturn[i] = content[i];
		
		return toReturn;
	}

	@Override
	public void newRow() {
		try {
			byte[] toCopy = "\n".getBytes(ENCODING);
			for (int i = 0; i < toCopy.length ; i++)
				content[contentIndex++] = toCopy[i];
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void terminate() {
		content = null;
		System.gc();
	}
}
