package com.imageFiller.cli;

import java.awt.image.BufferedImage;

import com.imageFiller.ImageFiller;

public class Main {

	public static void main(String[] args) {
		String imgRef = args[0];
		String newRef = args[1];
		
		ImageFiller filler = new ImageFiller();
		BufferedImage img = filler.loadImage(imgRef);
		
		System.out.println("creating new filled image...");
		filler.createFilledImage(img, newRef);
		System.out.println("done!");
	}
}
