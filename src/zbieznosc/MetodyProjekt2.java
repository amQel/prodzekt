package zbieznosc;

import java.util.Scanner;
import Jakobian.Jakobian;


public class MetodyProjekt2 {
	
	
	public static double[] FodX(double x, double y) {
		double a,b;
		
		a = 2*x*y -3;
		b = x*x - y - 2;
		
		
		double[] macierz={a,b};
		return macierz;
	}
	
	
	public static double[] matX(double x, double y){
		double a,b;
		a = x;
		b = y;
		double[] macierz = {a,b};
		return macierz;
	}
	
	public static double[] GodX(double startX, double startY){
	
		//[x0, y0] - [f'(x0,y0)]^-1 * f(x0,y0)

		Jakobian fun = new Jakobian();
		fun.zrob(startX, startY);
		
		
		double wyznacznik = (fun.getA() * fun.getD() - fun.getB() * fun.getC()); 
		double[][] mDopelnien = new double[2][2];
		double[] g = new double[2];
		if(wyznacznik!=0){
			wyznacznik=1/wyznacznik;
			
			mDopelnien[0][0] = fun.getD() * wyznacznik;//a
			mDopelnien[1][0] = -1 * fun.getB() * wyznacznik;//b
			mDopelnien[0][1] = -1 * fun.getC() * wyznacznik;//c
			mDopelnien[1][1] = fun.getA() * wyznacznik;//d
//		
//			System.out.println(mDopelnien[0][0]+" " +  mDopelnien[1][0] + " " +  mDopelnien[0][1] + " " + mDopelnien[1][1]);
//			new java.util.Scanner(System.in).nextLine();
//		
		g[0] = matX(startX,startY)[0] - ((mDopelnien[0][0] * FodX(startX, startY)[0]) + (mDopelnien[1][0]* FodX(startX, startY)[1]));
		g[1] = matX(startX,startY)[1] - ((mDopelnien[0][1] * FodX(startX, startY)[0]) + (mDopelnien[1][1]* FodX(startX, startY)[1]));
//		
//		System.out.println( FodX(startX, startY)[0] + " " +  FodX(startX, startY)[1]);
//		new java.util.Scanner(System.in).nextLine();
		}
		return g;
		
	}
	
	public static double wczytX0()
	{
		Scanner in = new Scanner(System.in);
		double firstX=0;
		try{
			System.out.print( "x0 = ");
			firstX = in.nextDouble();
		}catch(Exception e){
			System.out.println("Podano bledna liczbe. Podaj jeszcze raz:\n");
			return wczytX0();
		}
		
		return firstX;
	}
	
	public static double wczytY0()
	{
		Scanner in = new Scanner(System.in);
		double firstY=0;
		try{
			System.out.print( "y0 = ");
			firstY = in.nextDouble();
		}catch(Exception e){
			System.out.println("Podano bledna liczbe. Podaj jeszcze raz:\n");
			return wczytY0();
		}
		
		return firstY;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] results = new double[1000][2];
		Scanner in = new Scanner(System.in);

			
		System.out.println( "Podaj początkowe wartości:");
		double firstX = wczytX0();
		double firstY = wczytY0();
		
		System.out.println( "Podaj ilość iteracji");
		int n = wczytN();
		System.out.println("Podaj dokladnosc przyblizenia p");
		double p = wczytP();
		
		results[0][0] = GodX(firstX,firstY)[0];
		results[0][1] = GodX(firstX,firstY)[1];
		
		
		
		
				for (int i=1;i<n;i++){
					results[i][0] = GodX(results[i-1][0], results[i-1][1])[0];
					results[i][1] = GodX(results[i-1][0], results[i-1][1])[1];
				}
				
				double pomX = 0.0;
				double pomY = 0.0;
				int i = 0;
		
				pomX = results[0][0];
				pomY = results[0][1];
				System.out.println( "x1 = " + pomX);
				System.out.println( "y1 = " + pomY);
				System.out.println( "\n" );
				
				double pomXp;
				double pomYp;
				double deltaX, deltaY;
				for (int j=1;j<n;j++){
					
					pomXp = results[j-1][0];
					pomYp = results[j-1][1];
					pomX = results[j][0];
					pomY = results[j][1];
				
				
				
				deltaX = pomX - pomXp;
				deltaX = Math.abs(deltaX);
				deltaY = pomY - pomYp;
				deltaY = Math.abs(deltaY);
						
				;System.out.println( "x" + (j+1) + " = " + pomX)
				;System.out.println( "y" + (j+1) + " = " + pomY)
				;System.out.println( "DeX"+ (j+1) + " = " + deltaX)
				;System.out.println( "DeY" + (j+1) + " = " + deltaY)
				;System.out.println( "\n")
				;
				if(deltaX < p || deltaY < p)
				{
					System.out.println( "Rozwiązania tego układu równań są zbieżne.")
					;break;
				}
				
		
			
			}
	}


	private static int wczytN() {
		Scanner in = new Scanner(System.in);
		int n=0;
		try{
			System.out.print( "LiczbaIteracji = ");
			n = in.nextInt();
			if(n<=0 || n>1000){
				System.out.println("liczba iteracji musi byc w przedziale (0;1000>");
				return wczytN();
			}
		}catch(Exception e){
			System.out.println("Nie podano liczby naturalnej. Podaj jeszcze raz:\n");
			return wczytN();
		}
		
		return n;
	}


	private static double wczytP() {
		Scanner in = new Scanner(System.in);
		double p=0;
		try{
			System.out.print( "p = ");
			p = in.nextDouble();
			if(p<=0 || p>=1){
				System.out.println("p musi byc w przedziale (0;1)");
				return wczytP();
			}
		}catch(Exception e){
			System.out.println("Nie podano liczby. Podaj jeszcze raz:\n");
			return wczytP();
		}
		
		return p;
		
	}

}