
import java.awt.image.BufferedImage;
import java.io.File;
public class CheckCode {

	public static void main(String[] args){
		new CheckCode().check();
	}
	//http://www.ems.com.cn/ems/rand
	//http://et.hnair.com/huet/servlet/com.travelsky.web.huet.user.GenImage?1408518188891
    public void check() {
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
        BufferedImage image = Tools.getImage("/home/june/checked2.png");
            compare(image);    
            System.out.println("");   
    }
    public static void compare(BufferedImage image){
        BufferedImage checkCode[] = Tools.getCheckCodes(image,4);
        int count = 0;
        for (int t = 0; t < 4; t++) {
            boolean ckFlg = false;
            boolean flag = false;
            int num=-1;
            BufferedImage testImage = null;
            for (int i = 0; i < 10; i++) {
                num=-1;
                ckFlg = true;
                flag = false;
                count = 0;
                testImage = Tools.getImage("/home/june/CheckCode/test1/"+ i+".png");
                	if(testImage==null){
                    continue;
                }
                for (int y = 0; y < checkCode[t].getHeight() && !flag; y++) {
                    for (int x = 0; x < checkCode[t].getWidth(); x++) {               	
                        int expRGB = Tools.pixelConvert(checkCode[t].getRGB(x, y));
                        int cmpRGB = 0;
                        try{
                        	cmpRGB = Tools.pixelConvert(testImage.getRGB(x, y));
                        }catch(Exception e){
                        //	System.out.println(e.getMessage());
                        }
                        if (expRGB != cmpRGB) {
							count++;
							if (count >= (checkCode[t].getWidth() * checkCode[t].getHeight() * 0.1)) {
								flag = true;
								ckFlg = false;
								break;
							}
						}
					}
                }
                if (ckFlg) {
                    num=i;
                    System.out.println(num);
                    break;
                }
            }
            if (ckFlg) {
            	System.out.println("第"+(t+1)+"个数字是："+num);            
            }
        }
    }
}
