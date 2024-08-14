package com.fundamental;

import java.util.Scanner;

public class Arrayassceding {
	public static void main(String[] args) {
		int a[]=new int[5];
		int i,j,temp;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the elements :");
		
		for(i=0;i<5;i++)
		{
			System.out.println("Enter "+(i+1)+"Element :");
			a[i]=sc.nextInt();
		}
		for(i=0;i<5;i++)
		
		{
			for(j=i+1;j<5;j++)
			{
				if(a[i]>a[j])
				{
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
		for(i=0;i<5;i++)
		{
		System.out.println(a[i]+" ");
		}
	}

}
