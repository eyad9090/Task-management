package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Helper extends JFrame {
    public static ImageIcon getImage(String path, int x, int y) {
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Helper.class.getResource(path)));
        Image imageSize = image.getImage();
        imageSize = imageSize.getScaledInstance(
                x,
                y,
                Image.SCALE_SMOOTH
        );
        image = new ImageIcon(imageSize);
        return image;
    }
}
