package cqut.syntaxAnalyzer.ll_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cqut.syntaxAnalyzer.ll_1.entity.FirstAndFollow;
import cqut.syntaxAnalyzer.ll_1.entity.PredictionTable;

/**
 * 生成预测分析表和first集。follow集
 * 
 * @author JiaoJian
 * 
 * @date 2010-11-16
 */
public class ProducePredictionTable {
	List<PredictionTable> pridictionTable=new ArrayList<PredictionTable>();
	Map<String,FirstAndFollow> firstAndFollows=new HashMap<String, FirstAndFollow>();//存放所有的first集和follow集

	public void first(String starting) {
		//根据产生式的开始符号，分析得到该非终结符的first集
		
	}

	public void follow(String starting) {
		//根据提供的非终结符，分析得出该非终结符的follow集

	}

	/**
	 * 根据first集和follow集建立预测分析表
	 * 
	 * @return
	 */
	public List<PredictionTable> generate() {
		return null;
	}
}
