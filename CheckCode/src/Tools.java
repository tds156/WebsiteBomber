
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Tools {
    public static void writeImageToFile(String imgFile, BufferedImage bi) {
    	//把图片写入文件
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(imgFile
            .substring(imgFile.lastIndexOf('.') + 1));
        ImageWriter writer = (ImageWriter) writers.next();
        File f = new File(imgFile);
        ImageOutputStream ios;
        try {
            ios = ImageIO.createImageOutputStream(f);
            writer.setOutput(ios);
            writer.write(bi);
            ios.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static int pixelConvert(int pixel) {
        int result = 0;
        //获取图像的 RGB 分量
        int r = (pixel >> 16) & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = (pixel) & 0xff;

        result = 0xff000000;	//黑色
        //黑色 或 白色
        int tmp = r * r + g * g + b * b;
        
        if (tmp > 3 * 128 * 128) {
      //  
            result += 0x00ffffff;	//白色
        }
        return result;
    }

    public static BufferedImage getImage(String path) {
        //根据路径读取图片
    	BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
        }
        return image;
    }

    public static BufferedImage getCuttedImages(BufferedImage image){
    	//弃用，原用于切割掉图片左右的空间
    	int x1=0,x2=0;
    	boolean foundedFirst = false;
    	boolean foundedLast = false;
    	BufferedImage cuttedImage;
    	for (int i = 0; i < image.getHeight() && !foundedFirst; i++) {
            for (int j = 0; j < image.getWidth(); j++) {
            	if(image.getRGB(j, i) == 0xff000000 ){
            		x1 = i;
            		foundedFirst = true;
            		break;
            	}
            }       
    	}
    	for (int i = (image.getHeight())-1; i >= 0 && !foundedLast; i--) {
    		for (int j = 0; j < image.getWidth(); j++) {
    			if(image.getRGB(j, i) == 0xff000000 ){
            		x2 = i;
            		foundedLast = true;
            		break;
            	}
            }
    	}
    	cuttedImage = image.getSubimage(0, x1, image.getWidth(), x2-x1);
    	writeImageToFile("/home/june/cutted.bmp", cuttedImage);
    	return cuttedImage;
    }
    
    public static int getStart(int widthPosition, BufferedImage image){
    	//找到一个字符的起点
    	for (int i = widthPosition; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
            	if(image.getRGB(i, j) == 0xff000000 ){
            		return i;
            	}
            }       
    	}	
    	return -1;
    }
    
    public static int getEnd(int widthPosition, BufferedImage image){
    	//找到字符的终点
    	int count = 0;
    	for (int i = widthPosition; i < image.getWidth() ; i++) {
    		count = 0;
            for (int j = 0; j < image.getHeight(); j++) {
            	if(image.getRGB(i, j) == 0xffffffff ){
            		count++;
            		if(count == image.getHeight()){
            			count = 0;
            			return i;
            		}
            	}
            }       
    	}
    	return -1;
    }
    
    public static void downloadImage(String urlAddress, String path) throws Exception{
    	//下载验证码图片下来
    	URL url = new URL(urlAddress);
		File outFile = new File(path);
		OutputStream os = new FileOutputStream(outFile);
		InputStream is = url.openStream();
		byte[] buff = new byte[1024];
		while(true) {
			int readed = is.read(buff);
			if(readed == -1) {
				break;
			}
			byte[] temp = new byte[readed];
			System.arraycopy(buff, 0, temp, 0, readed);
			os.write(temp);
		}
		is.close(); 
        os.close();
    }
    
    public static BufferedImage[] getCheckCodes(BufferedImage image, int x) {
        //开始切割
    	int numb = 0;
    	numb = x;
    	BufferedImage checkCode[] = new BufferedImage[numb];
        int array[] = new int[2*numb+1];
    	int height = image.getHeight();
        array[0] = 0;
        for(int i=0;i<numb;i++){
        	array[2*i+1] = Tools.getStart(array[2*i], image);
        	array[2*i+2] = Tools.getEnd(array[2*i+1], image);
        	checkCode[i] = image.getSubimage(array[2*i+1], 0, array[2*i+2]-array[2*i+1], height);
        }
        return checkCode;
    }
}
