import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.event.*;
import javax.swing.plaf.DimensionUIResource;
import java.util.Scanner;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.*;

class S_data_6_hour {
    public static int count = 0;
    public double open;
    public double close;
    public double low;
    public double high;
    public Date date;
    public String time;
    public double volume;

    public S_data_6_hour(double i_open, double i_close, double i_low, double i_high, Date i_date) {
        this.open = i_open;
        this.close = i_close;
        this.low = i_low;
        this.high = i_high;
        this.date = i_date;
        count++;
    }

    public void print_data() {
        System.out.println("Data :" + this.date);
        System.out.println("Time :" + this.time);
        System.out.println("Open :" + this.open);
        System.out.println("High :" + this.high);
        System.out.println("Low :" + this.low);
        System.out.println("Close :" + this.close);
        System.out.println("Volume :" + this.volume);
    }
}
class S_data_24_hour {
    public static int count = 0;
    public double open;
    public double close;
    public double low;
    public double high;
    public Date date;
    public String time;
    public double volume;

    public S_data_24_hour(double i_open, double i_close, double i_low, double i_high, Date i_date) {
        this.open = i_open;
        this.close = i_close;
        this.low = i_low;
        this.high = i_high;
        this.date = i_date;
        count++;
    }

    public void print_data() {
        System.out.println("Data :" + this.date);
        System.out.println("Time :" + this.time);
        System.out.println("Open :" + this.open);
        System.out.println("High :" + this.high);
        System.out.println("Low :" + this.low);
        System.out.println("Close :" + this.close);
        System.out.println("Volume :" + this.volume);
    }
}
class S_data_1_hour {
    public static int count = 0;
    public double open;
    public double close;
    public double low;
    public double high;
    public Date date;
    public String time;
    public double volume;

    public S_data_1_hour() {}

    public void set_data(String[] arr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        this.date = df.parse(arr[0] + " " + arr[1]);
        this.time = arr[1];
        this.open = Double.parseDouble(arr[2]);
        this.high = Double.parseDouble(arr[3]);
        this.low = Double.parseDouble(arr[4]);
        this.close = Double.parseDouble(arr[5]);
        this.volume = Double.parseDouble(arr[6]);
        this.count++;
    }

    public void print_data() {
        System.out.println("Data :" + this.date);
        System.out.println("Time :" + this.time);
        System.out.println("Open :" + this.open);
        System.out.println("High :" + this.high);
        System.out.println("Low :" + this.low);
        System.out.println("Close :" + this.close);
        System.out.println("Volume :" + this.volume);
    }

}

class R_data {
    private static final String file_path = "C:\\Users\\y-tit\\OneDrive\\PROJECT3\\100g\\XAGUSD60.csv";
    private static final String DELIMITER = ",";
    private Scanner infile;
    public S_data_1_hour[] H1_data;
    public S_data_6_hour[] H6_data;
    public S_data_24_hour[] H24_data;
    public R_data() throws FileNotFoundException, ParseException {
        this.infile = new Scanner(new File(file_path));
        this.H1_data = new S_data_1_hour[2048];
        for (int i = 0; i < H1_data.length; i++) {
            this.H1_data[i] = new S_data_1_hour();
        }
        this.Read_file();
        this.set_H6_data();
        this.set_H24_data();
    }

    public void Read_file() throws ParseException, FileNotFoundException {
        this.infile.useDelimiter(DELIMITER);
        int count = 0;
        while (infile.hasNext()) {
            if (count == 0) {
                infile.nextLine();
                count++;
            } else {
                String[] temp = infile.nextLine().split(DELIMITER, 7);
                this.H1_data[count - 1].set_data(temp);
                count++;
            }
        }
        infile.close();
    }

    public void set_H6_data() {
        System.out.println(S_data_1_hour.count);
        this.H6_data = new S_data_6_hour[S_data_1_hour.count / 6];
        int c_count = 0;
        double open = this.H1_data[0].open;
        double close =this.H1_data[0].close;
        double low = this.H1_data[0].low;
        double high = this.H1_data[0].high;
        for (int i = 0; i < S_data_1_hour.count; i++) {
            if (i != 0) {
                if (i % 6 == 0) {
                    close = this.H1_data[i].close;
                    this.H6_data[c_count] = new S_data_6_hour(open, close, low, high, this.H1_data[i].date);
                    this.H6_data[c_count].print_data();
                    c_count++;
                } else if (i % 6 == 1) {
                    open = this.H1_data[i].open;
                    low = this.H1_data[i].low;
                    high = this.H1_data[i].high;
                } else {
                    if (low > this.H1_data[i].low) {
                        low = this.H1_data[i].low;
                    }
                    if (high < this.H1_data[i].high) {
                        high = this.H1_data[i].high;
                    }
                }
            }
        }
    }
    public void set_H24_data()
    {
        this.H24_data = new S_data_24_hour[S_data_1_hour.count/24];
        int c_count = 0;
        double open = 0;
        double close = 0;
        double low = 0;
        double high = 0;
        for (int i = 0; i < S_data_1_hour.count; i++) {
            if (i != 0) {
                if (i % 24 == 0) {
                    close = this.H1_data[i].close;
                    this.H24_data[c_count] = new S_data_24_hour(open, close, low, high, this.H1_data[i].date);
                    c_count++;
                } else if (i % 24 == 1) {
                    open = this.H1_data[i].open;
                    low = this.H1_data[i].low;
                    high = this.H1_data[i].high;
                } else {
                    if (low > this.H1_data[i].low) {
                        low = this.H1_data[i].low;
                    }
                    if (high < this.H1_data[i].high) {
                        high = this.H1_data[i].high;
                    }
                }
            }
        }
    }
}
class S_main extends JFrame implements ActionListener {
    private String Mode;
    private JPanel[] JP;
    private JLabel[] JL;
    private JButton[] JB;
    private JScrollPane[] SCP;
    private R_data manage_data;
    private JFreeChart chart;
    private CandlestickRenderer renderer;
    private DateAxis domainAxis;
    private NumberAxis rangeAxis;
    private ChartPanel chartPanel;
    private void set_label() {
        JL[0] = new JLabel("Project 2");
        JL[0].setFont(new Font(null, Font.PLAIN, 60));
        JL[0].setPreferredSize(new Dimension(200, 200));
        // JL[0].setBounds(10,10,300,300);
        JP[1].add(JL[0], BorderLayout.PAGE_START);
    }

    private void set_screen() {
        JP[0] = new JPanel();
        JP[1] = new JPanel(new BorderLayout());
        JP[1].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JP[2] = new JPanel();
    }

    private void set_button() {
        JB[0] = new JButton("24 Hour");
        JB[0].addActionListener(this);
        // JB[0].setBounds(10,10,100,100);
        JB[1] = new JButton("6 Hour");
        JB[1].addActionListener(this);
        JB[1].setPreferredSize(new Dimension(100, 80));
        // JB[1].setBounds(10,10,100,100);
        JB[2] = new JButton("1 Hour");
        JB[2].addActionListener(this);
        JP[1].add(JB[2], BorderLayout.WEST);
        JP[1].add(JB[1], BorderLayout.CENTER);
        JP[1].add(JB[0], BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent e) {
        // System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("1 Hour")) {
            this.Mode = "1 Hour";
        } else if (e.getActionCommand().equals("6 Hour")) {
            this.Mode = "6 Hour";
        } else if (e.getActionCommand().equals("24 Hour")) {
            this.Mode = "24 Hour";
        } else {

        }
        // JL[2].removeAll();
        //this.chart.setTitle(this.Mode);
        // JP[2].removeAll();
        chart.removeLegend();
        try {
            this.set_candle();
        } catch (FileNotFoundException | ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        chartPanel.setChart(chart);
        JP[2].add(chartPanel);
    }
    public S_main() throws FileNotFoundException, ParseException
    {
        manage_data = new R_data();
        JP = new JPanel[5];
        JL = new JLabel[5];
        JB = new JButton[5];
        SCP = new JScrollPane[2];
        this.Mode = new String("1 Hour");
        domainAxis = new DateAxis("Data");
        rangeAxis = new NumberAxis("Price");
        renderer = new CandlestickRenderer();
        set_screen();
        set_label();
        set_button();
        JP[0].add(JP[1]);
        this.set_candle();
        //JP[2].add(this.chartPanel);
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(800,600));
        JP[2].add(chartPanel);
        JP[0].add(JP[2]);
        super.add(JP[0]);
        super.setTitle("Project 2");
        super.setPreferredSize(new DimensionUIResource(1250,720));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.pack();
    }
    public XYPlot set_data()
    {
        XYDataset dataset = this.getdataset();
        XYPlot mainPlot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        return mainPlot;
    }
    public void set_candle() throws FileNotFoundException, ParseException
    {
        XYPlot mainPlot = this.set_data();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setDrawVolume(false);
        rangeAxis.setAutoRangeIncludesZero(false);
        chart = new JFreeChart(this.Mode, null, mainPlot, false);
        mainPlot.setDomainPannable(true);
        mainPlot.setRangePannable(true);
        //final Timeline oldTimeline = domainAxis.getTimeline();
        //domainAxis.setTimeline(oldTimeline);
    }
    public AbstractXYDataset getdataset()
    {
        DefaultOHLCDataset result;
        OHLCDataItem[] data;
        data = this.getdata();
        result = new DefaultOHLCDataset(this.Mode,data);
        return result;
    }
    public OHLCDataItem[] getdata()
    {
        if(this.Mode.equals("1 Hour"))
        {
            System.out.println("1 Hour");
            List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
            for(int i = 0;i<manage_data.H1_data.length;i++)
            {
            OHLCDataItem item = new OHLCDataItem(manage_data.H1_data[i].date,manage_data.H1_data[i].open,manage_data.H1_data[i].high,manage_data.H1_data[i].low,manage_data.H1_data[i].close,manage_data.H1_data[i].volume);
            dataItems.add(item);
                }
            OHLCDataItem[] temp = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
            return temp;
        }
        else if(this.Mode.equals("6 Hour"))
        {
            System.out.println("6 hour");
            List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
            for(int i = 0;i<manage_data.H6_data.length;i++)
            {
                OHLCDataItem item = new OHLCDataItem(manage_data.H6_data[i].date,manage_data.H6_data[i].open,manage_data.H6_data[i].high,manage_data.H6_data[i].low,manage_data.H6_data[i].close,manage_data.H6_data[i].volume);
                System.out.println(manage_data.H6_data[i].date);
                dataItems.add(item);
            }
            OHLCDataItem[] temp = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
            return temp;
        }
        else if(this.Mode.equals("24 Hour"))
        {
            System.out.println("6 hour");
            List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
            for(int i = 0;i<manage_data.H24_data.length;i++)
            {
                OHLCDataItem item = new OHLCDataItem(manage_data.H24_data[i].date,manage_data.H24_data[i].open,manage_data.H24_data[i].high,manage_data.H24_data[i].low,manage_data.H24_data[i].close,manage_data.H24_data[i].volume);
                System.out.println(manage_data.H24_data[i].date);
                dataItems.add(item);
            }
            OHLCDataItem[] temp = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
            return temp;
        }
        else
        {
            return null;
        }
    }
}
public class project2
{
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        S_main test = new S_main();
    }
}