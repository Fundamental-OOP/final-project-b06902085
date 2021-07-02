all:
	rm -r bin/
	javac -cp . -sourcepath src -d bin/ src/*.java
	java -cp bin/ Main 2>/dev/null
run:
	rm -r bin
	javac -cp . -sourcepath src -d bin/ src/*.java 
	java -cp bin/ Main 2>/dev/null
