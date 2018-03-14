import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public final class Board
{
    private final int width = 100;
    private final int height = 15;
    private ArrayList<Point> creatures = new ArrayList<>();
    private ArrayList<Point> neighboors = new ArrayList<>();
    private ArrayList<Point> futurCrea = new ArrayList<>();

    private Point[][] board;

    public static void main(String[] args)
    {
        new Board(Constants.ROW);
    }

    public  Board(int setUp)
    {
        initBoard();
        addCrea(setUp);
        createLife();
    }
    private void createLife()
    {
        while (true)
        {
            generateNeighboors();
            handleCrea();
            handleNeighboors();

            drawBoard();

            prepareNextGen();

            wait1();
        }
    }
    private void prepareNextGen()
    {
        resetNeighboors();

        for (Point creature : creatures)
            creature.changeType(Constants.VOID);
        creatures.clear();

        creatures = new ArrayList<>(futurCrea);

        for (Point creature : creatures)
            creature.changeType(Constants.CREATURE);

        futurCrea.clear();


    }
    private void handleCrea()
    {
        for (Point creature : creatures)
        {
            if (creature.getNeighboors() == 2 || creature.getNeighboors() == 3)
                futurCrea.add(creature);
        }
    }
    private void handleNeighboors()
    {
        for (Point neighboor : neighboors)
        {
            if (neighboor.getNeighboors() == 3 && !(futurCrea.contains(neighboor)))
                futurCrea.add(neighboor);
        }
    }
    private void wait1()
    {
        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e)
        {
            System.err.print("Error while waiting : "+ e);
        }
    }
    private void generateNeighboors()
    {
        for (Point creature : creatures)
        {
            ArrayList<Point> currentNeighboors = getNeighboors(creature);
            for (Point currentNeighboor : currentNeighboors)
            {
                currentNeighboor.addNeighboors();
                if (currentNeighboor.getType() != Constants.CREATURE && !(neighboors.contains(currentNeighboor)))
                    neighboors.add(currentNeighboor);
            }

        }
    }
    private void resetNeighboors()
    {
        for (Point neighboor : neighboors)
            neighboor.resetNeighboors();
        neighboors.clear();

        for (Point creature : creatures)
            creature.resetNeighboors();
    }
    private ArrayList<Point> getNeighboors(Point crea)
    {
        int x = crea.getX();
        int y = crea.getY();
        ArrayList<Point> temp = new ArrayList<>();
        if(board[x][y+1].getType() != Constants.WALL)
            temp.add(board[x][y+1]);
        if(board[x][y-1].getType() != Constants.WALL)
            temp.add(board[x][y-1]);
        if(board[x+1][y].getType() != Constants.WALL)
            temp.add(board[x+1][y]);
        if(board[x-1][y].getType() != Constants.WALL)
            temp.add(board[x-1][y]);
        if(board[x+1][y+1].getType() != Constants.WALL)
            temp.add(board[x+1][y+1]);
        if(board[x+1][y-1].getType() != Constants.WALL)
            temp.add(board[x+1][y-1]);
        if(board[x-1][y+1].getType() != Constants.WALL)
            temp.add(board[x-1][y+1]);
        if(board[x-1][y-1].getType() != Constants.WALL)
            temp.add(board[x-1][y-1]);
        return temp;
    }
    private void initBoard()
    {
        board = new Point [width][height];
        for (int x =0; x < width;x++)
        {
            for(int y =0;y<height ; y++)
            {
                if(x==0 || x == width-1)
                    board[x][y] = new Point(Constants.WALL,x,y);
                else if((y == 0) || (y == (height - 1)))
                    board[x][y] = new Point(Constants.WALL,x,y);
                else
                    board[x][y] = new Point(Constants.VOID,x,y);
            }
        }
    }
    private void addCrea(int setUp)
    {
        if(setUp == Constants.ROW)
        {
            for(int j =45;j<55;j++)
            {
                board[j][7].changeType(Constants.CREATURE);
                creatures.add(board[j][7]);
            }
        }
        else if (setUp == Constants.EXPLO)
        {
            board[50][6].changeType(Constants.CREATURE);
            creatures.add(board[50][6]);
            board[51][6].changeType(Constants.CREATURE);
            creatures.add(board[51][6]);
            board[51][5].changeType(Constants.CREATURE);
            creatures.add(board[51][5]);
            board[51][7].changeType(Constants.CREATURE);
            creatures.add(board[51][7]);
            board[52][7].changeType(Constants.CREATURE);
            creatures.add(board[52][7]);
            board[52][5].changeType(Constants.CREATURE);
            creatures.add(board[52][5]);
            board[53][6].changeType(Constants.CREATURE);
            creatures.add(board[53][6]);
        }
    }
    private void drawBoard()
    {
        for(int j =0; j< height; j++)
        {
            for (int i =0; i < width;i++)
                System.out.print(Constants.list[board[i][j].getType()]);
            System.out.print("\n");
        }
    }
}
