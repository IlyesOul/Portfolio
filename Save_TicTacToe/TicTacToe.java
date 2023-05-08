import java.util.*;

class TicTacToe{
    static Scanner console = new Scanner(System.in);
    static HashTTTSet table = new HashTTTSet();
    static LinkedList<TTT> list = new LinkedList<TTT>();
    static boolean inRow=false;
    static int inARow = -1, rows = 0, cols =0, c =0, onBoard, xCount, oCount;
    static boolean xWon = false;
    static char whoWon = ' ';
    static void main()
    {
        System.out.println("What is the inARow?");
        inARow = console.nextInt();
        System.out.println();
        System.out.println("How many columns?");
        cols = console.nextInt();
        System.out.println("How many rows?");
        rows = console.nextInt();
        System.out.println("How many tacs on the board at a time?");
        onBoard = console.nextInt();
        do
        {
            TTT p = c%2==0? makeTTT('X', c): makeTTT('O', c);
            list.add(p);
            table.add(p);
            check();
            System.out.println("TTT at " + p);
            print();
            c++;
        }while(!inRow);

        System.out.println("GAME OVER !!!");
        whoWon = xWon? 'X': 'O';
        System.out.println(whoWon + " WON THE GAME!!!");
    }

    static TTT makeTTT(char let, int pl)
    {   
        int x = -1, y = -1, i =0;
        while((x > cols&&y>rows)||(x<0&&y<0)||table.contains(x, y, let)){
            // If the TTT is invalid
            if(i!=0)
                System.out.println("Coordinate is invalid, try again:");

            System.out.println(let + ", what do you want your TTTs X to be? (Must be  between 1-"+(cols)+")" + "\n");
            x = console.nextInt();
            System.out.println();
            System.out.println();
            System.out.println(let+ ",what do you want your TTTs Y to be? (Must be  between 1-"+(rows)+")" + "\n");
            y = console.nextInt();
            i++;
        }
        while(table.isTaken(x, y))
        {
            System.out.println("Space already taken, decide again");
            System.out.println(let + ", what do you want your TTTs X to be? (Must be  between 1-"+(cols)+")" + "\n");
            x = console.nextInt();
            System.out.println(let + ", what do you want your TTTs Y to be? (Must be  between 1-"+(rows)+")" + "\n");
            y = console.nextInt();
        }
        if(let == 'X')
        {
            xCount++;
        }else
        {
            oCount++;
        }
        return new TTT(x, y, let);
    }

    static void check()
    {
        if(list.size() == 1||list.size()<inARow+2)
            return; 
        int u = 1, d = 1, l = 1, r = 1, ru = 1, rd = 1, lu = 1, ld = 1;
        int u2 = 1, d2 = 1, l2 = 1, r2 = 1, ru2 = 1, rd2 = 1, lu2 = 1, ld2 = 1;
        //Most recent x and o's
        TTT recent = list.get(list.size()-1).getChar() == 'X'? new TTT(list.get(list.size()-1).getX(), list.get(list.size()-1).getY(), 'X'): new TTT(list.get(list.size()-1).getX(),list.get(list.size()-1).getY() ,'O');
        TTT recent2 = list.get(list.size()-2).getChar() == 'X'? new TTT(list.get(list.size()-2).getX(), list.get(list.size()-2).getY(), 'X'): new TTT(list.get(list.size()-2).getX(),list.get(list.size()-2).getY() ,'O');
        TTT remove1 = list.get(0), remove2 = list.get(1);
        //Removing the last X or O
        if((xCount == onBoard+1&&list.get(0).getChar()=='X'))
        {
            System.out.println("Removed (1)" + list.get(0));
            list.remove(0);
            table.remove(remove1);
            xCount--;
        }else if((xCount == onBoard+1&&list.get(1).getChar()=='X'))
        {
            list.remove(1);
            table.remove(remove2);
            xCount--;
            System.out.println("Removed (2)" + list.get(0));
        }else if((oCount == onBoard+1&&list.get(0).getChar()=='O'))
        {
            list.remove(0);
            table.remove(remove1);
            oCount--;
            System.out.println("Removed (3)" + list.get(0));
        }
        else if((oCount == onBoard+1&&list.get(1).getChar()=='O'))
        {
            list.remove(1);
            table.remove(remove2);
            oCount--;
            System.out.println("Removed (4)" + list.get(0));
        }
        // Check vertically
        for(int i =1; i<= inARow; i++)
        {
            if(recent.getY()-i>=0)
            {
                if(table.contains(recent.getX(), recent.getY() - i, 'X'))
                {
                    d++;
                } if(!table.contains(recent.getX(), recent.getY() - i, 'X'))
                {
                    d=0;
                }else if(table.contains(recent.getX(), recent.getY()-i, 'O'))
                {
                    d2++;
                }if(!table.contains(recent.getX(), recent.getY()-i, 'O'))
                {
                    d2=0;
                }
            }
            if(recent.getY()+i <= rows)
            {
                if(table.contains(recent.getX(), recent.getY() + i, 'X'))
                {
                    u++;
                }if(!table.contains(recent.getX(), recent.getY() + i, 'X'))
                {
                    u=0;
                }else if(table.contains(recent.getX(), recent.getY() + i, 'O'))
                {
                    u2++;
                }if(!table.contains(recent.getX(), recent.getY() + i, 'O'))
                {
                    u2 =0;
                }
            }
            if(u == inARow||d==inARow)
            {
                inRow = true;
                xWon = true;
            }else if(u2 == inARow||d2==inARow)
            {
                inRow = true;
                xWon = false;
            }
        }

        //Check horizontally
        for(int i = 1; i<=inARow; i++)
        {
            if(recent.getX() - i>= 0)
            {
                if(table.contains(recent.getX() - i, recent.getY(), 'X'))
                {
                    l++;
                }
                if(!table.contains(recent.getX() - i, recent.getY(), 'X'))
                {
                    l=0;
                }else if(table.contains(recent.getX() -i, recent.getY(), 'O'))
                {
                    l2++;
                }if(!table.contains(recent.getX() -i, recent.getY(), 'O'))
                {
                    l2 = 0;
                }
            }
            if(recent.getX() + i<= cols)
            {
                if(table.contains(recent.getX() + i, recent.getY(), 'X'))
                {
                    r++;
                }if(!table.contains(recent.getX() + i, recent.getY(), 'X'))
                {
                    r=0;
                }else if(table.contains(recent.getX() +i, recent.getY(), 'O'))
                {
                    r2++;
                }if(!table.contains(recent.getX() +i, recent.getY(), 'O'))
                {
                    r2=0;
                }
            }
            if(l == inARow||r==inARow)
            {
                inRow = true;
                xWon = true;
            }else if(r==inARow||r2==inARow)
            {
                inRow = true;
                xWon = false;
            }
        }

        //Left down
        for(int i = 1; i<=inARow; i++)
        {  
            if((recent.getX() -i >= 0 && recent.getY()-i >= 0))
            {
                if(table.contains(recent.getX()-i, recent.getY()-i, 'X'))
                {
                    ld++;
                }if(!table.contains(recent.getX()-i, recent.getY()-i, 'X'))
                {
                    ld=0;
                }else if(table.contains(recent.getX()-i, recent.getY()-i, 'O'))
                {
                    ld2++;
                }if(!table.contains(recent.getX()-i, recent.getY()-i, 'O'))
                {
                    ld2=0;
                }
            }
            if(ld == inARow)
            {
                inRow = true;
                xWon = true;
            }else if(ld2 == inARow)
            {
                inRow = true;
                xWon = false;
            }
        }

        //Right up
        for(int i = 1; i<=inARow; i++)
        {
            if((recent.getX() +i <= cols && recent.getY()+i <= rows))
            {
                if(table.contains(recent.getX()+i, recent.getY()+i, 'X'))
                {
                    ru++;
                }if(!table.contains(recent.getX()+i, recent.getY()+i, 'X'))
                {
                    ru=0;
                }else if(table.contains(recent.getX()+i, recent.getY()+i, 'O'))
                {
                    ru2++;
                }if(!table.contains(recent.getX()+i, recent.getY()+i, 'O'))
                {
                    ru2=0;
                }
            }
            if(ru == inARow)
            {
                inRow = true;
                xWon = true;
            }else if(ru2 == inARow)
            {
                inRow = true;
                xWon = false;
            }
        }
        //Right down
        for(int i =1; i<=inARow; i++)
        {
            if((recent.getX()+i <= cols&&recent.getY()-i >=0))
            {
                if(table.contains(recent.getX()+i, recent.getY()-i, 'X'))
                {
                    rd++;
                }if(!table.contains(recent.getX()+i, recent.getY()-i, 'X'))
                {
                    rd=0;
                }else if(table.contains(recent.getX()+i, recent.getY()-i, 'O'))
                {
                    rd2++;
                }if(!table.contains(recent.getX()+i, recent.getY()-i, 'O'))
                {
                    rd2=0;
                }
            }
            if(rd == inARow)
            {
                inRow = true;
                xWon = true;
            }else if(rd2 == inARow)
            {
                inRow = true;
                xWon = false;
            }
        }

        //Left up
        for(int i =1; i<=inARow; i++)
        {
            if((recent.getX()-i>=0&&recent.getY()+i <=rows))
            {
                if(table.contains(recent.getX()-i, recent.getY()+i, 'X'))
                {
                    lu++;
                }if(!table.contains(recent.getX()-i, recent.getY()+i, 'X'))
                {
                    lu=0;
                }else if(table.contains(recent.getX()-i, recent.getY()+i, 'O'))
                {
                    lu2++;
                }if(!table.contains(recent.getX()-i, recent.getY()+i, 'O'))
                {
                    lu2=0;
                }
            }
            if(lu == inARow)
            {
                inRow = true;
                xWon = true;
            }else if(lu2 == inARow)
            {
                inRow = true;
                xWon = false;
            }
        }
    }

    static void print()
    {
        for(int i =1; i<=rows; i++)
        {
            for(int j = 1; j<=cols; j++)
            {
                if(table.contains(j, i, 'X'))
                    System.out.print("|   X   | ");
                else if(table.contains(j, i, 'O'))
                    System.out.print("|   O   |");
                else
                    System.out.print("|       |");
            }
            System.out.println();
        }
    }
}