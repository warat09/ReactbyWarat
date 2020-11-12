import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
    public class project extends javax.swing.JFrame {
    JLabel l = new JLabel();
    void tree() {

        String path = "C:\\Users\\y-tit\\OneDrive\\PROJECT3\\100g";
        String pathcds = "C:\\Users\\y-tit\\OneDrive\\PROJECT3\\100g\\XAGUSD60.csv";
        JFrame frame = new JFrame();
        File f = new File(path);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("100g");
        JTree jTree1 = new JTree(root);
        JPanel PJ = new JPanel();
        PJ.add(jTree1);
        JScrollPane SC = new JScrollPane(PJ);
        SC.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        SC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane v = new JScrollPane(l);
        v.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        v.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JSplitPane p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,SC,v);
        p.setResizeWeight(0.3);
        frame.add(p);

        jTree1.addTreeSelectionListener(new TreeSelectionListener(){
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                //System.out.println(tp);   
                DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode)tp.getLastPathComponent();
                StringBuilder newPath = new StringBuilder(); //can append and delete String//
                newPath.append("C:\\Users\\y-tit\\OneDrive\\PROJECT3");
                Object[] o = tp.getPath();
                //System.out.print(o);
                for(int i = 0; i< o.length;i++){
                   newPath.append("\\").append(o[i]);
                }
               String patha = newPath.toString();
               System.out.println(patha);
                System.out.println(pathcds);
               File newFile = new File(patha);
               //System.out.println(tp.getLastPathComponent());
                if(newFile.isDirectory()){
                    createfile(newFile,newRoot);
                }
                
             else{
                    if(patha.equals(pathcds)){
                        EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        new CandlestickDemo2("Love pee Tuay.").setVisible(true);
                        }});
                    }
                    else{
                        l.setIcon(new ImageIcon(patha));
                        }    
                }
            }
        

            public void createfile(File newFile, DefaultMutableTreeNode root) {
                File[] files = newFile.listFiles();
                for(File f:files){
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(f.getName());
                    root.add(newNode);
                }           
            }
            
        });
        
        /////box gui/////
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
class Csvcds{
    String path = "C:\\Users\\y-tit\\OneDrive\\PROJECT3\\100g\\XAGUSD60.csv";
    String line = "";
    int i = 0;
    public String[] date = new String[2048];
    public String[] hr = new String[2048];
    public double[] open = new double[2048];
    public double[] close = new double[2048];
    public double[] high = new double[2048];
    public double[] low = new double[2048];
    public double[] volume = new double[2048];

    Csvcds(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String [] data = line.split(",");
                if(i != 0){
                    date[i-1] = data[0];
                    hr[i-1] = data[1];
                    open[i-1] = Double.parseDouble(data[2]);
                    close[i-1] = Double.parseDouble(data[3]);
                    high[i-1] = Double.parseDouble(data[4]);
                    low[i-1] = Double.parseDouble(data[5]);
                    volume[i-1] = Double.parseDouble(data[6]);
System.out.println("date : "+date[i-1]+" hr : "+hr[i-1]+" open : "+open[i-1]+" close : "+close[i-1]+" max : "+high[i-1]+" min : "+low[i-1]+" volume : "+volume[i-1]);
                }
                i++;
            }
        
        }catch(IOException e){
            e.printStackTrace(); 
        }
    }

}
/**
 * @see https://stackoverflow.com/a/18421887/230513
 * @see http://www.jfree.org/forum/viewtopic.php?f=10&t=24521
 */
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
class CandlestickDemo2 extends JFrame   {
    project j = new project();
    public CandlestickDemo2(String stockSymbol) {
        super("CandlestickDemo2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final DateAxis domainAxis = new DateAxis("Date");
        NumberAxis rangeAxis = new NumberAxis("Price");
        CandlestickRenderer renderer = new CandlestickRenderer();
        XYDataset dataset = getDataSet(stockSymbol);
        XYPlot mainPlot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        //Do some setting up, see the API Doc
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setDrawVolume(false);
        rangeAxis.setAutoRangeIncludesZero(false);
        //Now create the chart and chart panel
        JFreeChart chart = new JFreeChart(stockSymbol, null, mainPlot, false);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        mainPlot.setDomainPannable(true);
        mainPlot.setRangePannable(true);
        this.add(chartPanel);
        // Add tiemline toggle
        final Timeline oldTimeline = domainAxis.getTimeline();
        this.pack();
        this.setLocationRelativeTo(null);
        
    }

    private AbstractXYDataset getDataSet(String stockSymbol) {
        //This is the dataset we are going to create
        DefaultOHLCDataset result;
        //This is the data needed for the dataset
        OHLCDataItem[] data;
        //This is where we go get the data, replace with your own data source
        data = getData(stockSymbol);
        //Create a dataset, an Open, High, Low, Close dataset
        result = new DefaultOHLCDataset(stockSymbol, data);
        return result;
    }
    //This method uses yahoo finance to get the OHLC data

    protected OHLCDataItem[] getData(String stockSymbol) {
        Csvcds c = new Csvcds();     
        java.util.List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
        try {    
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String inputLine;
            for(int i = 0;i < 2048;i++) {
                Date date = df.parse(c.date[i]+" "+c.hr[i]);  
                OHLCDataItem item = new OHLCDataItem(date, c.open[i],c.high[i],c.low[i],c.close[i],c.volume[i]);
                dataItems.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        //Data from Yahoo is from newest to oldest. Reverse so it is oldest to newest
        Collections.reverse(dataItems);
        //Convert the list into an array
        OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        return data;
    }

}
class main {
    
    public static void main(String[] args) {
        project PJ = new project();
        PJ.tree();
    }
}

