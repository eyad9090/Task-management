package View;
import java.awt.*;

/**
 * this class deal with sizw of screen and componenets
 */
public class Size {
    private final int sizeX;
    private final int sizeY;
    Toolkit mySize;
    public Size()
    {
        this.mySize=Toolkit.getDefaultToolkit();
        this.sizeX=mySize.getScreenSize().width;
        this.sizeY=mySize.getScreenSize().height;
    }
    public int sizeXPercent(double percent)
    {
        percent/=100;
        return (int)(sizeX*percent);
    }
    public int sizeXPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int sizeYPercent(double percent)
    {
        percent/=100;
        return (int)(sizeY*percent);
    }
    public int sizeYPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
}
