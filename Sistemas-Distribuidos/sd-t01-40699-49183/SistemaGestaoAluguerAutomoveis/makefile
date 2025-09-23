# Makefile for Java project

# Variables
SRC_DIR = src
BUILD_DIR = build/classes
CLASSPATH = $(BUILD_DIR)
RMI_DIR = ./
JAVA_FILES = $(shell find $(SRC_DIR) -name "*.java") #$(SRC_DIR)/**/*.java
PORTA = 1099
SERVER_PORT = 0

# Targets
all: compile run-nameserver

compile: 
	javac -d $(BUILD_DIR) -classpath $(CLASSPATH) $(JAVA_FILES)

run-nameserver:
	rmiregistry -J-classpath -J$(CLASSPATH) $(PORTA) 
	
run-server:
	java -classpath "$(CLASSPATH):./postgresql-42.7.5.jar" sigaa/ServidorMain $(SERVER_PORT)

run-cliente-geral:
	java -classpath $(CLASSPATH) sigaa/ClienteGeralMain "127.0.0.1" $(SERVER_PORT)

run-cliente-administrador:
	java -classpath $(CLASSPATH) sigaa/ClienteAdministradorMain "127.0.0.1" $(SERVER_PORT)

clean:
	rm -rf $(BUILD_DIR)/*

# Usage:
# To compile: make compile or make
# To run: make run
# To clean: make clean

