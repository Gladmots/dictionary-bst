# Urban Dictionary

Java application using a Binary Search Tree (BST) data structure to efficiently store, search, update, and display words and their definitions. The application provides users with functionality to search for definitions, update existing entries, and view confidence scores based on user ratings.

  •	Technologies Used: Java, Java Swing, BST operations

Dictionary BST — Knowledge-Base Search (CLI + Swing GUI)
A compact Java project that demonstrates two ways to manage and search a “knowledge base” of statements:
Binary Search Tree (BST) — fast lookups keyed by term


Array-backed list — simple storage, sorted by confidenceScore


You can run it as a console (CLI) app or a desktop (Swing GUI) app.

✨ What it does
Load statements from a tab-separated file (TSV)


Add/update a statement for a term with a confidence score


Search by term (case-insensitive)


Search by term + sentence (case- & punctuation-insensitive)


Choose your flavor:


DictionaryBSTApp (BST; efficient lookups)


DictionaryArrayApp (array; simple, keeps items sorted by confidence)


DictionaryAppGUI (Swing GUI built on top of the BST version)



📁 Project structure (typical)
dictionary-bst/
├─ src/
│  ├─ DictionaryBSTApp.java      # CLI using a Binary Search Tree
│  ├─ DictionaryArrayApp.java    # CLI using an Array + sort by confidence
│  └─ DictionaryAppGUI.java      # Swing GUI that wraps the BST app
├─ data/
│  ├─ GenericsKB.txt             # sample TSV knowledge base (term, sentence, score)
│  └─ GenericsKB-additional.txt  # (optional) extra data
├─ bin/                          # (created on compile) .class files
├─ README.md
└─ Makefile                      # (optional) if you keep one

The code uses no external libraries—only the Java standard library.

📦 Input file format (TSV)
Each line has three fields, separated by tab (\t):
<term> <TAB> <sentence> <TAB> <confidenceScore>

Example (data/GenericsKB.txt):
photosynthesis    process by which plants convert light to chemical energy    0.97
encapsulation     bundling data and methods in a single unit                  0.92
polymorphism      ability of different types to be treated uniformly          0.88

Notes:
confidenceScore is a decimal number (e.g., 0.97).


Searches are case-insensitive.


The term + sentence search also ignores punctuation and whitespace.



▶️ How to run (CLI)
Prerequisites
JDK 8+ (works with 11/17+ as well)


Confirm with: java -version and javac -version


Compile
From the project root:
# Create output folder
mkdir -p bin

# Compile all sources to bin/
javac -d bin src/*.java

Run — BST version (recommended)
# Starts an interactive menu in your terminal
java -cp bin DictionaryBSTApp

Typical session:
Choose an action from the menu:
1. Load a knowledge base from a file
2. Add a new statement to the knowledge base
3. Search for an item in the knowledge base by term
4. Search for an item in the knowledge base by term and sentence
5. Quit
Enter your choice: 1
Enter file name: data/GenericsKB.txt
Knowledge base loaded successfully.

Run — Array version (alternative)
java -cp bin DictionaryArrayApp


🖥️ How to run (GUI – Swing)
java -cp bin DictionaryAppGUI

You’ll see a window with a dropdown for actions.


Actions mirror the CLI: Load, Add, Search (term), Search (term + sentence), Quit.


For Load, enter something like: data/GenericsKB.txt.


If you’re on Windows and using VS Code, you can also run from the Run panel or right-click and “Run Java”.

🔎 How the data structures work
BST (DictionaryBSTApp)
Key: term (String)


Value: the best sentence for that term (the one with the highest confidenceScore)


Insert/update rule:


If the term doesn’t exist, create a new node.


If the term exists, only replace the stored sentence if the new confidenceScore is higher.


Average complexities:


Insert: O(log n)


Search: O(log n)


Worst case (unbalanced): O(n)


Array (DictionaryArrayApp)
Statements are stored in an array; on each insert, the prefix [0..size) is sorted by confidenceScore (descending).


searchTerm scans linearly.


Complexities:


Insert (with sort): roughly O(n log n)


Search: O(n)



🧪 Search behavior details
By term
 Case-insensitive comparison of the term.


By term + sentence
 Case-insensitive and punctuation/whitespace removed for both the input sentence and the stored sentence, then compared.
 Example:
 "Hello, world!" ≈ "helloworld"



🧠 Why this project is useful
Shows two contrasting data structures for the same problem.


Demonstrates trade-offs between simplicity (array) and performance (BST).


Clean, focused examples of:


File I/O


String normalization


Interactive CLI menus


Basic Swing GUI patterns (dialogs, combo box, event handling)


Great as a learning exercise, a DS&A demo, or a starter for larger knowledge-base tools.

🛠️ Tips & Troubleshooting
File not found when loading
 Use a relative path from the project root, e.g. data/GenericsKB.txt.


GUI doesn’t appear / exits immediately
 Make sure you’re running java -cp bin DictionaryAppGUI after compiling.


Accidentally added the wrong confidence score
 Just add the same term again with the correct (higher) score to replace it.


Large files
 The BST version will scale better. Consider balancing techniques (AVL/Red-Black) if needed.



🚀 Roadmap / Ideas
Persist to disk (serialize BST) after inserts.


Allow multiple sentences per term (store a list, ranked by score).


Export search results to CSV/PDF.


Add prefix search / autocomplete.


Replace hand-rolled BST with TreeMap or an AVL/Red-Black tree.


Package as a JAR with a small launcher script.



🧾 License
MIT (or your preferred license). Add a LICENSE file to the repo root.

📷 Screenshots (optional)
Add images under docs/ and reference them here:
![GUI screenshot](docs/gui.png)


Quick commands (copy/paste)
# Compile
mkdir -p bin && javac -d bin src/*.java

# Run CLI (BST)
java -cp bin DictionaryBSTApp

# Run CLI (Array)
java -cp bin DictionaryArrayApp

# Run GUI (Swing)
java -cp bin DictionaryAppGUI

MIPS Image Processing:





