package com.example.tvd.analogics_device_printing;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.analogics.printerApi.PrinterApi;
import com.analogics.printerApi.Printer_3inch_ThermalAPI;
import com.example.tvd.analogics_device_printing.values.FunctionCall;

public class MainActivity extends AppCompatActivity {
    Button print, img_print;
    FunctionCall fcall;

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
                   // printer.printData("HUBLI ELECTRICITY SUPPLY"+ "\n"+ " COMPANY LTD"+ "\n");
                    printer.printData(fcall.aligncenter("HUBLI ELECTRICITY SUPPLY\n",25));
                    printer.printData(fcall.aligncenter("COMPANY LTD\n", 25));

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
                    printer.printData(fcall.space(" Meter SLNO", 16) + ":" + "12345678" + "\n");
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
                    printer.printData(""+"\n");
                    printer.printData(""+"\n");
                    printer.printData(""+"\n");
                    printer.printData(""+"\n");

                } else {
                    Toast.makeText(MainActivity.this, "Printer is Not available!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* new PrinterApi().multiLinguallinePrint("   ಉಪ ವಿಭಾಗ/Sub Division", 25, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint("   ಆರ್.ಆರ್.ಸಂಖ್ಯೆ/RRNO", 25, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint("   ಖಾತೆ ಸಂಖ್ಯೆ/Account ID", 25, Typeface.DEFAULT);
                new PrinterApi().multiLinguallinePrint("ಜಕಾತಿ/Tariff", 25, Typeface.SANS_SERIF);
                new PrinterApi().multiLinguallinePrint("ಮಂ.ಪ್ರಮಾಣ/Sanct Load", 25, Typeface.SERIF);

                new PrinterApi().multiLinguallinePrint("ಬಿಲ್ಲಿಂಗ್ ಅವಧಿ/Billing Period", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("ರೀಡಿಂಗ ದಿನಾಂಕ/Reading Date", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("ಬಿಲ್ ಸಂಖ್ಯೆ/Bill No", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("ಮೀಟರ್ ಸಂಖ್ಯೆ/Meter SlNo", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("ಇಂದಿನ ಮಾಪನ/Pres Rdg", 25, Typeface.MONOSPACE);*/

                new PrinterApi().multiLinguallinePrint("    HUBLI ELECTRICITY SUPPLY", 23, Typeface.DEFAULT_BOLD);
                new PrinterApi().multiLinguallinePrint("    COMPANY LTD", 23, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಉಪ ವಿಭಾಗ/Sub Division" + ":" + "     " + "540038", 20, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಆರ್.ಆರ್.ಸಂಖ್ಯೆ/RRNO" + ":" + "     " + "RR123F", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಖಾತೆ ಸಂಖ್ಯೆ/Account ID" + ":" + "     " + "12345678", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಹೆಸರು ಮತ್ತು ವಿಳಾಸ/Name and Address", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    Mr. K Vijay", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    Bangalore, Belgum", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಜಕಾತಿ/Tariff" + ":" + "     " + "LT2A", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಮಂ.ಪ್ರಮಾಣ/Sanct Load" + ":" + "     " + "HP" + " " + "5" + "KW" + " " + "3", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಪ್ರಿಂಟ್ ಡೇಟ್/PrintDate" + ":" + "     " + "17-08-2018", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಬಿಲ್ ಸಂಖ್ಯೆ/BillNo" + ":" + "     " + "1234567", 25, Typeface.MONOSPACE);
                new PrinterApi().multiLinguallinePrint("    ಮೀಟರ್ ಸಂಖ್ಯೆ/Meter SlNo" + ":" + "     " + "1234567", 25, Typeface.MONOSPACE);
            }
        });
    }


}
