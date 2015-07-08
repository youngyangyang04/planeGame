package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

public class GameUtil {
	private GameUtil(){}//工具类通常将构造方法私有
	
	public static Image getImage(String path){
		java.net.URL u = GameUtil.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

}
