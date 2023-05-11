import java.util.*;
import java.io.*;

class Data_Structure_Program
{
    static Scanner console = new Scanner(System.in);
    //List of users chosen structures as well as tree that structures are stored in
    static Queue<Structure> structureQueue;
    static Set<Structure> structureSet;
    static void Main()
    {
        //Initialize users queue and Hash set of data structures
        initialize();
        int ans = 0;
        do
        {
            System.out.println("Welcome to the program builder!\n");
            System.out.println("Would you like to get information on data structures (0) \nOr get suggestions for your own program?(1)");
            int LR = console.nextInt();
            if(LR == 0)
            {
                structureInformation();
            }else if(LR==1)
            {
                programAssister();
            }
        }while(ans!=-1);
    }

    //Initializes HashSet of structures as well as users list
    static void initialize()
    {
        //Instaniating structures
        structureQueue = new LinkedList<Structure>();
        structureSet = new HashSet<Structure>();
        //Objects created with names, complexities stored as doubles
        structureSet.add(new Structure("Array", 2.0, 5, " A basic collection of indexed data in a fixed size", "O(N)","I"));
        structureSet.add(new Structure("Linked-List", 2.0, 1, " A linear collection of data where each is linked to each other. Very space efficient but not too time efficient", "O(N)", "XE"));
        structureSet.add(new Structure("Stack", 2.0 , 2, " A linear collection of data where each item is stacked onto each other, and the first item in is the last out", "O(N)", "E"));
        structureSet.add(new Structure("Queue", 2.0 ,2, " A linear collection of data where each item is put next each other  , and the first item in is the first out", "O(N)", "E"));
        structureSet.add(new Structure("Binary Tree", Math.log(2.0), 1, " A non-linear collection of data where data is represented as nodes, and a nodes left is 'smaller' and a nodes right is 'larger'", "O(LOG(N))", "XE"));
        structureSet.add(new Structure("HashSet", 1.0, 5, " A linear collection of data that can expand and shrink. Data is stored in a hash table which is placed using a hash function with gives every item in the set a unique value which is its index", "O(1)", "I"));
        structureSet.add(new Structure("TreeSet", Math.log(2.0), 5, " A linear collection of data that can expand and shrink. Data is stored in a tree that sorts data under the Comparable Interface or uses a comparator", "O(LOG(N))", "I"));
        structureSet.add(new Structure("ArrayList", 2.0, 5, " A linear collection of data which is an array that can expand and shrink. The base array is doubled each time its almost filled, so it's space-inefficent ", "O(N)", "I"));
        structureSet.add(new Structure("Max/Min Heap", Math.log(2.0), 1, " A collection of data that's stored in nodes, but the node is either larger than its children (Max), or smaller than its children (Min). It can be expressed as an array ", "O(LOG(N))", "E"));
    }

    static void programAssister()
    {
        //Hashset of the preffered data structures
        Set<Structure> preferenced = new HashSet<Structure>();

        System.out.println("NOTE: THIS PROGRAM ASSUMES YOU ALREADY HAVE A PROGRAM/IDEA IN MIND");
        blankSpace(3);
        System.out.println("You have selected: Program Builder");
        blankSpace(2);
        //If the user has no data structures

        //Get the users preference for space/time efficiency
        System.out.println("For your program, do you prioritize space or time efficiency (S for space and T for time)?");
        String s_t = console.next();
        blankSpace(2);
        //Space preference
        if(s_t.equalsIgnoreCase("S"))
        {
            System.out.println("You've selected space-preference");
            blankSpace(2);
            System.out.println("On a scale of 1-5, where 1 is a structure that only uses neccesary space, and 5 is a structure that generates alot of unused space, what would you consider the max-expensive?");
            int range = console.nextInt();

            //Find structures within the inexpensive range and add them to the preferences HashSet
            Iterator itr = structureSet.iterator();
            while(itr.hasNext())
            {
                Structure curr = (Structure)itr.next();
                if(((curr).getSpace()) <= range)
                {
                    preferenced.add(curr);
                }
            }
        }else if(s_t.equalsIgnoreCase("T"))
        {
            System.out.println("You've selected time-preference");
            blankSpace(2);
            System.out.println("Is O(N) complexity too expensive? (Y/N)");
            String exp = console.next();

            //Find structures within the inexpensive range and add them to the queue
            Iterator itr = structureSet.iterator();
            while(itr.hasNext())
            {
                Structure curr = (Structure)itr.next();
                //If O(N) is too expensive, only add the structures that are constant time or LOG(N)
                if(!exp.equalsIgnoreCase("Y"))
                {
                    if(curr.getTimeS().equals("O(1)") || curr.getTimeS().equals("O(LOG(N))"))
                    {
                        preferenced.add(curr);
                    }
                }else
                {
                    System.out.println("Since you accepted O(N), all of the following data structures can be used in your program");
                    System.out.println("The main thing you should focus on is the amount of space they take up, and your operations runtimes");
                    preferenced.addAll(structureSet);
                    break;
                }
            }

            blankSpace(3);
            //Ternary operator for printing the users choice
            String choice = s_t.equalsIgnoreCase("S") ? " space-complexity": " time-complexity";
            //Printing the users choice and reulting list of data 
            System.out.println("Based off your choice of " + choice + " we suggest these data structures for your program");
            System.out.println(preferenced);
        }
    }

    //If the user only wants information regarding structures, it prints the structures, assess the list, and gives the option to add structures
    static void structureInformation()
    {
        blankSpace(3);
        System.out.println("You have selected: Structure Information");
        int ans = 0;
        while(ans!=-1)
        {
            System.out.println("We will now print the lists of data structures in no particular order");
            System.out.println("When you find a data structure you like, simply enter its index and it will be added to your list");
            //Printing lists
            System.out.println();
            System.out.println("Data structure list:");
            int i =0;
            //Use a set iterator to print the elements of the HashSet, indexed
            Iterator<Structure> itr = structureSet.iterator();
            while(itr.hasNext()&&i<structureSet.size())
            {
                System.out.print("("+i+")");
                System.out.print(itr.next());
                System.out.println();
                i++;
            }
            blankSpace(5);

            System.out.println("Would you like to add any structures to your list? Enter its index now (-1 for no)");
            int index = console.nextInt();
            if(index!=-1)
            {
                Structure target;
                itr = structureSet.iterator();
                i=0;
                //Searching the HashSet for the index specified by the user
                if(index == 0)
                {
                    target = itr.next();
                }else
                {
                    while(itr.hasNext()&&i<index)
                    {
                        itr.next();
                        i++;
                    }
                    target = (Structure) itr.next();
                }
                //Adding to the queue
                if(!structureQueue.contains(target))
                {
                    structureQueue.add(target);
                }
            }

            //Print the queue
            printQueue();

            //Assess the logistics of the queue
            assessQueue();

            System.out.println("Would you like to quit? (-1 for yes, will continue loop if not)");
            ans = console.nextInt();
        }
    }

    //Simply prints the users Queue of structures
    static void printQueue()
    {
        //Needs an auxilary queue for printing
        Iterator<Structure> itr = structureQueue.iterator();
        int i =0;
        System.out.println("Your list of structures:");
        while(itr.hasNext())
        {
            Structure curr = (Structure) itr.next();
            System.out.println("(" + i + ")"+curr.getName());
            i++;
        }
    }

    static void assessQueue()
    {
        Structure curr;
        blankSpace(5);
        //First function: Find the most common complexity
        HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
        Iterator<Structure> itr = structureQueue.iterator();

        //Map each Complexity String with its frequency in the HashMap
        while(itr.hasNext())
        {
            curr = (Structure) itr.next();
            if(frequencies.containsKey((((Structure)curr).getTimeS())))
            {
                frequencies.put(((Structure)curr).getTimeS(), frequencies.get(((Structure)curr).getTimeS())+1);
            }else
            {
                frequencies.put(((Structure)curr).getTimeS(), 1);
            }
        }
        //Get the most frequent complexity in the map
        String result = "";int maxCount = 0;
        for(String comp: frequencies.keySet())
        {
            if(frequencies.get(comp) > maxCount)
            {
                result = comp;
            }
        }
        blankSpace(2);
        //Prints the average runtime for the users 'program', which is the queue of structures
        System.out.println("Your programs average complexity is: " + result);
        blankSpace(2);
        //Second function: Split the users list into four categories: time expensive; space expensive; time efficient; space efficient
        Stack<Structure> tEfficient = new Stack<Structure>(); Stack<Structure> sEfficient = new Stack<Structure>(); Stack<Structure> sExpensive = new Stack<Structure>(); Stack<Structure> tExpensive = new Stack<Structure>();
        itr = structureQueue.iterator();
        while(itr.hasNext())
        {
            curr = (Structure) itr.next();
            //If it's space expensive
            if(curr.getSpace() < 3)
            {
                sEfficient.push(curr);
            }else//Not space expensive
            {
                sExpensive.push(curr);
            }
            //Time efficient
            if(Double.compare(curr.getTime(), 2) < 0)
            {
                tEfficient.push(curr);
            }else if(Double.compare(curr.getTime(), 2) >= 0)//Time expensive
            {
                tExpensive.push(curr);
            }
        }
        //Printing results
        blankSpace(2);
        //Print time efficient
        System.out.println("Time efficient structures:");
        for(Structure current: tEfficient)
        {
            System.out.println(current.getName() + ", " + current.getTimeS() + " time complexity");
        }
        tEfficient.clear();
        blankSpace(2);
        System.out.println("Space efficient structures:");
        for(Structure current: sEfficient)
        {
            System.out.println(current.getName() + ", " + current.getTimeS() + " time complexity");
        }
        sEfficient.clear();
        blankSpace(2);
        System.out.println("Space expensive structures:");
        for(Structure current: sExpensive)
        {
            System.out.println(current.getName() + ", " + current.getTimeS() + " time complexity");
        }
        sExpensive.clear();
        blankSpace(2);
        System.out.println("Time expensive structures:");
        for(Structure current: tExpensive)
        {
            System.out.println(current.getName() + ", " + current.getTimeS() + " time complexity");
        }
        tExpensive.clear();
        blankSpace(5);
    }

    //Prints 'rep' amount of blank spaces
    public static void blankSpace(int rep)
    {
        for(int i =0; i<rep; i++)
            System.out.println();
    }
}