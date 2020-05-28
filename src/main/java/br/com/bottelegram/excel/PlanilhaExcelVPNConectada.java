package br.com.bottelegram.excel;

//https://www.devmedia.com.br/apache-poi-manipulando-documentos-em-java/31778
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.bottelegram.comando.dto.VPNConectadaExcel;

public class PlanilhaExcelVPNConectada {
	private static final String ACCEPT = "Accept";

	public static void main(String[] args) {
		carregarPlanilhaVPN();
	}
	private static final String ARQ_EXCEL = "Logs_Mar_30__2020_8_35_52_AM.xlsx";

	public static List<VPNConectadaExcel> carregarPlanilhaVPN() {
		List<VPNConectadaExcel> listaexcel = new ArrayList<>();
		try (FileInputStream arquivo = new FileInputStream(new File(ARQ_EXCEL));
				Workbook obj = new XSSFWorkbook(arquivo);) {

			Sheet worksheet = obj.getSheet("logs");
			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				VPNConectadaExcel vpnExcel = new VPNConectadaExcel();
				listaexcel.add(vpnExcel);
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					try {
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setTime(cell.getStringCellValue());
							}
							break;
						case 1:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setBlade(cell.getStringCellValue());
							}
							break;
						case 2:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setSeverity(cell.getStringCellValue());
							}
							break;
						case 3:
							if (cell.getStringCellValue() != null
									&& ACCEPT.equalsIgnoreCase(cell.getStringCellValue())) {
								vpnExcel.setAction(cell.getStringCellValue());
							} else {
								continue;
							}
							break;
						case 4:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setOrigin(cell.getStringCellValue());
							}
							break;
						case 5:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setSource(cell.getStringCellValue());
							}
							break;
						case 6:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setUser(cell.getStringCellValue());
								if (cell.getStringCellValue().contains("(")
										&& cell.getStringCellValue().contains(")")) {
									String mat[] = cell.getStringCellValue().split("\\(");
//									System.out.println("mat[0]:" + mat[0]);
									if (mat.length >= 1) {
//										System.out.println("mat[1]:" + mat[1].replace(")", ""));
										vpnExcel.setMatricula(mat[1].replace(")", ""));
									}
								}
							} else {
								continue;
							}
							break;
						case 7:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setDestination(cell.getStringCellValue());
							}
							break;
						case 8:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setService(cell.getStringCellValue());
							}
							break;
						case 9:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setServico(cell.getStringCellValue());
							}
							break;
						case 10:
							if (cell.getStringCellValue() != null) {
								vpnExcel.setId(cell.getStringCellValue());
							}
							break;
						}
					} catch (Exception e) {
						System.out.println("Célula com problemas.");
					}
//					System.out.println("Célula da planilha:" + vpnExcel);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaexcel;
	}

	private boolean validarVersaoExcel(File file) throws FileNotFoundException {
		String name = file.toString();
		int pos = name.lastIndexOf('.');
		String ext = name.substring(pos + 1);
		if (ext.equals("xlsx")) {
			return true;
		}
		return false;
	}

}
