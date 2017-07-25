package sa.main;
import java.util.Random;
import java.util.function.Function;
// 起動処理

public class Sample01 {
	public static void main(String[] args) {
		Sample01 Sample01 = new Sample01();
		Sample01.main();
	}
	public Double T;
	public Double vec;
	private Function<Double,Double> cost2;
	void main() {
		int i=0;
		while(i<10){
			System.out.println("a");

		}

		System.out.println("SAcreated");
		/**
		 * default parameters
		 */
		T = 10000.;
		vec = 0.;
		//cost2 = x -> 4/8*((x-1)*(x-1)+(x-119)*(x-119)+(x-865)*(x-865)+(x-1727)*(x-1727)+(x-1746)*(x-1746)+(x-442)*(x-442)+(x-16)*(x-16)+(x-1)*(x-1));

		cost2 = x -> (x-1)*(x-1);

	calc();
	get();
	System.out.println(vec);

	}


	Double calc(){
		double step = 1.;
		while (T > 0.01){
			Double rnd = new Random().nextDouble();
			Double direction = (rnd - 0.5) * step;
			Double newCost = cost2.apply(vec+direction);
			Double oldCost = cost2.apply(vec);
			//温度から確率を定義する
			Double p = Math.pow(Math.E, -1.*Math.abs(newCost - oldCost)/T);
			if( newCost < oldCost || new Random().nextDouble() < p ){
				vec = vec + direction;
			}
			T = T * 0.999;
		}
		return vec;
	}
	public Double get(){
		return vec;
	}

}