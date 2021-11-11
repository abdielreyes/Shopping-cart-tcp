#! /bin/bash

echo "Compiling files.."
javac Database.java
javac Product.java
javac Server.java
echo "Running server..."
java Server
