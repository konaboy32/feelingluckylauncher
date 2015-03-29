@echo off
REM =========================================================
REM Start script for the Feeling Lucky Launcher for MAME
REM =========================================================

REM =========================================================
REM Configure these variables according to your system:
set MAME_HOME=G:\_ARCADE\EMULATORS\MAME148_HACK
set ROMS_HOME=G:\_ARCADE\COMPLETE_ROM_SETS\mame148
REM =========================================================

java Launcher %MAME_HOME% %ROMS_HOME%
