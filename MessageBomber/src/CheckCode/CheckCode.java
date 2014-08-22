package CheckCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.http.conn.params.ConnConnectionParamBean;

import android.R;
import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
public class CheckCode {
	
	static DownloadImage di = new DownloadImage();
	Bitmap bitmap = null;
	String codeUrl = null;
	int codeNumber = 0;
	String codeDir = null;
	public CheckCode(String codeUrl, int codeNumber, String codeDir){
		this.codeUrl = codeUrl;
		this.codeNumber = codeNumber;
		this.codeDir = codeDir;
	}
	
    public void check() {
    	Runnable runnable = new Runnable() {		
			public void run() {
				bitmap = di.downFiletoDecive(codeUrl);
				System.out.println("运行过");
			}
		};
		new Thread(runnable).start();
		SystemClock.sleep(1000);
		Filter.blackAndWhiteFilter(bitmap);
		Filter.dotFilter(bitmap);
		compare(bitmap,codeNumber,codeDir);				
    }
    public static void compare(Bitmap image, int codeNumber,String codeDir){
         Bitmap checkCode[] = Tools.getCheckCodes(image,codeNumber);
        int count = 0;
        for (int t = 0; t < codeNumber; t++) {
            boolean ckFlg = false;
            boolean flag = false;
            int num=-1;
            Bitmap testImage = null;
            for (int i = 0; i < 10; i++) {
                num=-1;
                ckFlg = true;
                flag = false;
                count = 0;
                testImage = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ic_);
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
