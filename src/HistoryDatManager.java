import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDatManager {
	private static final String START_TAG = "$info";
	private static final String END_TAG = "$end";
	private static final String BIO_TAG = "$bio";
	private static final String STAFF = "- STAFF -";
	private static final String SOURCES = "- SOURCES -";
	private static final String PORTS = "- PORTS -";
	private static final String SERIES = "- SERIES -";
	private static final String QUOTE = "\"";
	private static final String BACKSLASH = "\\";
	private static final String FULLSTOP = ".";
	private static final String EMPTY = "";
	private String path = null;

	public HistoryDatManager(String path) {
		this.path = path;
	}

	public String trimRom(String rom) {
		if (rom.contains(FULLSTOP)) {
			rom = rom.substring(0, rom.indexOf(FULLSTOP));
		}
		if (rom.contains(BACKSLASH)) {
			rom = rom.substring(rom.lastIndexOf(BACKSLASH) + 1);
		}
		if (rom.contains(QUOTE)) {
			rom = rom.replaceAll(QUOTE, EMPTY);
		}		
		return rom;
	}
	
	public boolean isEndEntry(String line) {
		if (SERIES.equals(line)) {
			return true;
		}
		if (STAFF.equals(line)) {
			return true;
		}
		if (PORTS.equals(line)) {
			return true;
		}
		if (SOURCES.equals(line)) {
			return true;
		}
		if (END_TAG.equals(line)) {
			return true;
		}
		return false;
	}
	
	public List<String> getRomInfo(String rom) {
		rom = trimRom(rom);
		List<String> result = new ArrayList<>();
		String line;
		boolean found = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				
				//search for start tag
				if (line.startsWith(START_TAG) & line.contains(rom)) {
					found = true;
					//skip blank lines at start of entry
					reader.readLine();
					reader.readLine();
				}
				
				//search for end tag
				if (found && !line.startsWith(START_TAG) && !line.startsWith(BIO_TAG)) {
					if (isEndEntry(line)) {
						return result;
					}
					result.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

}
