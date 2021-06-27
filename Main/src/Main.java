import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
  static int rowid = 0;
  public static void main(String[] args) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet spreadsheet = workbook.createSheet(" Trial ");

    JFrame frame = new JFrame(" INVENTORY ");
    frame.setSize(900, 600);

    LinkedList list = new LinkedList(5);

    JLabel l = new JLabel("OPTIONS -");
    JLabel l0 = new JLabel("PRODUCT DETAILS -");
    JLabel l1 = new JLabel("Name   :");
    JLabel l2 = new JLabel("Category   :");
    JLabel l3 = new JLabel("Price   :");
    JButton insertbt = new JButton("INSERT");
    JButton removebt = new JButton("DISPATCH");
    JButton searchbt = new JButton("SEARCH");
    JButton sizebt = new JButton("STORAGE");
    JButton statusbt = new JButton("STATUS");
    JButton clearbt = new JButton("CLEAR WAREHOUSE");
    JTextArea board = new JTextArea("\n     -   Warehouse Created Succesfully!");
    JTextField nametx = new JTextField("");
    JTextField pricetx = new JTextField("");
    JRadioButton r1 = new JRadioButton("Auto");
    JRadioButton r2 = new JRadioButton("Electronic");
    JRadioButton r3 = new JRadioButton("Fabric");
    JRadioButton r4 = new JRadioButton("Food");
    JRadioButton r5 = new JRadioButton("Other");

    l.setBounds(145, 35, 150, 30);
    l0.setBounds(440, 35, 150, 30);
    l1.setBounds(440, 80, 100, 20);
    l2.setBounds(440, 118, 100, 20);
    l3.setBounds(440, 210, 50, 25);
    insertbt.setBounds(620, 205, 100, 40);
    removebt.setBounds(270, 80, 100, 40);
    searchbt.setBounds(145, 80, 100, 40);
    sizebt.setBounds(270, 140, 100, 40);
    statusbt.setBounds(145, 140, 100, 40);
    clearbt.setBounds(145, 200, 225, 40);
    board.setBounds(135, 290, 600, 150);
    nametx.setBounds(500, 80, 220, 25);
    pricetx.setBounds(500, 212, 80, 25);
    r1.setBounds(440, 143, 100, 20);
    r2.setBounds(550, 143, 100, 20);
    r3.setBounds(660, 143, 100, 20);
    r4.setBounds(440, 168, 100, 20);
    r5.setBounds(550, 168, 100, 20);

    ButtonGroup bg = new ButtonGroup();
    bg.add(r1);
    bg.add(r2);
    bg.add(r3);
    bg.add(r4);
    bg.add(r5);

    frame.add(insertbt);
    frame.add(removebt);
    frame.add(searchbt);
    frame.add(sizebt);
    frame.add(statusbt);
    frame.add(clearbt);
    frame.add(board);
    frame.add(nametx);
    frame.add(pricetx);
    frame.add(r1);
    frame.add(r2);
    frame.add(r3);
    frame.add(r4);
    frame.add(r5);
    frame.add(l);
    frame.add(l0);
    frame.add(l1);
    frame.add(l2);
    frame.add(l3);

    insertbt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int id;
        String name = nametx.getText();
        String price = pricetx.getText();

        if (r1.isSelected()) {
          id = Integer.parseInt("1" + price);
          r1.setSelected(false);
        } else if (r2.isSelected()) {
          id = Integer.parseInt("2" + price);
          r2.setSelected(false);
        } else if (r3.isSelected()) {
          id = Integer.parseInt("3" + price);
          r3.setSelected(false);
        } else if (r4.isSelected()) {
          id = Integer.parseInt("4" + price);
          r4.setSelected(false);
        } else {
          id = Integer.parseInt("5" + price);
          r5.setSelected(false);
        }

        name = list.insert(id, name);
        board.setText(name);
        nametx.setText("");
        pricetx.setText("");
        XSSFRow row = spreadsheet.createRow(rowid++);
        Cell cell = row.createCell(1);
        cell.setCellValue(id);
        cell = row.createCell(0);
        cell.setCellValue("Import");
      }
    });

    removebt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int in = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Product ID : "));
        String str = list.remove( in );
        board.setText(str);
        XSSFRow row = spreadsheet.createRow(rowid++);
        Cell cell = row.createCell(1);
        cell.setCellValue( in );
        cell = row.createCell(0);
        cell.setCellValue("Export");
      }
    });

    clearbt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        list.makeEmpty();
        board.setText("\n    -   Storage Cleared Succesfully.");
      }
    });

    sizebt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board.setText("\n\t Warehouse Empty : " + list.isEmpty() + "\n\t Available Capacity " + (50 - list.getSize()) + " Out of 50 Slots.");
      }
    });

    statusbt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String str;
        int in = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Warehouse Field : "));
        if ( in > 1 || in < 6) {
          str = list.print( in );
        } else {
          str = "\n\t Warehouse Field Does Not Exist.";
        }
        board.setText(str);
      }
    });

    searchbt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int in = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Product ID : "));
        String str = list.search( in );
        board.setText(str);
      }
    });

    frame.setLayout(null);
    frame.setVisible(true);
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(frame, "Store Transactions in a Spreadsheet?", "Close Window?", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
          FileOutputStream out = null;
          try {
            out = new FileOutputStream(new File("Spreadsheet.xlsx"));
          } catch (FileNotFoundException e1) {
            e1.printStackTrace();
          }
          try {
            workbook.write(out);
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            workbook.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            out.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
          System.out.println("Writesheet.xlsx written successfully");
          System.exit(0);
        }
      }
    });
  }
}