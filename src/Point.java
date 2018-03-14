

public final class Point
{
    private int type;
    private int[] coord = new int [2];
    private int neighboors = 0;

    public Point(int type , int x, int y)
    {
        this.type = type;
        this.coord[0]=x;
        this.coord[1]=y;
    }

    public int getType()
    {
        return type;
    }

    public void changeType(int newType)
    {
        type = newType;
    }

    public int getNeighboors()
    {
        return neighboors;
    }
    public void addNeighboors()
    {
        neighboors ++;
    }
    public void resetNeighboors()
    {
        neighboors = 0;
    }
    public int getX()
    {
        return coord[0];
    }
    public int getY()
    {
        return coord[1];
    }
}

