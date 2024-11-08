package com.project.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.project.config.GoogleSheetsConfig;

@Service
public class GoogleSheetsService {
    private static final String SPEADSHEET_ID = "1cDzBKp9340pd_o5fF51iH9OXdmrNREaex0PmTvVmCg4";
    private static final String  RANGE = "Test1!A1:D2";

    private Sheets sheetService;

    @Autowired
    public GoogleSheetsService() {
        try {
            GoogleSheetsConfig config = new GoogleSheetsConfig();
            this.sheetService = config.googleSheetsConfig();
        }catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
    
    public List<List<Object>> readSheetData() throws IOException {
        try {
            ValueRange response = sheetService.spreadsheets().values()
                .get(SPEADSHEET_ID, RANGE)
                .execute();

                List<List<Object>> values = response.getValues(); 
                
                if (values == null || values.isEmpty()) {
                    System.out.println("Log : No data found");
                    return null;
                }
                return values;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeSheetData(List<List<Object>> data) throws IOException {
        ValueRange body = new ValueRange().setValues(data);
        try {
            sheetService.spreadsheets().values()
            .append(SPEADSHEET_ID, RANGE, body)
            .setValueInputOption("RAW")
            .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
