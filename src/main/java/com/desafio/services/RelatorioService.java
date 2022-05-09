package com.desafio.services;

import com.desafio.model.Pedido;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;

@Service
public class RelatorioService {

    public void adicionaPedidoRelatorio(Pedido pedido) throws IOException {
        BigDecimal valorMinimo = new BigDecimal("500");
        if(pedido.getValorTotal() != null && (pedido.getValorTotal().compareTo(valorMinimo) > 0)) {
            File file = new File("relatorioPedido.xlsx");
            if (file.exists() && !file.isDirectory()) {
                atualizaPedidoRelatorio(file, pedido);
            } else {
                criarPedidoRelatorio(pedido);
            }
        }
    }

    private void atualizaPedidoRelatorio(File file, Pedido pedido) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("relatorioPedido.xlsx"));
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);

        Cell cellId = row.createCell(0);
        cellId.setCellValue(pedido.getId());

        Cell cellValorTotal = row.createCell(1);
        cellValorTotal.setCellValue(pedido.getValorTotal().toString());

        FileOutputStream outputStream = new FileOutputStream("relatorioPedido.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();


    }
    private void criarPedidoRelatorio(Pedido pedido) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Relatorio");

        /* Cabeçalho */
        Row rowHeader = sheet.createRow(0);

        Cell cellIdHeader = rowHeader.createCell(0);
        cellIdHeader.setCellValue("Id");

        Cell cellValorTotalHeader = rowHeader.createCell(1);
        cellValorTotalHeader.setCellValue("Valor total pedido");

        /* Primeiro produto */
        Row row = sheet.createRow(1);

        Cell cellId = row.createCell(0);
        cellId.setCellValue(pedido.getId());

        Cell cellValorTotal = row.createCell(1);
        cellValorTotal.setCellValue(pedido.getValorTotal().toString());

        FileOutputStream fileOut = new FileOutputStream("relatorioPedido.xlsx");
        wb.write(fileOut);
        fileOut.close();

        wb.close();
    }

}
