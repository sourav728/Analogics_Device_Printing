package com.example.tvd.analogics_device_printing.values;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.analogics.library.printer.ImageAlignment;
import com.analogics.library.printer.ImageScale;
import com.analogics.library.printer.LogoPrint;
import com.analogics.utils.AnalogicsUtils;
import com.analogics.utils.PrintSpliter;
import com.analogics.utils.TextConverter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FunctionCall {

    public String space(String s, int len) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = len - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.append(" ");
        }
        return (s + spaces);
    }

    public String alignright(String msg, int len) {
        StringBuilder msgBuilder = new StringBuilder(msg);
        for (int i = 0; i < len - msgBuilder.length(); i++) {
            msgBuilder.insert(0, " ");
        }
        msg = msgBuilder.toString();
        msg = String.format("%" + len + "s", msg);
        return msg;
    }

    public String aligncenter(String msg, int len) {
        int count = msg.length();
        int value = len - count;
        int append = (value / 2);
        return space(" ", append) + msg + space(" ", append);
    }
    public String currentDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        return sdf.format(new Date());
    }

    public void multiLinguallinePrint2(String Printdata, int text_size, Typeface typeface) {
        new AnalogicsUtils();
        TextConverter convert = new TextConverter();
        new PrintSpliter();
        Bitmap bmp = textAsBitmap2(Printdata, (float)text_size, 9.0F, -16711681, typeface);
        convert.addBorder(bmp, 0, 0);
        String[] c = null;
        try {
            Bitmap mBitmap = bmp.copy(Bitmap.Config.ARGB_4444, true);
            c = this.prepareDataToPrint(mBitmap);
            this.printImageData(c);
            Thread.sleep(400L);
        } catch (InterruptedException var12) {
            var12.printStackTrace();
        }

    }

    public Bitmap textAsBitmap2(String text, float textSize, float stroke, int color, Typeface typeface) {
        TextPaint paint = new TextPaint();
        paint.setColor(color);
        paint.setDither(false);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(stroke);
        paint.setTypeface(typeface);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = (float)((int)(-paint.ascent() + 3.0F));
        StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), paint, 1000, Layout.Alignment.ALIGN_NORMAL, 1.0F, 1.0F, false);
        int linecount = staticLayout.getLineCount();
        int height = (int)(baseline + paint.descent()) * linecount + 10;
        Bitmap image = Bitmap.createBitmap(1000, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(image);
        staticLayout.draw(canvas);
        return image;
    }

    public String[] prepareDataToPrint(Bitmap bmp) throws InterruptedException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            InputStream in = new ByteArrayInputStream(stream.toByteArray());
            BufferedInputStream buf = new BufferedInputStream(in);
            byte[] bMapArray = null;

            try {
                bMapArray = new byte[buf.available()];
                buf.read(bMapArray);
            } catch (IOException var13) {
                var13.printStackTrace();
            }

            Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
            new LogoPrint();
            ImageScale ImageScaleret = ImageScale.valueOf("SCALE_ONE_TO_ONE");
            ImageAlignment ImageAlignmentret = ImageAlignment.valueOf("IMAGE_CENTER");
            byte[] data1 = LogoPrint.printImage(bMap, ImageScaleret, ImageAlignmentret);
            byte[] cmddata = LogoPrint.printImageCommand(bMap, ImageScaleret, ImageAlignmentret);
            outputStream.write(cmddata);
            outputStream.write(data1);
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        byte[] imagedata = outputStream.toByteArray();
        StringBuilder sb = new StringBuilder();
        String[] ss = new String[imagedata.length];
        int i = 0;
        byte[] var21 = imagedata;
        int var20 = imagedata.length;

        for(int var8 = 0; var8 < var20; ++var8) {
            byte b = var21[var8];
            sb.append("0x" + String.format("%02X ", b));
            ss[i] = "0x" + String.format("%02X ", b);
            ++i;
        }

        return ss;
    }


    public boolean printImageData(String[] printdata) {
        boolean response = false;

        try {
            String[] str = new String[]{"multybyte-twiddler", "w", "0x3C"};
            String[] sd1 = this.f(str, printdata);
            Process process = Runtime.getRuntime().exec(sd1);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            char[] buffer = new char[5000];
            StringBuffer commandOutput = new StringBuffer();

            int numRead;
            while((numRead = bufferedReader.read(buffer)) > 0) {
                commandOutput.append(buffer, 0, numRead);
            }

            bufferedReader.close();
            process.waitFor();
            response = true;
            return response;
        } catch (IOException var11) {
            return response;
        } catch (InterruptedException var12) {
            return response;
        }
    }

    public String[] f(String[] first, String[] second) {
        List<String> both = new ArrayList(first.length + second.length);
        Collections.addAll(both, first);
        Collections.addAll(both, second);
        return (String[])both.toArray(new String[both.size()]);
    }
    //Making left alignment for values
    public String leftalign(String s1, int len) {
        StringBuilder s1Builder = new StringBuilder(s1);
        for (int i = 0; i < len - s1Builder.length(); i++) {
            s1Builder.append(" ");
        }
        s1 = s1Builder.toString();
        return (s1);
    }
}
