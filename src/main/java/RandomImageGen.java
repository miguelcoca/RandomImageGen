import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

class RandomImageGen {

    private final int width;
    private final int height;
    private final BufferedImage img;
    private BufferedImage bmp;

    public RandomImageGen(int width, int height) {
        this.width = width;
        this.height = height;
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    private void createRandomData() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Random rnd = new Random();

                int randomPixel = rnd.nextInt(0xffffff);
                int a = randomPixel << 24;
                int r = randomPixel << 16;
                int g = randomPixel << 8 ;
                int b = randomPixel << 4 ;

                randomPixel = a | r | g | b;

                this.img.setRGB(x, y, randomPixel);
            }
        }
    }

    private void createDummyData(){
        new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB).createGraphics().
                drawImage(this.img,0,0,this.width,this.height,Color.WHITE,null);
    }

    public void writeImageFile(String filename, String fileFormat) {
        File saveToFile;
        BufferedImage imageBuff;

        String fullFilePath = getFullPath(filename, fileFormat);

        saveToFile = new File(fullFilePath);

        createRandomData();
        //createDummyData();

        try {
            if(fileFormat.equals("bmp")) {
                formatBMP();
                imageBuff = this.bmp;
            }else{
                    imageBuff = this.img;
            }

            ImageIO.write(imageBuff, fileFormat, saveToFile);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private String getFullPath(String filename, String format) {
        String currentDir = getCurrentDir();
        String pathSeparator = System.getProperty("os.name").toLowerCase().indexOf("win")>0?"\\":"/";
        return String.format("%s%s%s.%s",currentDir,pathSeparator,filename,format);
    }

    private void formatBMP() {
        this.bmp = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_RGB);
        bmp.createGraphics().drawImage(img, 0, 0, null);
    }

    private String getCurrentDir() {
        Path currentRelativePath = Paths.get("");
        return (currentRelativePath.toAbsolutePath().toString());
    }
}
