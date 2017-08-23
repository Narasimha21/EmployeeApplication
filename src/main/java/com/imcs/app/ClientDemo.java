package com.imcs.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.imcs.project.dao.BonusDao;
import com.imcs.project.dao.BonusDaoInterface;
import com.imcs.project.entities.Bonus;
import com.imcs.project.service.EmployeeBonusImpl;

public class ClientDemo {
	static Logger logger = Logger.getLogger(ClientDemo.class.getName());
	private static void load() {
		File file = new File("D:\\MyFile.txt");
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			BonusDaoInterface bi = new BonusDao();
			String currentLine;
			int index = 0;
			while ((currentLine = br.readLine()) != null) {
				if (currentLine == null || currentLine.equals("")) {
					break;
				}
				if (index != 0) {
					String[] s = currentLine.split(" ");
					Bonus b = new Bonus(Integer.parseInt(s[0]), Float.parseFloat(s[1]), Float.parseFloat(s[1]));
					bi.addBonus(b);
				}
				index++;

			}
			logger.info("Reading Bonus File ");
			bi.saveBonus();
		} catch (NumberFormatException e) {
			logger.error("while Reading Bonus File ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("while Reading Bonus File ",e);// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		load();

		EmployeeBonusImpl employeeBonusInterface = new EmployeeBonusImpl();
		employeeBonusInterface.allocate();
	}

}
