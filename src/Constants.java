
public final class Constants
{
    private  Constants()
    {
        System.err.println("This class should never be instantiate.");
        System.exit(1);
    }

    public static final int VOID = 0;
    public static final int CREATURE = 1;
    public static final int WALL = 2;
    public static final char[] list = {' ','O','#'};

    public static final int ROW = 0;
    public static final int EXPLO = 1;
}
