package com.sims.advent.of.code.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SignalsAndNoise {
	
	private static enum STRATEGY{
		MAX,
		MIN;
	}

	public static void main(String[] args) {
		try {
			List<String> signals = Files.readAllLines(Paths
					.get("resources/day6/input.txt"));
			Map<Integer, Map<Character, Integer>> frequencyMap = new HashMap<Integer, Map<Character, Integer>>();
			for (String signal : signals) {
				int frequencyIndex = 0;
				for (int i = 0; i < signal.length(); i++) {
					Map<Character, Integer> charMap = null;

					if (frequencyMap.containsKey(frequencyIndex)) {
						charMap = frequencyMap.get(frequencyIndex);
						Integer freqValue = charMap.get(signal.charAt(i));
						Integer frequency = (freqValue == null) ? 1
								: freqValue + 1;
						charMap.put(signal.charAt(i), frequency);
					} else {
						charMap = new HashMap<>();
						charMap.put(signal.charAt(i), 1);
						frequencyMap.put(frequencyIndex, charMap);
					}

					frequencyIndex++;
				}
			}

			System.out.println("Part 1 message = " + decodeMessage(STRATEGY.MAX, frequencyMap));
			System.out.println("Part 2 message = " + decodeMessage(STRATEGY.MIN, frequencyMap));
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String decodeMessage(STRATEGY strategy, Map<Integer, Map<Character, Integer>> frequencyMap ){
		StringBuilder sb = new StringBuilder();
		
		for (Entry<Integer, Map<Character, Integer>> entry : frequencyMap
				.entrySet()) {
			Collection<Integer> vals = entry.getValue().values();
			int maxValueInMap = (strategy.compareTo(STRATEGY.MAX) == 0) ? Collections.max(vals) : Collections.min(vals) ;
			for (Entry<Character, Integer> e : entry.getValue().entrySet()) {
				if (e.getValue() == maxValueInMap) {
					sb.append(e.getKey());
				}
			}
		}
		
		return sb.toString();
		
	}

}
