all:
	wget -p https://www.dropbox.com/sh/ad5g1cz0sz2i8n6/AACPeA0PIHHM2TkxNlGd6CC3a?dl=0
	mv AACPeA0PIHHM2TkxNlGd6CC3a?dl=0 assets
	rm -r bin/
	javac -cp . -sourcepath src -d bin/ src/*.java
	java -cp bin/ Main
run:
	wget https://www.dropbox.com/sh/ad5g1cz0sz2i8n6/AACPeA0PIHHM2TkxNlGd6CC3a?dl=0
	mv AACPeA0PIHHM2TkxNlGd6CC3a?dl=0 assets
	rm -r bin/
	javac -cp . -sourcepath src -d bin/ src/*.java
	java -cp bin/ Main