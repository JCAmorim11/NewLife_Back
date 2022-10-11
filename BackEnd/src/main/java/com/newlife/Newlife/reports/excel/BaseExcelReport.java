package com.newlife.Newlife.reports.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseExcelReport {

   private HSSFWorkbook workbook;

    private CellStyle normalStyle;

    private CellStyle okStyle;

    private CellStyle notOkStyle;


    private CellStyle idStyle;
    private CellStyle headerCenterStyle;
    private CellStyle headerStyle;

    private CellStyle rotatedHeaderStyle;

    public static Integer FIRST_AVAILABLE_ROW = 7;

    public BaseExcelReport(String reportName, String document, String s) throws Exception{
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/template_excel.xls");
        workbook = new HSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        sheet.getRow(0).getCell(1).setCellValue(reportName);
        sheet.getRow(1).getCell(1).setCellValue("Documento: "+ document);

        sheet.getRow(0).getCell(7).setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));


    }

    private Cell createCell(int cellIndex, Row row, CellStyle style) {
        Cell cell = row.createCell(cellIndex);
        if(style != null) cell.setCellStyle(style);

        return cell;
    }

    public Cell createHeaderCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, headerStyle);
    }

    public Cell createHeaderCenterCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, headerCenterStyle);
    }

    public Cell createRotatedCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, rotatedHeaderStyle);
    }

    public Cell createNormalCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, normalStyle);
    }

    public Cell createOkCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, okStyle);
    }

    public Cell createNotOkCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, notOkStyle);
    }

    public Cell createIdCell(int cellIndex, Row row) {
        return createCell(cellIndex, row, idStyle);
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    private void makeBorder(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
    }
}
