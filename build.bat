@echo off
mkdir DB 2>nul
javac -encoding UTF-8 -cp ".;driver\h2-2.2.224.jar" src\*.java -d .
java -cp ".;driver\h2-2.2.224.jar" MainClass
