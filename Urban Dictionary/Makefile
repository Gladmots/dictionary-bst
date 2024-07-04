JFLAGS = -g
JC = javac
SRCDIR = src
BINDIR = bin
DATADIR = data

# Use find command to locate all .java files inside the assignment1 folder
CLASSES := $(shell find $(SRCDIR)/ -name "*.java")

# Compile the classes inside the GenericsKb folder
default: 
	$(JC) $(JFLAGS) -cp $(BINDIR) $(CLASSES) -d $(BINDIR)

clean:
	$(RM) $(BINDIR)/*.class

# the run command will run the GenericksKbBSTApp
run:
	 java -cp $(BINDIR) GenericsKbBSTApp

# the gui command will run the GUI for the GenericksKbBSTApp
gui:
	java -cp $(BINDIR) GenericsKbBSTAppGUI

# the array command will run the GenericksKbArrayApp
array:
	java -cp $(BINDIR) GenericsKbArrayApp



      