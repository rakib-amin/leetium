package google;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;


public class TestSolutions {
	
   /**
    * Run this <code> 
    $("table.table.table-striped tr").each(function() {
     if($(this).find('i.fa.fa-unlock').length == 0) $(this).hide();
    }); 	
    </code> in browser's JS console for filtering out premium only problems 
    */
	
	static Set<Integer> probSet = new TreeSet<>();
	static Map<Integer, String> slugMap = new TreeMap<>();
	
	public static final String LEETCODE_PROBLEMS_FILE = "all_probs.json";
	public static final String LEETCODE_PROBLEM_URL = "https://leetcode.com/problems/%s/";
	
	public static void removeDuplicates(String inputFile, String outputFile) throws IOException {
		File input = new File(inputFile);
		Scanner sc = new Scanner(input);
		while(sc.hasNext()) {
			String line = sc.nextLine().strip();
			if(!line.isEmpty() && !line.contains("//")) {
				probSet.add(Integer.parseInt(line));
			}
		}
		/*
		FileWriter fileWriter = new FileWriter(outputFile);
		for(Integer s : probSet) {
			fileWriter.append(s.toString() + "\n");
		}
		fileWriter.close();
		*/
	} 
	
	public static void getSlugs() throws FileNotFoundException {
		InputStreamReader streamReader = new InputStreamReader(
				new FileInputStream(LEETCODE_PROBLEMS_FILE), 
				StandardCharsets.UTF_8);
		Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
        	Reader reader = new BufferedReader(streamReader);
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            reader.close();
        } catch (IOException io) {
            io.printStackTrace();
        } 
        JSONObject probsJSON = new JSONObject(writer.toString());
        JSONArray problems = probsJSON.getJSONArray("stat_status_pairs");
        
        for(int i = 0; i < problems.length(); i++) {
        	Integer id = problems.getJSONObject(i).getJSONObject("stat")
        			.getInt("frontend_question_id");
        	String slug = problems.getJSONObject(i).getJSONObject("stat")
        			.getString("question__title_slug");
        	
        	slugMap.put(id, slug);
        }
        
        
       
	}
	
	public static void createHTMLFile(String outputFile) throws IOException {
		
		FileWriter fileWriter = new FileWriter(outputFile);
		fileWriter.append("<html><style>\n" + 
				"table {\n" + 
				"  font-family: arial, sans-serif;\n" + 
				"  border-collapse: collapse;\n" + 
				"  width: 100%;\n" + 
				"}\n" + 
				"\n" + 
				"td, th {\n" + 
				"  border: 1px solid #dddddd;\n" + 
				"  text-align: left;\n" + 
				"  padding: 8px;\n" + 
				"}\n" + 
				"\n" + 
				"tr:nth-child(even) {\n" + 
				"  background-color: #dddddd;\n" + 
				"}\n" + 
				"</style>\n" + 
				"</head><body><table>");
		fileWriter.append("<tr><th>Problem Link</th></tr>");
		for(Map.Entry<Integer, String> e : slugMap.entrySet()) {
			fileWriter.append("<tr><td>");
			fileWriter.append("<a href='" + String.format(LEETCODE_PROBLEM_URL, e.getValue()) + "'>");
			fileWriter.append(e.getKey() + "</a>");
			fileWriter.append("</td></tr>");
		}
		fileWriter.append("</table></body></html>");
		fileWriter.close();
	}
	
	public static void main(String[] a) {
		
		try {
			removeDuplicates("leet_code_premium.txt", "premiums.txt");
			getSlugs();
			slugMap.keySet().retainAll(probSet);
			createHTMLFile("problems_to_do.html");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
