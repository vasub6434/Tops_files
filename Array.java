package com.fundamental;

import java.util.Scanner;

public class Array {
	public static void main(String[] args) {
		int a[]=new int [5];
		int i,max=a[0];
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter array elements");
		for(i=0;i<a.length;i++)
		{
			System.out.println("Enter"+(i+1)+"Element :");
			a[i]=sc.nextInt();
		}
		System.out.println("Array elements are");
		for (i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
		
       
		for(i=1;i<a.length;i++)
		{
			if(a[i]>max)
			{
				max=a[i];
			}
		}
		System.out.println(max);
	}

}
