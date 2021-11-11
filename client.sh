#! /bin/bash

echo "Compiling files..."
javac Product.java
javac ShoppingCart.java
javac Server.java
echo "Running client..."
java Client
