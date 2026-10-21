CPRG 304 Project  1: Complexity and Sorting

A Java 21 command-line application that reads a file of clothing products, sorts them from largest to smallest with one of six sorting algorithms, and reports how long the sort took.

Requirements
Java 21
Eclipse (project: assignment1StartingCode)
Project structure
text
assignment1StartingCode/
  res/         products1.txt, products2.txt, products3.txt   (input files)
  src/
    appDomain/   AppDriver, Benchmark, CommandLineArguments,
                 OutputFormatter, ProductFileReader
    products/    Product (abstract), Jeans, TShirts, Skirts,
                 Dresses, Shorts, Jackets, Shoes
    utilities/   Order, OriginalPriceComparator, UpdatedPriceComparator,
                 BubbleSort, SelectionSort, InsertionSort,
                 MergeSort, QuickSort, HeapSort
Package	What it holds
appDomain	The program itself: reads the arguments and the file, runs and times the sort, prints the result
products	The abstract Product class and its seven subclasses
utilities	The two comparators, Order (decides descending order), and the six sorts
Usage
text
java -jar Sort.jar -f<file> -t<o|u|d> -s<b|s|i|m|q|z>
Option	Meaning
-f<file>	Path to the product file, with no space after -f
-to	Sort by Original Price
-tu	Sort by Updated Price
-td	Sort by Real Difference (adjustment times the absolute difference of the two prices, see below)
-sb	Bubble sort
-ss	Selection sort
-si	Insertion sort
-sm	Merge sort
-sq	Quick sort (median of 3 pivot)
-sz	Heap sort

Flags are case-insensitive, can come in any order, and accept - or the en dash –.

Examples
text
java -jar Sort.jar -fres/products1.txt -to -sb
java -jar Sort.jar -fres/products2.txt -tu -si
java -jar Sort.jar -fres/products1.txt -td -sz

Run these from the project folder so the res/ path works.

Running in Eclipse
Run, Run Configurations..., Java Application, then New Configuration.
Project: assignment1StartingCode. Main class: appDomain.AppDriver.
On the Arguments tab, enter the options, for example -fres/products1.txt -to -sb.
Input file format
Line 1 is the number of products, N.
Each of the next N lines is <Type> <original price> <updated price>, for example Shorts 5.25 23.36.
Types: Jeans, TShirts, Skirts, Dresses, Shorts, Jackets, Shoes (capitalisation ignored).
The products are read into a plain array, not an ArrayList.
Real Difference

calcRealDiff() = adjustment x |original price - updated price|

Type	Adjustment
Jeans	0.5
TShirts	0.2
Skirts	0.1
Dresses	0.6
Shorts	0.3
Jackets	0.7
Shoes	0.4

compareTo compares products by calcRealDiff(). The two comparators compare by original price and by updated price.

Output

The sort is always descending and is produced by the sort itself (not sorted ascending and then reversed).

Number of products	What is printed
10 or fewer	Every product, labelled 1-st, 2-nd, 3-rd, 4-th, ...
11 to 1,000	First element is:, every 100th product, then Last element is:
More than 1,000	First element is:, every 1000th product, then Last element is:

Each row shows the class name with its package (for example products.Jeans) and the value that was sorted on. Real Difference is printed unrounded.

The last line is the sort time, for example:

text
Bubble Sort run time was: 0.425 milliseconds

Only the sort is timed, never the file reading or the printing.

Errors

A bad argument prints ERROR: <message> and then the usage line. A file problem prints ERROR: <message> only. Both go to the error stream. Examples:

text
ERROR: No arguments were given.
ERROR: -t must be o, u or d, found 'x'
ERROR: File not found: res/nope.txt
Performance note

Merge, quick and heap sort take roughly 0.5 to 2 seconds on products3.txt (1,000,000 products). Bubble, selection and insertion sort grow with the square of the size, so on that file they would take hours. Use them on products1.txt and products2.txt.

Building Sort.jar
In Eclipse, choose File, Export..., Java, Runnable JAR file.
Launch configuration: one whose main class is appDomain.AppDriver.
Export destination: the project folder, file name Sort.jar.
Library handling: keep the default.

Sort.jar is a build output and is not committed to this repository.

Team
Member	Owns
A (Vincent)	appDomain package and Sort.jar
B	products package, comparators, bubble sort, insertion sort
C	Order, selection, quick, merge and heap sort, heap sort write-up
Git workflow
Everyone pushes straight to main.
Pull before you start and again before you push.
Push only code that compiles.
Make small, frequent commits and stay inside your own files.
Never edit .project or .classpath, and never commit bin/.
