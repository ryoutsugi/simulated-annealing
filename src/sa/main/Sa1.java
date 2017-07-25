package sa.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
// 起動処理

public class Sa1 {
	public static void main(String[] args) {
		Sa1 Sa1 = new Sa1();
		Sa1.main();
	}
	public Double T;
	public Double vec[]={0., 0., 0., 0. ,0., 0., 0., 0.};
	private Function<Double, Function<Double, Function<Double,Function<Double,Function<Double,Function<Double,Function<Double,Function<Double, Double>>>>>>>> cost2;
	double rnd[] = new double[10];
	double direction[] = new double[10];
	void main() {

		System.out.println("SAcreated");
		String path = "C://CSV//child01.csv"; // ファイルへの物理パス

		String CSV1[][] = getCSV_All(path);
		for (int i = 0; i < CSV1.length; i++) {
		System.out.println(CSV1[7][3]);
		}

	// デフォルトパラメータ
		T = 10000000.;
		//vec = 0.;

		cost2 = (x1) -> (x2) -> (x3) -> (x4) -> (x5) -> (x6) -> (x7) -> (x8) -> (x1-1)*(x1-1)+(x2-119)*(x2-119)+(x3-865)*(x3-865)+(x4-1727)*(x4-1727)+(x5-1746)*(x5-1746)+(x6-442)*(x6-442)+(x7-16)*(x7-16)+(x8-1)*(x8-1) ;
		//System.out.println(T);
	calc();
	get();
	int n=0;
	while(n < 8){
	System.out.println(vec[n]);
	n++;
	}
	System.out.println(cost2.apply(vec[0]).apply(vec[1]).apply(vec[2]).apply(vec[3]).apply(vec[4]).apply(vec[5]).apply(vec[6]).apply(vec[7]));

	}
	String[][] getCSV_All(String myFILE) {
		// ファイルデータ：返り値用2次元配列
		String[][] charaDataArray = { { "カラ" } };
		// ファイルデータ：一時保存用リスト
		ArrayList<String[]> lists = new ArrayList<String[]>();

		try {
			// ファイルの読み込み準備
			File csv = new File(myFILE);
			BufferedReader br = new BufferedReader(new FileReader(csv));

			// データをリストに保存
			String line = null;
			while ((line = br.readLine()) != null) {
				// 読み込んだ文字列 line をカンマで分割して lists に格納
				lists.add(line.split(","));
			}
			br.close();

			// リストから2次元配列に移動
			charaDataArray = new String[lists.size()][];
			for (int i = 0; i < lists.size(); i++) {
				charaDataArray[i] = lists.get(i);
			}

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
			System.out.println("ファイルが見つかりませんでした");

		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
			System.out.println("IOエラー");
		}
		return charaDataArray;
	}

	Double[] calc(){
		double step = 1.;
		while (T > 0.01){
			int i=0;
			while (i < 9){
			rnd[i] = new Random().nextDouble();
			direction[i] = (rnd[i] - 0.5) * step;
			i++;
			}

			Double newCost = cost2.apply(vec[0]+direction[0]).apply(vec[1]+direction[1]).apply(vec[2]+direction[2]).apply(vec[3]+direction[3]).apply(vec[4]+direction[4]).apply(vec[5]+direction[5]).apply(vec[6]+direction[6]).apply(vec[7]+direction[7]);
			Double oldCost = cost2.apply(vec[0]).apply(vec[1]).apply(vec[2]).apply(vec[3]).apply(vec[4]).apply(vec[5]).apply(vec[6]).apply(vec[7]);
			//System.out.println(newCost+"a"+oldCost);
			//System.out.println(vec);
			//温度から確率を定義する
			System.out.println(newCost);
			System.out.println(oldCost);
			Double p = Math.pow(Math.E, -1.*Math.abs(newCost - oldCost)/T);
			//System.out.println(p);
			if( newCost < oldCost || new Random().nextDouble() < p ){
				int a=0;
				while(a<8){
				vec[a] = vec[a] + direction[a];
				a++;
			}
			}

		     //  try {
		            //出力先を作成する
		          //  FileWriter fw = new FileWriter("C://Users//ia13016//Documents//test.csv", true);
		            //PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
		            //BufferedWriter bw = new BufferedWriter(fw);
		            //pw.print(T);;
		            //pw.print(cost2.apply(vec[0]).apply(vec[1]).apply(vec[2]).apply(vec[3]).apply(vec[4]).apply(vec[5]).apply(vec[6]).apply(vec[7]));
		            //pw.println();
		            //ファイルに書き出す
		            //pw.close();
		            //終了メッセージを画面に出力する
		            //System.out.println("出力完了");

		        //} catch (IOException ex) {
		            //例外時処理
		          //  ex.printStackTrace();
		        //}

			T = T * 0.9999;
		}
		return vec;
	}
	public Double[] get(){
		return vec;
	}
}