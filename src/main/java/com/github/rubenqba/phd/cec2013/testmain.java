package com.github.rubenqba.phd.cec2013;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;


public class testmain {
    public static void main(String[] args) throws Exception{
		int i,j,k,n,m,func_num;
		double[] x ;
		double[] f;

		Locale.setDefault(Locale.US);

		File fpt = new File("src/main/resources/input_data/shift_data.txt");
		
		m=2;
		n=10;
		
		testfunc tf = new testfunc();
		if (fpt.exists()) {
			Scanner input = new Scanner(fpt);
			x = new double[n*m];
			for(i=0;i<n;i++){
				x[i]=input.nextDouble();
			}
			for(i=0;i<n;i++){
				System.out.println(x[i]);
			}
			input.close();
			
			for(i=1;i<m;i++){
				for(j=0;j<n;j++){
					x[i*n+j]=0.0;
					System.out.println(x[i*n+j]);
				}
			}
			
			f= new double[m];
			for(i=0;i<28;i++){
				func_num=i+1;
				for(k=0;k<1;k++){
					tf.test_func(x,f,n,m,func_num);
					for(j=0;j<m;j++){
						System.out.println("f"+func_num+"(x["+(j+1)+"])="+f[j]);
					}
				}
			}
		}		
		
	}
}


		