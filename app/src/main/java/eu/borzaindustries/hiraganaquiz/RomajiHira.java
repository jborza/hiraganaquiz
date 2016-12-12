package eu.borzaindustries.hiraganaquiz;

import java.util.LinkedHashMap;
import java.util.Random;

public class RomajiHira {
	public LinkedHashMap<String, String> dict;
	private Prefs prefs;

	public RomajiHira(Prefs prefs) {
		dict = new LinkedHashMap<String, String>();
		this.prefs = prefs;
		Fill();
	}

	public String lastHiragana;
	public String lastRomaji;

	public void generate() {
		String[] arr = dictKeysArray();
		int length = random.nextInt(2) + 2;
		StringBuilder h = new StringBuilder();
		StringBuilder r = new StringBuilder();

		// add first character
		for (int i = 0; i < length; i++) {
			String ro = randomCharacter(arr);
			String hi = dict.get(ro);
			if (doDouble() && canBeDoubled(ro) && i > 0) {
				if (ro.charAt(0) == 'n')
					hi = N + hi;
				else
					hi = TSU + hi;
				ro = ro.charAt(0) + ro;
			}
			h.append(hi);
			r.append(ro);
		}

		lastHiragana = h.toString();
		lastRomaji = r.toString();
	}

	private String[] dictKeysArray() {
		String[] arr = new String[size()];
		int i = 0;
		for (String key : dict.keySet()) {
			arr[i++] = key;
		}
		return arr;
	}

	private String randomCharacter(String[] arr) {
		int idx = random.nextInt(size());
		String s = arr[idx];
		return s;
	}

	private boolean doDouble() {
		return random.nextInt(6) == 0;
	}

//	class Pair {
//		public String r;
//		public String h;
//
//		public Pair(String r, String h) {
//			this.r = r;
//			this.h = h;
//		}
//	}
//
//	private Pair performDouble(String romaji) {
//		// if starts with n
//		if (romaji.charAt(0) == 'n') {
//			return new Pair(romaji.charAt(0) + romaji);
//		}
//	}

	private boolean canBeDoubled(String s) {
		if (s.length() == 1) // a,i,u,e,o, n
			return false;
		if (s.charAt(0) == 'w' || s.charAt(0) == 'y')
			return false;
		return true;
	}

	private int size() {
		return dict.size();
	}

	private String TSU = "っ";
	private String N = "ん";

	private void Fill() {
		if (prefs.a) {
			dict.put("a", "あ");
			dict.put("i", "い");
			dict.put("u", "う");
			dict.put("e", "え");
			dict.put("o", "お");
		}
		if (prefs.k) {
			// k
			dict.put("ka", "か");
			dict.put("ki", "き");
			dict.put("ku", "く");
			dict.put("ke", "け");
			dict.put("ko", "こ");
			// g
			dict.put("ga", "が");
			dict.put("gi", "ぎ");
			dict.put("gu", "ぐ");
			dict.put("ge", "げ");
			dict.put("go", "ご");
		}
		if (prefs.s) {// s
			dict.put("sa", "さ");
			dict.put("shi", "し");
			dict.put("su", "す");
			dict.put("se", "せ");
			dict.put("so", "そ");
			// z
			dict.put("za", "ざ");
			dict.put("ji", "じ");
			dict.put("zu", "ず");
			dict.put("ze", "ぜ");
			dict.put("zo", "ぞ");
		}
		if (prefs.t) {
			// t
			dict.put("ta", "た");
			dict.put("chi", "ち");
			dict.put("tsu", "つ");
			dict.put("te", "て");
			dict.put("to", "と");
			// d
			dict.put("da", "だ");
			// dict.put("ji", "");
			// dict.put("zu", "");
			dict.put("de", "で");
			dict.put("do", "ど");
		}
		if (prefs.n) {
			// n
			dict.put("na", "な");
			dict.put("ni", "に");
			dict.put("nu", "ぬ");
			dict.put("ne", "ね");
			dict.put("no", "の");
		}
		if (prefs.h) {
			// /// h
			dict.put("ha", "は");
			dict.put("hi", "ひ");
			dict.put("fu", "ふ");
			dict.put("he", "へ");
			dict.put("ho", "ほ");
			// b
			dict.put("ba", "ば");
			dict.put("bi", "び");
			dict.put("bu", "ぶ");
			dict.put("be", "べ");
			dict.put("bo", "ぼ");
			// p
			dict.put("pa", "ぱ");
			dict.put("pi", "ぴ");
			dict.put("pu", "ぷ");
			dict.put("pe", "ぺ");
			dict.put("po", "ぽ");
		}
		if (prefs.m) {
			// m
			dict.put("ma", "ま");
			dict.put("mi", "み");
			dict.put("mu", "む");
			dict.put("me", "め");
			dict.put("mo", "も");
		}
		if (prefs.r) {
			// r
			dict.put("ra", "ら");
			dict.put("ri", "り");
			dict.put("ru", "る");
			dict.put("re", "れ");
			dict.put("ro", "ろ");
		}

		if (prefs.y) {
			// y
			dict.put("ya", "や");
			dict.put("yu", "ゆ");
			dict.put("yo", "よ");

			// w
			dict.put("wa", "わ");
			dict.put("wo", "を");

			// n
			dict.put("n", "ん");
		}
		// ki, shi, chi, ni, hi, mi, ro + ya, yu, yo = kya, ...

		// gi, ji, bi, pi + ya, yu, yo

	}

	private Random random;

	public void setRandom(Random random) {
		this.random = random;
	}
}
