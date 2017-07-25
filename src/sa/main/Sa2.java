package sa.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
// 起動処理

public class Sa2 {
	public static void main(String[] args) {
		Sa2 Sa2 = new Sa2();
		Sa2.main();
	}
	public Double T;
	public Double vec[]=new Double[150];
	//private Function<Double,Double> cost2;
	double cost2=0;
	double x[] = new double[150];
	double rnd[] = new double[150];
	double direction[] = new double[200];
	String path = "C://CSV//child01.csv"; // ファイルへの物理パス
	String[][] CSV1 = getCSV_All(path);
	int result[][]=new int[150][150];


	void main() {

		System.out.println("SAcreated");
		//デフォルトパラメータ
		T = 100000.;

		 Arrays.fill(vec, 0.);

	calc();
	get();

	int a=0;
	while(a<113){
		System.out.println(vec[a]);
		//解
	a++;
	}

	int l=0;
	//SA法全体の結果
	while(l<113){
	cost2 = cost2+(x[l]-result[0][l])*(x[l]-result[0][l]);
	l++;
	}
//誤差関数出力
	System.out.println(cost2);
	//System.out.println(result[7][3]);
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
		for (int i = 0; i < CSV1.length; i++) {
			for (int j = 0; j < CSV1[i].length; j++) {
				result[i][j]  =Integer.parseInt(CSV1[i][j]);
				//int型に変換
			}
		}
		//System.out.println(result[7][3]);

		double step = 1.;
		while (T > 0.01){
			int i=0;
			while (i < 113){
			rnd[i] = new Random().nextDouble();
			direction[i] = (rnd[i] - 0.5) * step;
			i++;
			}

			int j=0;
			while (j < 113){
				x[j]=(vec[j]+direction[j]);
			j++;
			}
			cost2=0;//初期化
			int l=0;
			while(l<113){
			cost2 = cost2+(x[l]-result[0][l])*(x[l]-result[0][l]);
			l++;
			}

			Double newCost =cost2;

			int k=0;
			while (k < 113){
				x[k]=vec[k];

			k++;
			}
			cost2=0;//初期化
			int n=0;
			while (n< 113){
			cost2 = cost2+(x[n]-result[0][n])*(x[n]-result[0][n]);
			n++;
			}
			Double oldCost =cost2;


			Double p = Math.pow(Math.E, -1.*Math.abs(newCost - oldCost)/T);//温度が上がるごとに確率は下がる
			//System.out.println(p);
			if( newCost < oldCost || new Random().nextDouble() < p ){
				int a=0;
				while(a<113){
				vec[a] = vec[a] + direction[a];
				a++;
			}
				}

			T = T * 0.9999999;
		}
		return vec;
	}
	public Double[] get(){
		return vec;
	}

}