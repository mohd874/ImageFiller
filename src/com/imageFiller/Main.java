package com.imageFiller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static BufferedImage loadImage(String ref) {
		BufferedImage bimg = null;
		try {

			bimg = ImageIO.read(new File(ref));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bimg;
	}

	public static int[] calculateNewDimentions(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		int newW = 2;
		int newH = 2;
		
		while(newW <= w){
			newW *= newW;
		}
		
		while(newH <= h){
			newH *= newH;
		}
		
		System.out.println("new dimintions: width: "+newW+" height: "+newH);
		
		int [] res = {newW, newH};
		return res;
	}
	
	public static void saveImage(BufferedImage img, String ref) {  
	    try {  
	        String format = (ref.endsWith(".png")) ? "png" : "jpg";  
	        ImageIO.write(img, format, new File(ref));  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}  
	
	public static void main(String[] args) {
		String srcImageStr = args[0];
		String trgImageStr = args[1];
		
		BufferedImage srcImg = loadImage(srcImageStr);
		int [] newDims = calculateNewDimentions(srcImg);
		
		BufferedImage trgImg = new BufferedImage(newDims[0], newDims[1], srcImg.getType());
		Graphics g = trgImg.getGraphics();
		g.drawImage(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(),
							0, 0, srcImg.getWidth(), srcImg.getHeight(), null);
		
		saveImage(trgImg, trgImageStr);
	}
	
	
}
