javac=javac
bindir=bin
jflags=-d $(bindir)

files=src/Digraph.java \
	src/GraphParser.java \
    src/Driver.java \
    src/VertexComponent.java \
    src/GraphContainer.java


all:
	$(javac) $(jflags) $(files)