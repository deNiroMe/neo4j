package com.example.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CsvReader {

	private static final String csvFile = "wiki4HE.csv";
	
	public static void readCsv() {

        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
                for(String str: country) {
                	System.out.println(str);
                }
                
                break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	
	
	public static void appendToCsvFile() {
		
		try {
			List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\LENOVO\\Documents\\EGDownloads\\CiaoDVD(5)\\trusts.txt"));
			lines.stream().forEach( l -> {
				String contentToAppend = l + ",TRUSTS\r\n";
			    try {
			    
					Files.write(
					  Paths.get("C:\\Users\\LENOVO\\Documents\\EGDownloads\\CiaoDVD(5)\\copy\\trusts.txt"), 
					  contentToAppend.getBytes(), 
					  StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
