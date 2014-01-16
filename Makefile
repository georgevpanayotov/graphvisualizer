javac=javac
bindir=bin
jflags=-d $(bindir)

files=src/Digraph.java \
      src/GraphParser.java \
      src/Driver.java \
      src/VertexComponent.java \
      src/GraphContainer.java \
      src/VertexComponentDelegate.java

classfiles=-C bin Digraph.class \
           -C bin GraphParser.class \
           -C bin Driver.class \
           -C bin VertexComponent.class \
           -C bin GraphContainer.class \
           -C bin VertexComponentDelegate.class

all: class
	jar -cfm bin/GraphVis.jar Manifest $(classfiles)

class:
	mkdir -p bin
	$(javac) $(jflags) $(files)


