/*
 * All Thanks to Josiah Hester for his tutorial <a href="http://www.javalobby.org/articles/ultimate-image/">Ultimate Java Image Manipulation</a>
 * 
 */

package com.imageFiller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFiller {

	public BufferedImage loadImage(String ref) {
		BufferedImage bimg = null;
		try {
			File file = new File(ref);
			bimg = ImageIO.read(file);
			
		} catch (Exception e) {
			System.out.println("---Error Occured");
			e.printStackTrace();
		}
		return bimg;
	}

	public double[] calculateRequiredWidthAndHeight(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		
		double reqW = 2; //required Width
		double reqH = 2; //required Height
		
		while (reqW < w){
			reqW = Math.pow(reqW, 2);
		}
		
		while (reqH < h){
			reqH = Math.pow(reqH, 2);
		}
		
		System.out.println("New Image Width and Height: "+reqW+" x "+reqH);
		
		double [] d = {reqW,reqH};
		return d;
	}

	public void createFilledImage(BufferedImage img, String ref) {
		double [] dims = calculateRequiredWidthAndHeight(img);
		BufferedImage newImg = new BufferedImage((int)dims[0], (int)dims[1], img.getType());
		
		Graphics2D g2d = (Graphics2D) newImg.getGraphics();
		g2d.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
		g2d.dispose();
		saveImage(newImg, ref);
	}
	
	/**
	 * Saves a BufferedImage to the given file, pathname must not have any
	 * periods "." in it except for the one before the format, i.e. C:/images/fooimage.png
	 * @param img
	 * @param saveFile
	 */
	public void saveImage(BufferedImage img, String ref) {
		try {
			String format = (ref.endsWith(".png")) ? "png" : "jpg";
			ImageIO.write(img, format, new File(ref));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
