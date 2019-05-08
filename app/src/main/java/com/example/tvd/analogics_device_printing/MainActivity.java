package com.example.tvd.analogics_device_printing;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.analogics.printerApi.PrinterApi;
import com.analogics.printerApi.Printer_3inch_ThermalAPI;
import com.example.tvd.analogics_device_printing.values.FunctionCall;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {
    Button print, img_print;
    FunctionCall fcall;
    Typeface typeface_DroidSansMono;
    Canvas canvas = new Canvas();
    protected String btAddressDir = Environment.getExternalStorageDirectory() + "";
    String address = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        print = findViewById(R.id.btn_print);
        img_print = findViewById(R.id.btn_img_print);

        fcall = new FunctionCall();
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Printer_3inch_ThermalAPI printapi = new Printer_3inch_ThermalAPI();
                PrinterApi printer = new PrinterApi();
                boolean status = printer.isPrinterSupportable();
                if (status) {
                    String[] rf1 = new String[3];
                    rf1[0] = "0x1B";
                    rf1[1] = "0x4B";
                    rf1[2] = "0x64";
                    printer.setPrintCommand(rf1);
                    printer.setPrintCommand(printapi.font_Courier_28());
                    //printer.setPrintCommand(printapi.font_Double_Height_Width_On());
                    printer.printData(fcall.aligncenter("HUBLI ELECTRICITY SUPPLY\n", 25));
                    printer.printData(fcall.aligncenter("COMPANY LTD\n", 25));
                    //printer.setPrintCommand(printapi.font_Double_Height_Width_Off());

                    printer.setPrintCommand(printapi.font_Courier_28());
                    printer.printData(fcall.space(" SubDivision", 16) + ":" + "540038" + "\n");
                    printer.printData(fcall.space(" RRNo", 16) + ":" + "RR123F" + "\n");

                    printer.printData(fcall.space(" Account ID", 16) + ":" + "12345678" + "\n");
                    printer.setPrintCommand(printapi.font_Courier_28());
                    printer.printData(fcall.aligncenter(" Name and Address", 25) + "\n");
                    printer.printData(" " + "Mr. K Vijay" + "\n");
                    printer.printData(" " + "Bangalore, Belgum" + "\n");
                    printer.printData(fcall.space(" Tariff", 16) + ":" + "LT2A" + "\n");
                    printer.printData(fcall.space(" SancLoad", 10) + ":" + "HP" + ":" + "5" + fcall.space("", 5) + "KW" + ":" + "3" + "\n");

                    // printer.printData(fcall.space(" Billing Period", 5) + ":" + "01-07-2018" + "-" + "01-08-2018"+"\n");
                    printer.printData(fcall.space(" PrintDate", 16) + ":" + "17-08-2018" + "\n");
                    printer.printData(fcall.space(" BillNo", 16) + ":" + "12345678" + "\n");
                    printer.printData(fcall.space(" Meter SLNo", 16) + ":" + "12345678" + "\n");

                    printer.printData(fcall.space(" Pres Rdg", 16) + ":" + "1200" + "\n");
                    printer.printData(fcall.space(" Prev Rdg", 16) + ":" + "1000" + "\n");
                    printer.printData(fcall.space(" Constant", 16) + ":" + "2" + "\n");
                    printer.printData(fcall.space(" Consumption", 16) + ":" + "200" + "\n");
                    printer.printData(fcall.space(" Average", 16) + ":" + "100" + "\n");

                    printer.printData(fcall.aligncenter(" Fixed Charges", 25) + "\n");

                    printer.printData(fcall.alignright("50", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("500.00", 10) + "\n");

                    printer.printData(fcall.aligncenter(" Energy Charges", 25) + "\n");
                    printer.printData(fcall.alignright("20", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("200.00", 10) + "\n");
                    printer.printData(fcall.alignright("30", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("300.00", 10) + "\n");

                    printer.printData(fcall.space(" Rebates/TOD", 15) + ":" + " " + fcall.alignright("10.20", 10) + "\n");
                    printer.printData(fcall.space(" PF Penalty", 15) + ":" + " " + fcall.alignright("200.00", 10) + "\n");
                    printer.printData(fcall.space(" MD Penalty", 15) + ":" + " " + fcall.alignright("100.00", 10) + "\n");
                    printer.printData(fcall.space(" Interest", 12) + "@1%" + ":" + fcall.alignright("100.00", 11) + "\n");
                    printer.printData(fcall.space(" Others", 15) + ":" + " " + fcall.alignright("50.00", 10) + "\n");
                    printer.printData(fcall.space(" Tax", 12) + "@6%" + ":" + " " + fcall.alignright("50.00", 10) + "\n");
                    printer.printData(fcall.space(" Curr Bill Amt", 14) + ":" + " " + fcall.alignright("1200.00", 11) + "\n");
                    printer.printData(fcall.space(" Arrears", 14) + ":" + fcall.alignright("100.00", 12) + "\n");
                    printer.printData(fcall.space(" Credits & Adj", 14) + ":" + fcall.alignright("50.00", 12) + "\n");
                    printer.printData(fcall.space(" GOK Subsidy", 14) + ":" + fcall.alignright("50.00", 12) + "\n");

                    printer.printData(fcall.space(" Net Amt Due", 14) + ":" + fcall.alignright("50.00", 12) + "\n");

                    printer.printData(fcall.space(" Due Date", 14) + ":" + fcall.alignright("17-08-2018", 10) + "\n");
                    printer.printData(fcall.space(" Billed On", 9) + ":" + fcall.alignright(fcall.currentDateandTime(), 16) + "\n");


                    printer.setPrintCommand(printapi.barcode_Code_128_Alpha_Numerics("12345678"));
                    printer.printData("12345678");
                    printer.printData("" + "\n");
                    printer.printData("" + "\n");
                    printer.printData("" + "\n");
                    printer.printData("" + "\n");

                } else {
                    Toast.makeText(MainActivity.this, "Printer is Not available!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //todo unicode printing code

        img_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Printer_3inch_ThermalAPI printapi = new Printer_3inch_ThermalAPI();
                PrinterApi printer = new PrinterApi();
                int textsize = 22, rightspace = 14, details_right = 13;

                fcall.multiLinguallinePrint2(left_space("", 15) + "ಹುಬ್ಬಳ್ಳಿ ವಿದ್ಯುತ್ ಸರಬರಾಜು ಕಂಪನಿ ನಿಯಮಿತ", 27, Typeface.defaultFromStyle(Typeface.BOLD));
                fcall.multiLinguallinePrint2(left_space("", 22) + " GST.No.   29AABCH3176JEZZ", 24, Typeface.defaultFromStyle(Typeface.BOLD));
                fcall.multiLinguallinePrint2(left_space("", 5) + line(42), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(empty(20) + "540038", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 5) + "ಉಪ ವಿಭಾಗ/Sub Division" + " " + empty(3) + ": " + rightAppend("540038", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 5) + "ಆರ್.ಆರ್.ಸಂಖ್ಯೆ/RRNO" + " " + empty(9) + ": " + rightAppend("RR123F", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 7) + "ಖಾತೆ ಸಂಖ್ಯೆ/Account ID" + " " + empty(6) + ": " + rightAppend("1234567890", details_right), 28, Typeface.defaultFromStyle(Typeface.BOLD));
                fcall.multiLinguallinePrint2(left_space("", 5) + line(42), textsize, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(fcall.aligncenter("ಹೆಸರು ಮತ್ತು ವಿಳಾಸ/Name and Address", 40), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "Bangalore, Belgum", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಜಕಾತಿ/Tariff" + " " + empty(10) + ": " + rightAppend("LT2A", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಮಂ.ಪ್ರಮಾಣ/Sanct Load" + empty(2) + ": " + "HP:" + rightAppend("5", 6) + " KW:" + "3", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "Billing Period" + " " + empty(4) + ": " + "01-02-2019" + "-" + "01-03-2019", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ರೀಡಿಂಗ ದಿನಾಂಕ/Reading Date" + " " + empty(1) + ": " + rightAppend("01-02-2019", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಬಿಲ್ ಸಂಖ್ಯೆ/Bill No" + " " + empty(2) + ": " + rightAppend(("1234567890" + "02" + "01"), (details_right + 7)), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಮೀಟರ್ ಸಂಖ್ಯೆ/Meter SlNo" + " " + empty(3) + ": " + rightAppend("9876543456", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಇಂದಿನ ಮಾಪನ/Pres Rdg" + " " + empty(5) + ": " + rightAppend("120", 9) + "  " + "NOR", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಹಿಂದಿನ ಮಾಪನ/Prev Rdg" + " " + empty(5) + ": " + rightAppend("0", 9), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಮಾಪನ ಸ್ಥಿರಾಂಕ/Constant" + " " + empty(5) + ": " + rightAppend("10", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಬಳಕೆ/Consumption" + " " + empty(9) + ": " + rightAppend("120", details_right), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಸರಾಸರಿ/Average" + " " + empty(9) + ": " + rightAppend("10", details_right), textsize, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(left_space("", 4) + "ನಿಗದಿತ ಶುಲ್ಕ/Fixed Charges (Unit,Rate,Amount)", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(rightAppend("1.50", 10) + rightAppend("x", 3) + rightAppend("50", 8) + rightAppend("=", 6) + rightAppend("100.0", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(rightAppend("2.50", 10) + rightAppend("x", 3) + rightAppend("60", 8) + rightAppend("=", 6) + rightAppend("150.0", rightspace), textsize, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(left_space("", 5) + "ವಿದ್ಯುತ್ ಶುಲ್ಕ/Energy Charges(Unit,Rate,Amount)", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(rightAppend("1.50", 10) + rightAppend("x", 3) + rightAppend("50", 8) + rightAppend("=", 6) + rightAppend("100.0", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(rightAppend("2.50", 10) + rightAppend("x", 3) + rightAppend("60", 8) + rightAppend("=", 6) + rightAppend("150.0", rightspace), textsize, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(left_space("", 4) + "ಎಫ್.ಎ.ಸಿ/FAC" + " : " + rightAppend(String.valueOf("30"), 8) + " x" + fcall.alignright("0.04", 6) + fcall.alignright("100.0", (rightspace - 2)), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ರಿಯಾಯಿತಿ/Rebates/TOD" + empty(2) + "(-) : " + rightAppend("100.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಪಿ.ಎಫ್ ದಂಡ/PF Penalty" + " " + empty(4) + ": " + rightAppend("150.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಎಂ.ಡಿ.ದಂಡ/MD Penalty" + " " + empty(6) + ": " + rightAppend("120.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಇತರೆ/Others" + " " + empty(13) + ": " + rightAppend("50.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ತೆರಿಗೆ/Tax @9%" + " " + empty(12) + ": " + rightAppend("90.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಬಿಲ್ ಮೊತ್ತ/Cur Bill Amt" + " " + empty(2) + ": " + rightAppend("500.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಬಾಕಿ/Arrears" + " " + empty(12) + ": " + rightAppend("100.00", rightspace), textsize, Typeface.MONOSPACE);


                fcall.multiLinguallinePrint2(left_space("", 4) + "ಸರ್ಕಾರದ ಸಹಾಯಧನ/GOK" + empty(2) + "(-) : " + rightAppend("50.00", rightspace), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + line(42), textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 7) + "Net Amt Due" + " " + empty(12) + ": " + rightAppend(getResources().getString(R.string.rupee) + " " + "600.0", 27), 30, Typeface.defaultFromStyle(Typeface.BOLD));
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಪಾವತಿ ಕೊನೆ ದಿನಾಂಕ/Due Date" + " : " + "01-02-2019", textsize, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + "ಬಿಲ್ ದಿನಾಂಕ/Billed On" + empty(4) + ": " + fcall.currentDateandTime(), textsize, Typeface.MONOSPACE);

                boolean status = printer.isPrinterSupportable();

                if (status) {
                    printer.setPrintCommand(printapi.barcode_Code_128_Alpha_Numerics("1234567890"));
                    printer.printData("1234567890");

                }

                /*  new PrinterApi().multiLinguallinePrint(left_space("HUBLI ELECTRICITY SUPPLY", 35), 28, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint(left_space("COMPANY LTD",25), 28, Typeface.DEFAULT_BOLD);


                new PrinterApi().multiLinguallinePrint(fcall.aligncenter("HUBLI ELECTRICITY SUPPLY", 55), 28, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint(fcall.aligncenter("COMPANY LTD", 76), 28, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಉಪ ವಿಭಾಗ" + ":" + left_space("540038", 10), 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಆರ್.ಆರ್.ಸಂಖ್ಯೆ" + ":" + left_space("RR123F", 10), 28, Typeface.MONOSPACE);

                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಖಾತೆ ಸಂಖ್ಯೆ" + ":" + left_space("", 5) + "12345678", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಹೆಸರು ಮತ್ತು ವಿಳಾಸ", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 12) + "Mr. K Vijay", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 7) + "Bangalore, Belgum", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಜಕಾತಿ" + ":" + left_space("", 5) + "LT2A", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಮಂ.ಪ್ರಮಾಣ" + ":" + left_space("", 5) + "HP" + " " + "5" + "KW" + " " + "3", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + " ್ರಿಂಟ್ ಡೇಟ್" + ":" + left_space("", 2) + "17-08-2018", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಬಿಲ್ ಸಂಖ್ಯೆ" + ":" + left_space("", 5) + "123", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಮೀಟರ್ ಸಂಖ್ಯೆ" + ":" + left_space("", 5) + "123", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಇಂದಿನ ಮಾಪನ" + ":" + left_space("", 5) + "123", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಹಿಂದಿನ ಮಾಪನ" + ":" + left_space("", 5) + "23", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಮಾಪನ ಸ್ಥಿರಾಂಕ" + ":" + left_space("", 5) + "22", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಬಳಕೆ" + ":" + left_space("", 5) + "100", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಸರಾಸರಿ" + ":" + left_space("", 5) + "50", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ದಾಖಲಿತ ಬೇಡಿಕೆ" + ":" + left_space("", 5) + "5", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಪವರ ಫ್ಯಾಕ್ಟರ" + ":" + left_space("", 5) + "30", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ನಿಗದಿತ ಶುಲ್ಕ" + ":" + left_space("", 5) + "30", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ರಿಯಾಯಿತಿ" + ":" + left_space("", 5) + "10.00", 28, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint(left_space("", 5) + "ಪಿ.ಎಫ್ ದಂಡ" + ":" + left_space("", 5) + "205.00", 28, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(fcall.aligncenter("HUBLI ELECTRICITY SUPPLY", 63), 28, Typeface.DEFAULT_BOLD);
                fcall.multiLinguallinePrint2(fcall.aligncenter("COMPANY LTD", 69), 28, Typeface.DEFAULT_BOLD);
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಉಪ ವಿಭಾಗ",15) + fcall.aligncenter(":",2) + fcall.alignright("540038", 15), 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.aligncenter("ಹೆಸರು ಮತ್ತು ವಿಳಾಸ",38), 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 12) + "Mr. K Vijay", 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 10) + "Bangalore, Belgum", 28, Typeface.MONOSPACE);

                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಜಕಾತಿ",15) + fcall.aligncenter(":",2) + fcall.alignright("LT2A", 15)  , 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಮಂ.ಪ್ರಮಾಣ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "HP" + " " + "5" + " " + "KW" + " " + "3", 25, Typeface.MONOSPACE);
                //fcall.multiLinguallinePrint2(left_space("", 4) + " ್ರಿಂಟ್ ಡೇಟ್" + ":" + left_space("", 5) + "17-08-2018", 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಬಿಲ್ನೋ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "12345678", 28, Typeface.MONOSPACE);//Bill No
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಮೀಟರ್ ಎಸ್ಎಲ್ನೋ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "12345678", 28, Typeface.MONOSPACE);//Metre SL no
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಪ್ರಸ್ತುತ ಓದುವಿಕೆ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "200", 28, Typeface.MONOSPACE);//Presenting Reading
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಹಿಂದಿನ ಓದುವಿಕೆ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "100", 28, Typeface.MONOSPACE);//Previous Reading
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಹನಿರಂತರೆ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "2", 28, Typeface.MONOSPACE);//constant
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಬಳಕೆ",15) + fcall.aligncenter(":",2)+ left_space("", 2) + "100", 28, Typeface.MONOSPACE);//consumption
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಸರಾಸರಿ",15) + fcall.aligncenter(":",2) + left_space("", 2) + "50", 28, Typeface.MONOSPACE);//Average
                fcall.multiLinguallinePrint2(fcall.aligncenter("ಸ್ಥಿರ ಶುಲ್ಕಗಳು", 40), 28, Typeface.MONOSPACE);//Fixed charges
                fcall.multiLinguallinePrint2(fcall.alignright("50", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("500.00", 18), 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(fcall.aligncenter("ಶಕ್ತಿ ಶುಲ್ಕಗಳು", 40), 28, Typeface.MONOSPACE);//Fixed charges
                fcall.multiLinguallinePrint2(fcall.alignright("20", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("200.00", 18), 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(fcall.alignright("30", 5) + " " + "x" + fcall.alignright("10", 10) + fcall.alignright("300.00", 18), 28, Typeface.MONOSPACE);
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ರಿಯಾಯಿತಿಗಳು / TOD",15) + fcall.aligncenter(":",2)  + fcall.alignright("50.00", 13), 28, Typeface.MONOSPACE);//Rebates/TOD
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಪಿಎಫ್ ಪೆನಾಲ್ಟಿ",15) + fcall.aligncenter(":",2)  + fcall.alignright("50.00", 16), 28, Typeface.MONOSPACE);//PF Penalty
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("MD ಪೆನಾಲ್ಟಿ",15) + fcall.aligncenter(":",2)  + fcall.alignright("50.00", 17), 28, Typeface.MONOSPACE);//MD Penalty
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಆಸಕ್ತಿ",15) + fcall.aligncenter(":",2)  + fcall.alignright("50.00", 18), 28, Typeface.MONOSPACE);//Interest
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಇತರರು",15) + fcall.aligncenter(":",2)  + fcall.alignright("40.00", 16), 28, Typeface.MONOSPACE);//Others
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ತೆರಿಗೆ",15) + fcall.aligncenter(":",2)  + fcall.alignright("40.00", 18), 28, Typeface.MONOSPACE);//Tax
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಕರ್ರ್ ಬಿಲ್ ಆಮ್ಟ್",15) + fcall.aligncenter(":",2)  + fcall.alignright("40.00", 14), 28, Typeface.MONOSPACE);//Current Bill amt
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಬಾಕಿ",15) + fcall.aligncenter(":",2)  + fcall.alignright("40.00", 16), 28, Typeface.MONOSPACE);//Arrears
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಶ್ರೀಯ ಮತ್ತು ಆಡ್ಜೆ",15) + fcall.aligncenter(":",2) + fcall.alignright("40.00", 17), 28, Typeface.MONOSPACE);//Credit&Adj
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("GOK ಸಬ್ಸಿಡಿ",15) + fcall.aligncenter(":",2) + fcall.alignright("40.00", 17), 28, Typeface.MONOSPACE);//GOK subsidy
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ನೆಟ್ ಅಮಟ್ ಕಾರಣ",15) + fcall.aligncenter(":",2)  + fcall.alignright("40.00", 8), 28, Typeface.MONOSPACE);//Net Amt due
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ಕಾರಣ ದಿನಾಂಕ",15) + fcall.aligncenter(":",2)  + fcall.alignright("17-08-2018", 2), 28, Typeface.MONOSPACE);//Due date
                fcall.multiLinguallinePrint2(left_space("", 4) + fcall.leftalign("ರಂದು ಬಿಲ್ ಮಾಡಲಾಗಿದೆ",15) + fcall.aligncenter(":",1)  + fcall.alignright(fcall.currentDateandTime(), 1), 28, Typeface.MONOSPACE);//Billed On*/
            }
        });


     /*   img_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //fcall.multiLinguallinePrint2(space("", 4) + "ಜಕಾತಿ/Tariff" + rightspacing("LT2A", 10)  , 20, Typeface.MONOSPACE);

            }
        });*/
    }

    public String left_space(String str, int n) {
        return String.format("%1$" + n + "s", str);
    }

    private String rightspacing(String s1, int len) {
        int i;
        StringBuilder s1Builder = new StringBuilder(s1);
        for (i = 0; i < len - s1Builder.length(); i++) {
        }
        s1Builder.insert(0, String.format("%" + i + "s", ""));
        s1 = s1Builder.toString();
        return (s1);
    }

    private String space(String s, int length) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = length - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.insert(0, String.format("%" + i + "s", ""));
        }
        return (s + spaces);
    }

    public String empty(int length) {
        StringBuilder sb5 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb5.append(" ");
        }
        return (sb5.toString());
    }

    //Dotted line
    public String line(int length) {
        StringBuilder sb5 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb5.append("-");
        }
        return (sb5.toString());
    }

    public String rightAppend(String str, int maxlen) {
        StringBuilder retStr = new StringBuilder();
        if (str.length() < maxlen) {
            for (int i = 0; i < maxlen - str.length(); i++) {
                retStr.append(" ");
            }
            retStr.append(str);
        }
        return retStr.toString();

    }

    public String barcode_Code_128_Alpha_Numerics_VIP(String data) {
        int len = data.length() + 1;
        System.out.println(len);
        String strHexNumber = Integer.toHexString(len);
        byte[] var10000 = new byte[]{Byte.valueOf(String.valueOf(len))};
        System.out.println(strHexNumber);
        byte[] rf1 = new byte[]{27, 90, 50, Byte.parseByte(strHexNumber, 16), 80, Byte.parseByte("C", 16)};
        String s = new String(rf1) + data;
        System.out.println("sdfsdfsd::" + s);
        return s;
    }
}
