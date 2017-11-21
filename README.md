# Binary-Tree-of-Words-in-Text-Document
This Java program (command line) reads text from a file, splits it into words at spaces and newline characters and constructs an (unbalanced) binary tree where each leaf node represents a unique word.

The tree construction start by creating a node for each unique word, where a node has a field to keep track of the occurrence count. The algorithm starts with the two least occurring nodes and creates a parent node. The parent node gets assigned an occurrence count that is the sum of the word occurrences. The process then repeats, i.e., it locates the two nodes with the least occurrence count, creates a parent node, and so on, until all nodes are part of the tree.

# Prerequisites
- Java 8 and above
- Input file as txt file

# Usage
To run the application from the command line, go to the directory where DocumentsWordBinaryTree.jar is located and type the following:

java -jar "DocumentsWordBinaryTree.jar" {input file system path}

example: 
command line: C:\java>java -jar DocumentsWordBinaryTree.jar C:\Users\User\Downloads\test.txt

### Example output
text = Hey you need need to to write write write good program program

output =
![](https://github.com/Md-ImranHossain/Binary-Tree-of-Words-in-Text-Document/blob/master/Capture.PNG)
