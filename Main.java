package DocumentWordBinaryTree;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;

/**
 *
 * @author Md Imran Hossain
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // check if the argument is there 
        if (args.length < 1) {
            System.out.println("Please provide a valid file path as an argument");
            System.exit(-1);
        }

        Path path = Paths.get(args[0]);

        // check for txt file as an input in the argument       
        if (!isTextFile(path)) {
            System.out.println("Only txt files are supported, please provide a link for a txt file");
            System.exit(-1);
        }

        // check if the file or directory exists in the system and also if it is readable
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS) && !Files.isReadable(path) && !Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("ERROR: the file does not exist  \n "
                    + "OR the file is not accessible \n"
                    + "OR path is invalid");
            System.exit(-1);
        }

        wordFreqCount(path);

    }

    private static boolean isTextFile(Path path) {

        String pathString = path.toString();
        String txtString = pathString.substring(pathString.length() - 4);
        return txtString.equals(".txt");

    }

    private static void wordFreqCount(Path path) {

        try {
            //reading the content of the txt file
            String content = new String(Files.readAllBytes(path), Charset.forName("UTF-8"));
            //creating stream of words 
            Stream<String> stream = Stream.of(content.toLowerCase().split("\\s+")).parallel();
            // counting the frequency of words and put that in HashMap as key value pairs
            Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
            //catching if the text file is empty
            if (wordFreq.isEmpty()) {
                System.out.println("Unfortunately your text file is empty!!\nPlease provide a text file with some content");
                System.exit(-1);
            }

            // Sorting the previous map in accending order depending on the value of pairs
            Map<String, Long> sortedWordFreq = wordFreq.entrySet().stream()
                    .sorted(Entry.comparingByValue())
                    .collect(toMap(Entry::getKey, Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            createBinaryTree(sortedWordFreq);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void createBinaryTree(Map<String, Long> map) {

        LinkedList<Node> nodeList = new LinkedList<>();
        //converting each key-value pair into node and put them in a linked list
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodeList.add(node);
        }
        //calculating the depth of the binary tree
        int verticalIteration = getVerticalIteration(nodeList.size());

        List<Node> replacedNodeList = new LinkedList<>();
        //creation of the binary tree
        //the process starts from the most bottom level nodes (sorted accendingly based on value)
        //creates an imidiate up level array of nodes by combining (summing the values)two bottom nodes at a time
        //this process goes on until the top level array got only one node        
        for (int i = 0; i < verticalIteration; i++) {

            for (int j = 0; j < nodeList.size() - 1; j += 2) {//two adjacent nodes at a time
                Node nodeLeft = nodeList.get(j);
                Node nodeRight = nodeList.get(j + 1);

                Node newNode = new Node("Lvl:" + (i + 2), nodeLeft.getValue() + nodeRight.getValue(), nodeLeft, nodeRight);
                nodeLeft.setParent(newNode);
                nodeRight.setParent(newNode);

                replacedNodeList.add(newNode);
            }

            if (nodeList.size() % 2 == 1) {//if there are odd number of nodes, the last node added up
                replacedNodeList.add(nodeList.get(nodeList.size() - 1));
            }
            //sort the new array again
            Collections.sort(replacedNodeList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
            //replacing original array of nodes with the new array of nodes
            nodeList.clear();
            nodeList.addAll(replacedNodeList);
            replacedNodeList.clear();

        }
        
        Node binaryTree = nodeList.get(0);
        binaryTree.print();

    }

    private static int getVerticalIteration(int arraysize) {

        boolean isLastIteration = false;
        int currentArraySize = arraysize;
        int iterationCount = 0;

        while (isLastIteration == false) {
            iterationCount++;
            currentArraySize = (currentArraySize % 2 == 1) ? (currentArraySize /= 2) + 1 : (currentArraySize /= 2);
            isLastIteration = (currentArraySize == 1);
        }

        return iterationCount;

    }

}
