package cube;

import java.io.FileNotFoundException;

import br.com.magna.entities.Simulation;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

		new Thread(Simulation.getInstance()).start();

//
//		Scanner scan = new Scanner(System.in);
//
//		System.out.print("Coordenadas 'O' (x y z) >");
//		double ox = scan.nextDouble();
//		double oy = scan.nextDouble();
//		double oz = scan.nextDouble();
//
//		System.out.print("Coordenadas 'X' (x y z) >");
//		double xx = scan.nextDouble();
//		double xy = scan.nextDouble();
//		double xz = scan.nextDouble();
//
//		double x1minusx2 = ox-xx;
//		double y1minusy2 = oy-xy;
//		double z1minusz2 = oz-xz;
//
//		double xpow2 = Math.pow(x1minusx2, 2);
//		double ypow2 = Math.pow(y1minusy2, 2);
//		double zpow2 = Math.pow(z1minusz2, 2);
//
//		double xplusyplusz = xpow2 + ypow2 + zpow2;
//
//		System.out.println("Dox = (oX,oY,oZ) - (xX, xY, xZ)");
//		System.out.println("Dox = ("+ ox + " , " + oy + " , " + oz + ") - (" + xx + " , " + xy + " , " + xz + ")");
//		System.out.println("Dox = " + x1minusx2 + ", " + y1minusy2 + ", " + z1minusz2);
//		System.out.println("Dox = sqrt(" + (ox - xx) + "^2 " + "+ " + (oy - xy) + "^2 " +"+ "+ (oz - xz) + "^2)");
//		System.out.println("Dox = sqrt(" + String.format("%.2f", xpow2) + " + " + String.format("%.2f", ypow2) + " + " + String.format("%.2f",zpow2));
//		System.out.println("Dox = sqrt(" + String.format("%.2f", xplusyplusz) + ")");
//		System.out.println("Dox = " + String.format("%.3f", Math.sqrt(xplusyplusz)));
	}
}
