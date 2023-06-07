package View;
import java.awt.*;

/**
 * deal with set positions of components
 */
public class Position {
    private final int sizeX;
    private final int sizeY;
    Toolkit mySize;
    public Position()
    {
        this.mySize=Toolkit.getDefaultToolkit();
        this.sizeX=mySize.getScreenSize().width;
        this.sizeY=mySize.getScreenSize().height;
    }
    public int moveXPercent(double percent)
    {
        percent/=100;
        return (int)(sizeX*percent);
    }
    public int moveXPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int moveYPercent(double percent)
    {
        percent/=100;
        return (int)(sizeY*percent);
    }
    public int moveYPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int centerX(int width)
    {
        return (sizeX/2)-(width/2);
    }
    public int centerY(int height)
    {
        return (sizeY/2)-(height/2);
    }

    public int centerX(int size,int width)
    {
        return (size/2)-(width/2);
    }
}
