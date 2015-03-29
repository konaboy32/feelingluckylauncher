import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Random;

public class Launcher {

	private static String MAME_EXE;
	private static String MAME_DIR;
	private static String ROMS_DIR;
	private static File[] ROMS;

	private static void init() {
		ROMS = new File(ROMS_DIR).listFiles();
	}
	
	private static String getRandomRom() {
		int random = new Random().nextInt(ROMS.length);
		String filename = ROMS[random].getName();
		return filename.substring(0, filename.indexOf("."));
	}
	
	private static String getExecutable() {
		String os = System.getProperty("sun.arch.data.model");
		if (os != null && os.equals("64")) {
			return "mame64.exe";
		}
		return "mame.exe";
	}

	private static void launch(String rom) {
		String infoCommand = MAME_DIR + "\\" + MAME_EXE + " -listfull " + rom;
		String launchCommand = MAME_DIR + "\\" + MAME_EXE + " " + rom;
		String line = null;
		System.out.println();
		
		try {
			Process infoProcess = Runtime.getRuntime().exec(infoCommand, null, new File(MAME_DIR));
			BufferedReader reader = new BufferedReader(new InputStreamReader(infoProcess.getInputStream()));	
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            Thread.sleep(4000);

			Process launchProcess = Runtime.getRuntime().exec(launchCommand, null, new File(MAME_DIR));
			launchProcess.waitFor();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MAME_DIR = args[0];
		ROMS_DIR = args[1];
		MAME_EXE = getExecutable();
		
		init();
		
		System.out.println("MAME directory:  " + MAME_DIR);
		System.out.println("ROMs directory:  " + ROMS_DIR);
		System.out.println("Executable:      " + MAME_EXE);
		System.out.println("Total roms:      " + ROMS.length);
		
		while (true) {
			launch(getRandomRom());
		}
	}
}
