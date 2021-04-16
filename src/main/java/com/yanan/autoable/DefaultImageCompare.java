package com.yanan.autoable;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import com.yanan.framework.plugin.annotations.Register;

@Register
public class DefaultImageCompare implements ImageCompare{

	@Override
	public double compare(byte[] image1, byte[] image2) {
		System.out.println("得到数据:"+image1.length);
		try {
			BufferedImage bufferedImg = ImageIO.read(new ByteArrayInputStream(image1));
			System.out.println("图形数据:"+bufferedImg);
			int width = bufferedImg.getWidth();
			int height = bufferedImg.getHeight();
			System.out.println(width+"x"+height);
			int[][] result = new int[width*height][];
			int[][] bixMap = new int[width][height];
 			Map<Integer,List<int[]>> types = new HashMap<>() ;
			int color;
			int count = 0;
			int[] items;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    color = bufferedImg.getRGB(i, j);
                    bixMap[i][j] = color;
                    items = new int[]{i,j,color};
                    result[count++] = items;
                    List<int[]> num = types.get(color);
                    if(num == null) {
                    	num = new ArrayList<>();
                    	types.put(color, num);
                    }
                    num.add(items);
                }
            }
            ImageOutput imageOutput = new ImageOutput();
            List<int[]> found = new ArrayList<>();
            boolean sim = false;
            		//当前rgb
            foundNext(bixMap,3,5,null,found,true);
//            	System.err.println();
            System.out.println(found.size());
            imageOutput.output(found.toArray(new int[][] {}));
//            types.forEach((key,value)->{
//            	Color colors = new Color(key);
//            	int[] rgb = {colors.getRed(),colors.getGreen(),colors.getBlue()};
////        		imageOutput.output(value.toArray(new int[][] {}));
////        		System.out.println(Arrays.toString(rgb)+"  "+value.size());
//            });
            //找出图形
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private int randomColor() {
		int r = Math.abs((int) (Math.random()*250));
		int g = Math.abs((int) (Math.random()*250));
		int b = Math.abs((int) (Math.random()*250));
		return new Color(r,g,b).getRGB();
	}
	private void foundNext(int[][] bixMap, int x, int y,int[] last, List<int[]> found,boolean line) {
		int colors = bixMap[x][y];
		int[] current = getRGB(colors);
		boolean jump = false;
		if(last != null) {
			if(compare(last,current)) {
				jump = true;
				if(!line) {
					if(x>0) {
						int[] temp = getRGB(bixMap[x-1][y]);
						if(!compare(last,temp )) {
							jump = false;
						}
					}
					y = y+1;
				}
			}
		}
		if(!jump) {
//			System.err.println(x+"-"+y+"-"+Arrays.toString(current));
			found.add(new int[] {x,y,colors});//Color.red.getRGB()
		}else {
			current = last;
		}
		if(x<bixMap.length-1 && line) {
			foundNext(bixMap,x+1,y,current,found,true);
		}
		if(y<bixMap[x].length-1) {
			foundNext(bixMap,x,y+1,current,found,false);
		}
//		if(x<bixMap.length-1 && y<bixMap[x].length-1) {
//			foundNext(bixMap,x+1,y+1,current,found);
//		}
	}
	private boolean compare(int[] last, int[] current) {
		int dR = last[0] - current[0];
		int dG = last[1] - current[1];
		int dB = last[2] - current[2];
		return Math.abs(dR)> 5 || Math.abs(dG)>5 || Math.abs(dB)>5;
	}
	private int[] getRGB(int color) {
		Color colors = new Color(color);
		return new int[]{colors.getRed(),colors.getGreen(),colors.getBlue()};
	}

}
