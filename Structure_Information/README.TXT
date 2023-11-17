Welcome to my Structure Information program!

This program generally serves as a program-builder/assister. There are two ways it suggets structures for use:
A) A user simply wants to see a list of Java data structures, and append the ones they prefer onto a Queue. Each structure is an object contains a name of the structre,
a double representing the time complexity, a string representing the time complexity, a 1-5 rating of its space-usage, a "XE (Extremely efficient)", "E (Efficient)", or "I (Inefficient)" rating for storage-usage, and a description of the stucture. 

So once the user sees the Hashset of the structures and all of their features/description, they can add it to their Queue. Each time a structure-object is added onto the Queue, basic statistics are printed about the Queue. For example, four stacks are created and each holds stuctures of the following criteria: space efficient structures, space expensive structures, time expensive structures, and time efficient structures. The program also prints out the most common time complexity in the users Queue, using a Hashmap that contains complexities and their frequencies.

The other feature of this program is an assister. It helps a user decide data structures that'd be the best for a users program. 
The first thing this poriton will do is request whether the user prefers space efficiency, or time efficiency. If the choice was space-efficiency, the program will request a threshold for what is too expensive, and it will iterate through the Hashset and add the structures who have space-efficencies under the threshold to the users queue.
If the user chose time-efficiency, the program will request whether or not O(N) is too expensive, and will add structures to the queue depending on that answer.

This program utilizes Hashmaps, Queues, Stacks, iterators, objects, and user input.

The main file is "Data_Structure_Program.java"
