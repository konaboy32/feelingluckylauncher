# Feeling Lucky Launcher for MAME

Random ROM launcher for MAME (Multiple Arcade Machine Emulator).

The program chooses a random ROM, displays the ROM info and then launches the game in MAME.  This repeats indefinitely until the user presses Ctrl+C. 

Java is required to execute the program.  The launcher has been tested with Java SE Runtime Environment 7 although all recent versions will work.

To use the program download the Launcher.class file from tbe bin folder.  The command to run the program is:

java Launcher MAME_DIR ROMS_DIR

You may optionally download the launcher.bat.  The HistoryDatManager is a work-in-progress experimental feature for fetching entries from the MAME history.dat file.
