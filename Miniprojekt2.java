package Panlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import zuoye3.intToBin;

/*
 * Wichtig: Die Methodensignatur darf nicht veraendert werden.
 */

public class Miniprojekt2 {
	public Miniprojekt2() {
	}

	// Aufgabe 1
	/*
	 * from 5-12 the number of hours should be 120
	 * 12 - 21 => 200
	 * 21 - 5 => 150
	 */
	public static int sollTeile(int Stunde) {
		// Bitte ergaenzen!
		int res = 0;
		if (Stunde < 12 && Stunde >= 5) res = 150;
		else if (Stunde < 21 && Stunde >= 12) res = 200;
		else if ((Stunde <= 23 && Stunde >= 21) || (Stunde >= 0 && Stunde < 5)) res = 120;
		else {
			System.out.println("please input a number that between 0 and 23");
			System.exit(-1);
		}
		return res;
	}
	
	// Aufgabe 2
	/*
	 * calculate begin, middle, and end. Then divide them by the corresponding hours
	 */
	public static double[] mittlereStdProduktionSchicht(int[][] stundenproduktion, int tag) {
		// Bitte ergaenzen!
		/*if (tag < 1 || tag > 8) {
			System.out.println("please input a right day");
			System.exit(-1);
		}*/
		double[] res = new double[3];
		double bin = 0;
		for (int i=5; i<12; i++) {
			bin += stundenproduktion[tag][i];
		}
		double mit = 0;
		for (int i=12; i<21; i++) {
			mit += stundenproduktion[tag][i];
		}
		double end = stundenproduktion[tag][21] + stundenproduktion[tag][22] + 
				stundenproduktion[tag][23] + stundenproduktion[tag+1][0] + stundenproduktion[tag+1][1] + stundenproduktion[tag+1][2] + 
				stundenproduktion[tag+1][3] + stundenproduktion[tag+1][4];
		//System.out.println(bin + " " + mit + " " + end);
		res[0] = bin / 7;
		res[1] = mit / 9;
		res[2] = end / 8;
		return res;
	
	}
	
	// Aufgabe 3
	/*
	 * 
	 */
	public static int[] tagesproduktion(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		int[] res = new int[10];
		for (int i=0; i<stundenproduktion.length; i++) {
			for (int j=0; j<stundenproduktion[i].length; j++) {
				res[i] += stundenproduktion[i][j];
			}
		}
		return res;
	
	}
	
	// Aufgabe 4
	public static int[] istSollVergleich(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		int[] res = new int[10];
		for (int i=0; i<stundenproduktion.length; i++) {
			for (int j=0; j<stundenproduktion[i].length; j++) {
				res[i] += stundenproduktion[i][j];
			}
			res[i] -= 3810;
		}
		return res;
	
	}
	
	// Aufgabe 5
	public static int minMittlereStdProd(int[][] stundenproduktion, int tag) {
		// Bitte ergaenzen!
		double[] res = mittlereStdProduktionSchicht(stundenproduktion, tag);
		if (res[0] <= res[1] && res[0] <= res[2]) return 0;
		else if (res[1] <= res[0] && res[1] <= res[2]) return 1;
		else if (res[2] <= res[0] && res[2] <= res[1]) return 2;
	
		return 3;
	}
	
	// Aufgabe 6
	public int maxMittlereStdProd(int[][] stundenproduktion, int tag) {
		// Bitte ergaenzen!
		double[] res = mittlereStdProduktionSchicht(stundenproduktion, tag);
		if (res[0] >= res[1] && res[0] >= res[2]) return 0;
		else if (res[1] >= res[0] && res[1] >= res[2]) return 1;
		else if (res[2] >= res[0] && res[2] >= res[1]) return 2;
	
		return 3;
	
	
	
	}
	
	// Aufgabe 7
	/*
	 * call sichtCal to calculate all the SchichtProd
	 * then we will have 3 * 8 data
	 * i is the day 
	 * j is the Schicht
	 * k is the Production
	 */
	public static int[] minSchichtProd(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		ArrayList<Integer> days = sichtCal(stundenproduktion);
		int tmp = Integer.MAX_VALUE;
		for (int i : days) {
			if (i < tmp) tmp = i;
		}
		int tmp2 = days.indexOf(tmp);
		int i = tmp2 / 3 + 1;
		int j = tmp2 % 3;
		int k = tmp;
		int[] res = new int[]{i, j, k};
		
		return res;
	}
	
	// Aufgabe 8
	/*
	 * similar to the Aufgabe 7
	 * 
	 */
	public static int[] maxSchichtProd(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		ArrayList<Integer> days = sichtCal(stundenproduktion);
		int tmp = Integer.MIN_VALUE;
		for (int i : days) {
			if (i > tmp) tmp = i;
		}
		int tmp2 = days.indexOf(tmp);
		int i = tmp2 / 3 + 1;
		int j = tmp2 % 3;
		int k = tmp;
		int[] res = new int[]{i, j, k};
		
		return res;
	}
	// Aufgabe 9
	/*
	 * very easy one 
	 * get all the productions in that day 
	 * sort it 
	 * tmp2 is used to store the Array without duplications 
	 * put tmp2 into int[]
	 */
	
	public static int[] differentStdProd(int[][] stundenproduktion, int tag) {
	
		// Bitte ergaenzen!
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		ArrayList<Integer> tmp2 = new ArrayList<Integer>();
		for (int i=0; i<stundenproduktion[tag].length; i++) {
			tmp.add(stundenproduktion[tag][i]);
		}
		Collections.sort(tmp);
		tmp2.add(tmp.get(0));
		for (int i=1; i<tmp.size(); i++) {
			if (tmp.get(i) > tmp.get(i-1)) tmp2.add(tmp.get(i));
		}
		//System.out.println(tmp.get(0) + " " + tmp.get(1));
		//System.out.println(tmp2);
		int[] res = new int[tmp2.size()];
		for (int i=0; i<res.length; i++) {
			res[i] = tmp2.get(i);
		}
		
		
		return res;
	}
	
	// Aufgabe 10
	/*
	 * very easy one 
	 * travel the list if the value > max move max to second 
	 */
	public static int secondStundeProd(int[][] stundenproduktion, int std) {
		// Bitte ergaenzen!
		int max = Integer.MIN_VALUE, sed = Integer.MIN_VALUE;
		for (int i=0; i<stundenproduktion.length; i++) {
			int tmp = stundenproduktion[i][std];
			if (tmp > max) {
				sed = max;
				max = tmp;
			} else if (tmp < max && tmp > sed) sed = tmp;
			
		}
	
		return sed;
	}
	
	// Aufgabe 11
	/*
	 * very complex 
	 * call check() to check if this hour in this day is a "Margin" time
	 * if true, put in res
	 * the tmp will store the largest value at last
	 */
	public static int[] maxProfitMargin(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		ArrayList<int[]> res = new ArrayList<int[]>();
		int[] tmp = null;
		for (int i=8; i>=1; i--) {
			for (int j=23; j>=0; j--) {
				tmp=check(stundenproduktion, i, j);
				if (null != tmp) res.add(tmp);
			}
		}
		//System.out.println(res);
		tmp = null;
		int tmp2 = Integer.MIN_VALUE;
		for (int[] t : res) {
			if (t[2] > tmp2) {
				tmp2 = t[2];
				tmp = t;
			}
		}
		return tmp;
	}
	
	// Aufgabe 12
	/*
	 * easy one 
	 * call sollTeil to find the production that should be produced
	 * travel the list find the largest difference 
	 */
	public static int[] maxLostSchicht(int[][] stundenproduktion) {
		// Bitte ergaenzen!
		int[] res = new int[3];
		int tmp = Integer.MAX_VALUE;
		for (int i=9; i>=0; i--) {
			for (int j=23; j>=0; j--) {
				if (stundenproduktion[i][j] - sollTeile(j) < tmp) {
					res[0] = i;
					res[1] = j;
					tmp = stundenproduktion[i][j] - sollTeile(j);
					res[2] = tmp;
				}
			}
		}
		return res;
	}
	
	public void ausgabeA(int[][] array) {
		if (array == null) {
			System.out.println("null");
		} else {
			for (int i = 0; i < array.length; i++) {
				System.out.println(Arrays.toString(array[i]));
			}
		}
	}
	
	public void ausgabeA(int[] array) {
		if (array == null) {
			System.out.println("null");
		} else {
			for (int i = 0; i < array.length; i++)
				System.out.println("[" + array[i] + "]");
		}
	}
	
	public void ausgabeA(double[] array) {
		if (array == null) {
			System.out.println("null");
		} else {
			for (int i = 0; i < array.length; i++)
				System.out.println("[" + array[i] + "]");
		}
	}
	
	/*
	 * used to calculate the time in different Schichts 
	 */
	public static ArrayList<Integer> sichtCal(int[][] stundenproduktion) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int bin = 0;
		int mit = 0;
		int end = 0;
		for (int i=1; i<=8; i++) {
			for (int j=21; j<=23; j++) {
				bin += stundenproduktion[i-1][j];
			}
			for (int j=0; j<5; j++) {
				bin += stundenproduktion[i][j];
			}
			for (int j=5; j<12; j++) {
				mit += stundenproduktion[i][j];
			}
			for (int j=12; j<21; j++) {
				end += stundenproduktion[i][j];
			}
			res.add(bin);
			res.add(mit);
			res.add(end);
			bin = 0;
			mit = 0;
			end = 0;
		}
		System.out.println(res);
		return res;
	}
	
	/*
	 * edge cases make it very complex
	 * day can only be in 1 - 8 that is ok
	 * but the hour can be 0 or 23 that is not ok
	 * one case for hour = 0
	 * one case for hour = 23
	 * last for 0 < hour < 23
	 */
	public static int[] check(int[][] stundenproduktion, int d, int h) {
		int[] res = new int[3];
		if (0 != h && 23 != h) {
			if (stundenproduktion[d][h] < stundenproduktion[d][h-1] || 
				stundenproduktion[d][h] < stundenproduktion[d][h+1]) return null;
			for (int i=h-1; i<=h+1; i++) {
				if (stundenproduktion[d][h] < stundenproduktion[d-1][i] ||
					stundenproduktion[d][h] < stundenproduktion[d+1][i]) return null;
			}
			res[0] = d;
			res[1] = h;
			res[2] = stundenproduktion[d][h];
			return res;
		}
		if (0 == h) {
			int tmp = stundenproduktion[d][h];
			if (tmp > stundenproduktion[d-1][h] && tmp > stundenproduktion[d+1][h] && 
				tmp > stundenproduktion[d-1][h+1] && tmp > stundenproduktion[d][h+1] && 
				tmp > stundenproduktion[d+1][h+1]) {
					res[0] = d;
					res[1] = h;
					res[2] = stundenproduktion[d][h];
					return res;
				}
		}
		if (23 == h) {
			int tmp = stundenproduktion[d][h];
			if (tmp > stundenproduktion[d-1][h] && tmp > stundenproduktion[d+1][h] && 
				tmp > stundenproduktion[d-1][h-1] && tmp > stundenproduktion[d][h-1] && 
				tmp > stundenproduktion[d+1][h-1]) {
					res[0] = d;
					res[1] = h;
					res[2] = stundenproduktion[d][h];
					return res;
				}
		}
		return null;
	}
	
	public static void main(String[] args) {
	
		Miniprojekt2 mini2 = new Miniprojekt2();
	
		int tag = 0;
	
		int[][] mittlereStundenproduktion = new int[][] {
				{ 0, 392, 533, 26, 635, 39, 693, 496, 690, 281, 903, 529, 121, 29, 345, 325, 195, 226, 290, 311, 369,
						344, 107, 201 },
				{ 473, 869, 449, 419, 235, 253, 47, 914, 580, 148, 806, 827, 50, 298, 406, 252, 205, 213, 47, 193, 140,
						278, 251, 7 },
				{ 240, 349, 543, 421, 168, 180, 421, 495, 157, 86, 169, 250, 349, 60, 247, 83, 499, 145, 272, 266, 15,
						610, 228, 647 },
				{ 241, 438, 99, 405, 723, 499, 320, 265, 240, 150, 927, 301, 146, 48, 400, 458, 18, 164, 63, 48, 239,
						25, 512, 7 },
				{ 686, 113, 495, 565, 845, 46, 929, 710, 38, 177, 873, 760, 197, 30, 124, 34, 58, 177, 164, 533, 394,
						360, 605, 634 },
				{ 874, 486, 220, 228, 451, 397, 126, 300, 161, 892, 738, 656, 168, 177, 28, 393, 51, 165, 42, 228, 363,
						96, 526, 319 },
				{ 445, 168, 315, 251, 283, 746, 565, 787, 437, 748, 826, 346, 42, 304, 321, 197, 101, 308, 182, 665,
						133, 673, 654, 657 },
				{ 44, 1, 673, 123, 895, 522, 30, 791, 535, 232, 116, 458, 450, 486, 362, 64, 320, 434, 15, 16, 681, 527,
						53, 623 },
				{ 218, 257, 605, 22, 140, 620, 694, 433, 866, 825, 168, 812, 261, 104, 186, 501, 256, 298, 121, 290,
						308, 468, 235, 224 },
				{ 866, 733, 922, 637, 328, 141, 462, 348, 645, 658, 402, 334, 210, 399, 368, 417, 119, 94, 284, 121,
						503, 554, 668, 535 } };
	
		int[][] stundenproduktion = new int[][] {
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 },
				{ 120, 120, 120, 120, 120, 150, 150, 150, 150, 150, 150, 150, 200, 200, 200, 200, 200, 200, 200, 200,
						200, 120, 120, 120 } };
						
						
						
		
		/*double[] res = mittlereStdProduktionSchicht( stundenproduktion, t);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}*/
		/*int[] res = istSollVergleich(stundenproduktion);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}*/
		/*int[] res = maxSchichtProd(stundenproduktion);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}*/
		//differentStdProd(stundenproduktion, 1);
		
		/*int[] res = maxProfitMargin(mittlereStundenproduktion);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}*/
		
		/*int[] res = check(mittlereStundenproduktion, 1, 1);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}
		*/
		/*int[] res = maxLostSchicht(mittlereStundenproduktion);
		for (int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}*/
//		System.out.println(secondStundeProd(mittlereStundenproduktion, 0));

		/*double[] stundenprod2 = mini2.mittlereStdProduktionSchicht(mittlereStundenproduktion, 5);
		System.out.println("Aufgabe 2) mittlereStdProduktionSchicht");
		mini2.ausgabeA(stundenprod2);*/

		
		
		
		
		
		
	//	System.out.println("Aufgabe 1) sollTeile(5)" + mini2.sollTeile(5));
	//	System.out.println("Aufgabe 1) sollTeile(12)" + mini2.sollTeile(12));
	//	System.out.println("Aufgabe 1) sollTeile(21)" + mini2.sollTeile(21));
	
		/*
		 * Erwartete Loesung: Aufgabe 1) sollTeile(5)150 Aufgabe 1)
		 * sollTeile(12)200 Aufgabe 1) sollTeile(21)120
		 */
	
	//	double[] stundenprod = mini2.mittlereStdProduktionSchicht(stundenproduktion, tag);
		// System.out.println("Aufgabe 2) mittlereStdProduktionSchicht");
		// mini2.ausgabeA(stundenprod);
		/*
		 * Erwartete Loesung: [150.0] [200.0] [120.0]
		 */
	//	double[] stundenprod2 = mini2.mittlereStdProduktionSchicht(mittlereStundenproduktion, 5);
	//	System.out.println("Aufgabe 2) mittlereStdProduktionSchicht");
	//	mini2.ausgabeA(stundenprod2);
		/*
		 * Erwartete Loesung: [467.14285714285717] [179.44444444444446]
		 * [300.375]
		 */
	
	
	
	
	}
}	
