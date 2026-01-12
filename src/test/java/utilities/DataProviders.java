package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider (name="LoginData")
	public static String[][] getData() throws IOException {

		String path = "C:\\Users\\Gopal\\Workspace_Custom\\OpenCart123\\testData\\Opecart_LoginData.xlsx";

		ExcelUtility xlutil = new ExcelUtility(path);

		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCol = xlutil.getCellCount("Sheet1", 1);

		String arr[][] = new String[totalRows][totalCol];

		for (int i = 1; i<=totalRows; i++) { //conditn is <= bcz, getLastRowNum() counts from 1

			for (int j = 0; j < totalCol; j++) { //conditn is < bcz, getLastCellNum() counts from 0

				arr[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}

		return arr;

	}
	

	}


