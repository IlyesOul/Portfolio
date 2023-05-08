public class TTT
{
    private int x, y, pl;
    private char symbol;

    public TTT(int x, int y, char symbol)
    {
        this.x = x;this.y = y;
        this.symbol = symbol;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public char getChar()
    {
        return symbol;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }    

    public void setChar(char symb)
    {
        symbol = symb;
    }

    boolean equal(TTT p)
    {
        return (this.getX()==p.getX()) && (this.getY()==p.getY())&& ((this.getChar() ==p.getChar()));
    }

    boolean isTaken(TTT p)
    {
        return (this.getX()==p.getX()) && (this.getY()==p.getY());
    }

    public String toString()
    {
        return "[" + getChar() +", " +  getX() + ", " + getY() +  "]";
    }
}