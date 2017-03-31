package com.flowfact.bufimg;

import java.util.List;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageWriter {

	private WritableImage img;
	
	public ImageWriter(WritableImage img) {
		this.img = img;
	}
	
	public WritableImage writeImage(List<String> teile) {
		
		PixelWriter writer = img.getPixelWriter();

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				String c = "";
				c = x + "-" + y;
				if (teile.contains(c)) {
					writer.setColor(x, y, Color.BLACK);
					
				}
			}
		}
		return img;
		
	}
	
	
	
	
}
