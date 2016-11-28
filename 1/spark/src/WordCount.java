import org.apache.spark.SparkConf;  
import org.apache.spark.api.java.JavaPairRDD;  
import org.apache.spark.api.java.JavaRDD;  
import org.apache.spark.api.java.JavaSparkContext;  
import org.apache.spark.api.java.function.FlatMapFunction;  
import org.apache.spark.api.java.function.Function2;  
import org.apache.spark.api.java.function.PairFunction;  
import org.apache.spark.api.java.function.VoidFunction;  
import scala.Tuple2;  
import java.util.Arrays;  
  
public class WordCount {  
    public static void main(String[] args) {  
  
        SparkConf conf = new SparkConf().setAppName("WordCount").setMaster("local");  
          
        JavaSparkContext sc = new JavaSparkContext(conf); //��ײ�ʵ���Ͼ���Scala��SparkContext  
          
        JavaRDD<String> lines = sc.textFile("C:\\TestFiles\\test.txt", 2);  
          
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {  
            @Override  
            public Iterable<String> call(String line) throws Exception {  
                return Arrays.asList(line.split(","));  
            }  
        });  
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {  
            @Override  
            public Tuple2<String, Integer> call(String word) throws Exception {  
                return new Tuple2<String, Integer>(word, 1);  
            }  
        });  
        JavaPairRDD<String, Integer> wordsCount = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {  
            //����ͬ��Key������Value���ۼƣ�����Local��Reducer����ͬʱReduce��  
            @Override  
            public Integer call(Integer v1, Integer v2) throws Exception {  
                return v1 + v2;  
            }  
        });  
          
        wordsCount.foreach(new VoidFunction<Tuple2<String, Integer>>() {  
            @Override  
            public void call(Tuple2<String, Integer> pairs) throws Exception {  
                System.out.println(pairs._1 + " : " + pairs._2);  
  
            }  
        });  
  
        sc.stop();  
    }  
}  