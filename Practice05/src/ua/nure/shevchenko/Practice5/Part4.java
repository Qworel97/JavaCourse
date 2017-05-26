package ua.nure.shevchenko.Practice5;

import java.util.Random;

public class Part4 {
	
	public static void main(String[] args) throws InterruptedException {
		int[][] matrix = new int[4][100];
		for(int i = 0; i<4;i++){
			for(int j =0;j<100;j++){
				matrix[i][j]=randInt(0,500);
			}
		}
		Comparationer comp = new Comparationer(matrix);
		System.out.println(comp.compare());
		int max = matrix[0][0];
		long temp = System.currentTimeMillis();
		for(int i=0;i<4;i++){
			for(int j=0;j<100;j++){
				if (matrix[i][j]>max){					
					max=matrix[i][j];
					Thread.sleep(1);
				}
			}
		}
		System.out.println(System.currentTimeMillis()-temp);
	}
	public static int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
}
