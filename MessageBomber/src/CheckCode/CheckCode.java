package CheckCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.http.conn.params.ConnConnectionParamBean;

import android.content.Context;
import android.graphics.*;
import android.os.Environment;
public class CheckCode {
	
	static DownloadImage di = new DownloadImage();
	Bitmap bitmap = null;
    public void check() {
		new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				di.downFiletoDecive("http://www.chinagwy.net/Common/DadomImage.aspx", bitmap);
				System.out.println("运行过");
			}
		};
        Filter.blackAndWhiteFilter(bitmap);
        Filter.dotFilter(bitmap);
        compare(bitmap);      
    }
    public static void compare(Bitmap image){
         Bitmap checkCode[] = Tools.getCheckCodes(image,6);
        int count = 0;
        for (int t = 0; t < 6; t++) {
            boolean ckFlg = false;
            boolean flag = false;
            int num=-1;
            Bitmap testImage = null;
            for (int i = 0; i < 10; i++) {
                num=-1;
                ckFlg = true;
                flag = false;
                count = 0;
     //           testImage = di.readImage("/home/june/CheckCode/CheckCode/"+ i+".bmp");
                	if(testImage==null){
                    continue;
                }
                for (int y = 0; y < checkCode[t].getHeight() && !flag; y++) {
                    for (int x = 0; x < checkCode[t].getWidth(); x++) {               	
                        int expRGB = Tools.pixelConvert(checkCode[t].getPixel(x, y));
                        int cmpRGB = 0;
                        try{
                        	cmpRGB = Tools.pixelConvert(testImage.getPixel(x, y));
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
                    break;
                }
            }
            if (ckFlg) {
                System.out.println("第"+(t+1)+"个数字是："+num);
            }
        }
    }
}
