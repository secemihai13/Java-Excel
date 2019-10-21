package ExcelProject.ExcelArtifact;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ListIterator;

public class App {

	private static String[] headers = {"HHNR", "PNR", "FirstName", "LastName", "Birthday"};

	public static void main( String[] args ) throws IOException {
		try {
			File excelFile = new File("input.xlsx");
			FileInputStream file = new FileInputStream(excelFile);
			XSSFWorkbook workBook = new XSSFWorkbook(file);
			XSSFSheet page = workBook.getSheetAt(0);
			Iterator<Row> rowIt = page.iterator();

			ArrayList<ObiectRand> listOfObjects = new ArrayList<ObiectRand>();

			//			Citirea si maparea datelor in structura de date.
			while(rowIt.hasNext()) {
				Row currentRow = rowIt.next();

				Cell cell1 = currentRow.getCell(0);
				Cell cell2 = currentRow.getCell(1);
				Cell cell3 = currentRow.getCell(2);
				Cell cell4 = currentRow.getCell(3);
				Cell cell5 = currentRow.getCell(4);
				listOfObjects.add(new ObiectRand(cell1.toString(), cell2.toString(), cell3.toString(), cell4.toString(), cell5.toString()));
			}

			workBook.close();
			file.close();

			ArrayList<LinkedHashSet<ObiectRand>> matrix = new ArrayList<LinkedHashSet<ObiectRand>>();
			matrix.add(new LinkedHashSet<ObiectRand>());

			int counter = 0;
			int columns = 1;
			int max = 0;
			for(int i = 0; i < listOfObjects.size() - 1; i++) {
				if(listOfObjects.get(i).getHhnr().equals(listOfObjects.get(i + 1).getHhnr())) {
					if(matrix.get(counter).add(listOfObjects.get(i)) == true) {
						columns++;
					}
					if(matrix.get(counter).add(listOfObjects.get(i + 1)) == true) {
						columns++;
					}
					if(columns > max) {
						max = columns;
					}
				} else {
					matrix.add(new LinkedHashSet<ObiectRand>());
					matrix.get(++counter).add(listOfObjects.get(i + 1));
					columns = 1;
				}
			}

			for(int i = 1; i <= max ; i++) {
				matrix.get(0).add(new ObiectRand("HHNR", "PNR" + i, "FirstName" + i, "LastName" + i, "Birthday" + i));
			}

			Iterator<ObiectRand> iter = matrix.get(0).iterator();
			System.out.print(iter.next().getHhnr() + " ");
			for(ObiectRand ob : matrix.get(0)) {
				System.out.print(ob.getPnr() + " " + ob.getFirstName() + " " + ob.getLastName() + " \t");
			} System.out.println();

			for(int i = 1; i < matrix.size(); i++) {
				Iterator<ObiectRand> it = matrix.get(i).iterator();
				System.out.println(it.next().getHhnr() + " " + matrix.get(i) + "\n");
			}
			
			
			//Deschidem Workbook:
			XSSFWorkbook wExportat = new XSSFWorkbook();
			XSSFSheet sheetExportat = wExportat.createSheet();

			//Primul rand:
			Row headerRow = sheetExportat.createRow(0);
			headerRow.createCell(0).setCellValue("HHNR");
			int index = 1;
			int ascendingNumber = 1;
			for(int j = 1; j < (headers.length - 1) * max; j++) {
				if(index == 5) {
					index = 1;
					ascendingNumber++;
				}
				Cell cell = headerRow.createCell(j);
				cell.setCellValue(headers[index] + ascendingNumber);
				index++;
			}

			
			
			for(int i = 1; i < matrix.size(); i++) {
				Row currentRow = sheetExportat.createRow(i);
				int counterCell = 1;
				Iterator<ObiectRand> it = matrix.get(i).iterator();
				currentRow.createCell(0).setCellValue(it.next().getHhnr());
				it = matrix.get(i).iterator();
				for(int j = 0; j < matrix.get(i).size(); j++) {
					ObiectRand currentObject = it.next();
					currentRow.createCell(counterCell++).setCellValue(currentObject.getPnr());
					currentRow.createCell(counterCell++).setCellValue(currentObject.getFirstName());
					currentRow.createCell(counterCell++).setCellValue(currentObject.getLastName());
					currentRow.createCell(counterCell++).setCellValue(currentObject.getBirthday());
				}
			}
			
			//Redimensionarea coloanelor:
			for(int i = 0; i < sheetExportat.getRow(0).getLastCellNum(); i++) {
				sheetExportat.autoSizeColumn(i);
			}

			FileOutputStream outPut = new FileOutputStream("output.xlsx");
			wExportat.write(outPut);
			outPut.close();
			wExportat.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
