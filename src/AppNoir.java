import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class AppNoir extends GraphicsApp implements AppConfig {

    private Image originalImage;
    private Image grayscaleImage;
    private boolean drawGrayscaleImage;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
        drawGrayscaleImage = false;
        originalImage = createImage(IMAGE_PATH, false);
        grayscaleImage = createImage(IMAGE_PATH, true);
    }

    private Image createImage(String path, boolean grayscale) {
        Image image = new Image(0, 0, path);
        image.setWidth(CANVAS_WIDTH, true);
        image.setHeight(CANVAS_HEIGHT, true);
        if (grayscale) {
            setGrayscaleModeFor(image);
        }
        return image;
    }

    private void setGrayscaleModeFor(Image image) {
        int[][] pixels = image.getPixelArray();
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                Color color = new Color(pixels[x][y]);
                int colorAverage = (color.red() + color.green() + color.blue()) / 3;
                Color grayscaleColor = new Color(colorAverage, colorAverage, colorAverage);
                pixels[x][y] = grayscaleColor.toInt();
            }
        }
        image.setPixelArray(pixels);
    }

    @Override
    public void draw() {
        if (drawGrayscaleImage) {
            grayscaleImage.draw();
        } else {
            originalImage.draw();
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (event.getKeyCode() == KeyPressedEvent.VK_SPACE) {
            drawGrayscaleImage = !drawGrayscaleImage;
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
