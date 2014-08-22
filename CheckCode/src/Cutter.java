
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cutter {
    public static void main(String[] args) {
    	try {
			Tools.downloadImage("http://et.hnair.com/huet/servlet/com.travelsky.web.huet.user.GenImage?1408518188891", "/home/june/check.ashx");
		} catch (Exception e) {
			System.out.println("download failed");
		}
    	BufferedImage image1 = Tools.getImage("/home/june/check.ashx");
        Filter.blackAndWhiteFilter(image1);
        Tools.writeImageToFile("/home/june/checked.png", image1);
        Filter.dotFilter(image1);
        Tools.writeImageToFile("/home/june/checked2.png", image1);
        
        BufferedImage image = null;
        BufferedImage checkCode[] = new BufferedImage[4];
        try {
            image = ImageIO.read(new File("/home/june/checked2.png"));
        } catch (IOException e) {
            System.out.println("can't open checkCode");
        }		
        checkCode=Tools.getCheckCodes(image,4);

        for (int i = 0; i < checkCode.length; i++) {
            try {
                ImageIO.write(checkCode[i], "PNG", new File("/home/june/CheckCode/" + (i+1)+".png"));
            } catch (IOException e) {
                System.out.println("can't open checkCode");
            }
        }
    }
}