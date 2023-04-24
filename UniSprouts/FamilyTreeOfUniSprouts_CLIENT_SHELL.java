import java.util.*;
import java.util.Scanner;

public class FamilyTreeOfUniSprouts_CLIENT_SHELL
{
    static FamilyTreeOfUniSprouts_Node root;
    static FamilyTreeOfUniSprouts_Node auxilaryUniSproutNode;
    //LIST FOR PRINTING AND NOTHING ELSE
    static HashSet<String> children;static HashSet <String> gParents; static HashSet<String>parents; 
    static HashSet<String>nieceNephew; static HashSet<String>cousin;static HashSet<String>gChildren;static HashSet<String>siblings;
    static String names[][] = {{"Jones", "ROOT-Node"},
            {"Bill","Jones"},  {"Katy","Jones"}, {"Mike","Jones"}, {"Tom","Jones"},
            {"Dave","Bill"},  {"Mary","Bill"}, {"Leo","Katy"}, {"Betty","Mike"}, 
            {"Roger","Mike"}, {"Larry","Mary"}, {"Paul","Mary"}, {"Penny","Mary"},
            {"Don","Betty"}, {"Petter","Paul"}, {"Dave2","Don"}
        };    

    // ====================================================================
    // =========================== MAIN ==================================== 
    // ====================================================================
    public static void main()
    {
        children=new HashSet<String>();gParents=new HashSet<String>();parents=new HashSet<String>();
        nieceNephew=new HashSet<String>();cousin=new HashSet<String>();gChildren=new HashSet<String>();siblings=new HashSet<String>();
        int  playAgain;
        String name, namesList= "\n"; 
        auxilaryUniSproutNode = null;
        Scanner sc = new Scanner(System.in);
        // Build the Family of UniSprouts Tree       

        do {
            root =  new FamilyTreeOfUniSprouts_Node(names[0][0] + ","+names[0][1], 0, null);
            // Gather names
            for (int r=0; r<names.length; r++) {
                namesList += "   " + names[r][0] + "     \t" + names[r][1] + "\n";                
            }

            // Output namesList         
            System.out.println(" nameList: \n  Parent Child" + namesList);
            buildFamilyTreeOfUniSprouts();
            printFamilyTreeOfUniSprouts(root);            

            // Input name + print all relatives
            System.out.print("\n\n Enter a name from which to get GrandParent/Parent/Siblings/Cousins/Children/GrandChildren/"+
                "Nieces/Nephews: ");
            name = sc.next();
            name = toTitle(name);
            goTo(name, root);
            name = auxilaryUniSproutNode.getName();
            printRelatives(name, root);
            printLists();
            // Play Again?
            System.out.print("\n\n Play Again? (1==yes, 2==no): ");
            playAgain = sc.nextInt();            
        } while (playAgain == 1);
    } // main  
    // ==================== buildFamilyTreeOfUniSprouts ====================== 
    public static void buildFamilyTreeOfUniSprouts()
    {
        FamilyTreeOfUniSprouts_Node aux2;
        for(int i =1; i<names.length; i++)
        {
            goTo(names[i][1], root);
            FamilyTreeOfUniSprouts_Node node = auxilaryUniSproutNode;
            if(node.getChildren()!=null)
            {
                aux2 = node.getChildren();
                while(aux2.getNext()!=null)
                {
                    aux2 = aux2.getNext();
                }
            }else
            {
                aux2 = node;
            }
            placeNodeInFamilyTreeOfUniSprouts(node, new FamilyTreeOfUniSprouts_Node(names[i][0]+","+names[i][1], node.getGenerationLevel()+1, aux2));
        }
    }

    private static void goTo(String lName, FamilyTreeOfUniSprouts_Node current)
    {
        if(current!=null)
        {
            if(current.getName().substring(0, current.getName().indexOf(',')).equals(lName))
            {
                auxilaryUniSproutNode = current;
            }else
            {
                goTo(lName, current.getChildren());
                goTo(lName, current.getNext());
            }
        }
    }
    // ============== placeNodeInFamilyTreeOfUniSprouts  ================= 
    public static void placeNodeInFamilyTreeOfUniSprouts(FamilyTreeOfUniSprouts_Node   
    parent, FamilyTreeOfUniSprouts_Node child)
    {
        if(parent.getChildren() == null)
        {
            parent.setChildren(child);
        }else
        {
            FamilyTreeOfUniSprouts_Node curr = parent.getChildren();
            while(curr.getNext()!=null)
            {
                curr = curr.getNext();
            }
            curr.setNext(child);
        }
    } // placeNodeInFamilyTreeOfUniSprouts()

    // ========================= printRelatives =============================== 
    public static void printRelatives(String name,FamilyTreeOfUniSprouts_Node current)
    {
        if(current!=null)
        {
            goTo(name, root);
            //If this name's last is equal to the givens first, means under
            boolean isLowerName = current.getName().substring(current.getName().indexOf(',')+1, current.getName().length()).equals(name.substring(0, name.indexOf(',')));
            FamilyTreeOfUniSprouts_Node dn = auxilaryUniSproutNode;
            //Children
            if(isLowerName&&current.getGenerationLevel()-auxilaryUniSproutNode.getGenerationLevel()  == 1)
            {
                children.add(current.getName().substring(0, current.getName().indexOf(',')));
                //Parents
            }if(current.getName().substring(0, current.getName().indexOf(',')).equals(name.substring(name.indexOf(',')+1,name.length()))&&current.getGenerationLevel() -auxilaryUniSproutNode.getGenerationLevel()==-1)
            {
                parents.add(current.getName().substring(0, current.getName().indexOf(',')));
                //Siblings
            }if(current.getName().substring(current.getName().indexOf(',')+1, current.getName().length()).equals(name.substring(name.indexOf(',')+1, name.length()))&&!current.getName().equals(name))
            {
                siblings.add(current.getName().substring(0, current.getName().indexOf(',')));
                //Cousins
            }if(current.getGenerationLevel() == auxilaryUniSproutNode.getGenerationLevel()&&!current.getName().substring(current.getName().indexOf(','), current.getName().length()).equals(name.substring(name.indexOf(','), name.length())))
            {
                cousin.add(current.getName().substring(0, current.getName().indexOf(',')));
            }//Grandchildren

            if(auxilaryUniSproutNode!=null&&auxilaryUniSproutNode.getChildren()!=null)
            {
                dn = auxilaryUniSproutNode.getChildren();
                while(dn.getNext()!=null)
                {
                    if(dn.getChildren()!=null)
                    {
                        FamilyTreeOfUniSprouts_Node n = dn.getChildren();
                        while(n!=null&&n.getGenerationLevel() == auxilaryUniSproutNode.getGenerationLevel()+2)
                        {
                            gChildren.add(n.getName().substring(0, dn.getChildren().getName().indexOf(',')));
                            n = n.getNext();
                        }
                    }
                    dn = dn.getNext();
                }
            }

            //Grandparents
            dn = auxilaryUniSproutNode;
            if(current.getPrevious()!=null)
            {
                //Go To parent
                while(!dn.getName().substring(0, dn.getName().indexOf(',')).equals(name.substring(name.indexOf(',')+1, 
                        name.length()))&&dn.getPrevious()!=null){
                    dn = dn.getPrevious();
                }
                //Go To Parent Of Parent
                FamilyTreeOfUniSprouts_Node dn2 = dn;
                while(!dn2.getName().substring(0, dn2.getName().indexOf(',')).equals(dn.getName().substring(dn.getName().indexOf(',')+1, 
                        dn.getName().length()))&&dn.getPrevious()!=null){
                    dn2 = dn2.getPrevious();
                }//End of Iteration (Dn2 on Grandparent)
                if(dn2.getGenerationLevel()-auxilaryUniSproutNode.getGenerationLevel()==-2)
                {
                    gParents.add(dn2.getName().substring(0, dn2.getName().indexOf(',')));
                }
            }//End Of Grandparents

            //Nieces/Nephews

            dn = current;
            //Go To parent of current
            if(!current.getName().substring(current.getName().indexOf(',')+1, current.getName().length()).equals(name.substring(0, name.indexOf(','))))
            {
                while(!dn.getName().substring(0, dn.getName().indexOf(',')).equals(current.getName().substring(current.getName().indexOf(',')+1, current.getName().length()))&&dn.getPrevious()!=null)
                {
                    dn = dn.getPrevious();
                }
                if(current.getGenerationLevel()-auxilaryUniSproutNode.getGenerationLevel()==1&&dn.getName().substring(dn.getName().indexOf(',')+1, dn.getName().length()).equals(name.substring(name.indexOf(',')+1,name.length())))
                {
                    nieceNephew.add(current.getName().substring(0, current.getName().indexOf(',')));
                }//End of Nieces/Nephews
            }
            printRelatives(name, current.getNext());
            printRelatives(name, current.getChildren());
        }
    }  // printRelatives()

    public static void printFamilyTreeOfUniSprouts(FamilyTreeOfUniSprouts_Node current) {
        for (String row : subtreeToString(current))
            System.out.println(row);
    } // printFamilyTreeOfUniSprouts()

    public static String[] subtreeToString(FamilyTreeOfUniSprouts_Node node) {
        final List<String[]> subtrees = new LinkedList<>();
        int rowCount = 0;
        int colCount = 0;
        for (FamilyTreeOfUniSprouts_Node c = node.getChildren(); c != null; c = c.getNext()) {
            final String[] subtree = subtreeToString(c);
            rowCount = Math.max(subtree.length, rowCount);
            colCount += subtree[0].length();
            subtrees.add(subtree);
        }
        if (rowCount == 0)
            rowCount++;
        else
            rowCount += 2;
        final String[] rowStrings = new String[rowCount];
        final String name = node.getName();
        colCount += subtrees.size() - 1;
        if (name.length() > colCount) {
            if (rowCount > 1)   {
                final String[] firstCol = subtrees.get(0);
                final String firstBorder = " ".repeat((name.length() - colCount) / 2);
                for (int i = 0; i < firstCol.length; i++)
                    firstCol[i] = firstBorder + firstCol[i];

                final String[] lastCol = subtrees.get(subtrees.size() - 1);
                final String lastBorder = " ".repeat((name.length() - colCount + 1) / 2);
                for (int i = 0; i < lastCol.length; i++)
                    lastCol[i] += lastBorder;
            }
            colCount = name.length();
        }
        rowStrings[0] = " ".repeat((colCount - name.length()) / 2)
        + name
        + " ".repeat((colCount - name.length() + 1) / 2);
        if (rowCount > 1) {
            final StringBuilder[] builders = new StringBuilder[rowStrings.length - 2];
            for (int i = 0; i < builders.length; i++)
                builders[i] = new StringBuilder(colCount);
            final char[] branchChars = new char[colCount];
            for (int i = 0; i < branchChars.length; i++)
                branchChars[i] = '\u2500';
            final int center = (colCount - 1) / 2;
            int i = 0;
            final ListIterator<String[]> iter = subtrees.listIterator();
            while (iter.hasNext()) {
                final boolean isFirst = !iter.hasPrevious();
                final String[] col = iter.next();
                final int subColSize = col[0].length();
                final boolean isLast = !iter.hasNext();
                final int subCenter = (subColSize - 1) / 2;

                char centerChar = '\u252c';
                if (isFirst ^ isLast) {
                    if (isFirst) {
                        centerChar = '\u250c';
                        for (int j = 0; j < subCenter; j++)
                            branchChars[j] = ' ';
                    }
                    else { // isLast
                        centerChar = '\u2510';
                        for (int j = i + subCenter; j < branchChars.length; j++)
                            branchChars[j] = ' ';
                    }
                }
                else if (isFirst && isLast) {
                    centerChar = ' ';
                    for (int j = 0; j < branchChars.length; j++)
                        branchChars[j] = ' ';
                }
                branchChars[i + subCenter] = centerChar;

                for (int j = 0; j < col.length; j++) {
                    builders[j].append(col[j]);
                    assert col[j].length() == subColSize;
                }
                for (int j = col.length; j < builders.length; j++)
                    builders[j].append(" ".repeat(subColSize));

                if (!isLast) {
                    for (StringBuilder builder : builders)
                        builder.append(' ');
                }

                i += subColSize + 1;
            }

            char centerChar;
            switch (branchChars[center]) {
                case '\u2500':
                    centerChar = '\u2534';
                    break;
                case '\u252c': 
                    centerChar = '\u253c';
                    break;
                case ' ':
                    centerChar = '\u2502';
                    break;
                default:
                    throw new IllegalStateException("Illegal central character '" + branchChars[center] + "' in \"" + new String(branchChars) + '"');
            }

            branchChars[center] = centerChar;

            rowStrings[1] = new String(branchChars);

            for (i = 0; i < builders.length; i++)
                rowStrings[i + 2] = builders[i].toString();
        }

        return rowStrings;
    }

    static void printLists()
    {
        System.out.println("Children:");
        for(String child : children)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Grandchildren:");
        for(String child : gChildren)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Parents:");
        for(String child : parents)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Niece/Nephews:");
        for(String child : nieceNephew)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Grand Parents:");
        for(String child : gParents)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Cousins:");
        for(String child : cousin)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Siblings:");
        for(String child : siblings)
        {
            System.out.print(" " + child + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String toTitle(String s)
    {
        String ret = "";
        for(int i =0; i<s.length(); i++)
        {
            if(i==0)
            {
                ret+=Character.toUpperCase(s.charAt(i));
            }else
            {
                ret+=Character.toLowerCase(s.charAt(i));
            }
        }
        return ret;
    }
}  // FamilyTreeOfUniSprouts_CLIENT