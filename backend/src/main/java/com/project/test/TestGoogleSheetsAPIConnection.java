package com.project.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.project.config.GoogleSheetsConfig;


public class TestGoogleSheetsAPIConnection {
    private Sheets sheetService;

    private static final String SPREADSHEETS_ID = "1cDzBKp9340pd_o5fF51iH9OXdmrNREaex0PmTvVmCg4";
    private static final String RANGE = "Test1!A1:D10";

    public TestGoogleSheetsAPIConnection() {
        try {
            GoogleSheetsConfig config = new GoogleSheetsConfig();
            sheetService = config.googleSheetsConfig();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void readSheetData() {
        try {
            ValueRange response = sheetService.spreadsheets().values()
                .get(SPREADSHEETS_ID, RANGE)
                .execute();
            List<List<Object>> values = response.getValues();

            for (@SuppressWarnings("rawtypes") List row : values) 
                System.out.println("Data from Google Sheet" + row.toString());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
