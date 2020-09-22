//package addressbook.panel;
//
//import java.awt.BorderLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.Vector;
//
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ListSelectionModel;
//import javax.swing.table.DefaultTableModel;
//
//import myway.product.sandwich.bread.Bread;
//import myway.product.sandwich.extra.Extra;
//import myway.stock.StockManager;
//import myway.stock.StockVO;
//
//public class StockPanel extends JPanel {
//    private static final long serialVersionUID = -3025233946911256790L;
//    
//    private StockManager stMgr;
//                                            // ǰ��, �̸�, ����
//    private DefaultTableModel defaultTableModel; // �⺻ ���̺� �� ��
//    private JTable table;
//    private JScrollPane scrollPane;
//    //	private JTabbedPane tabbedPane;
//
//
//    /**
//     * Create the frame.
//     * 
//     * @throws Exception
//     */
//    public StockPanel() throws Exception {
//        stMgr = new StockManager();
//        setLayout(new BorderLayout());
//
//        defaultTableModel = new DefaultTableModel(getStockVector(), getHeaderVector());
//        table = new JTable();
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton() == 1) {
//                    int row = table.getSelectedRow();
//                    int col = table.getSelectedColumn();
//                    Object korName = table.getValueAt(row, 2);
//
//                    if (col != 4) {
//                        return;
//                    }
//                    
//                    String str = "������ ���� �Է� :\n" + korName;
//                    String inputStock = JOptionPane.showInputDialog(str);
//
//                    try {
//                        int real = Integer.parseInt(inputStock);
//                        int stockNo = Integer.valueOf((String) table.getValueAt(row, 0));
//                        stMgr.update(stockNo, real);
//                        tableRefresh();
//                    } catch (NumberFormatException e1) {
//                        System.out.println("�����Է��ض�");
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        scrollPane = new JScrollPane(table);
//
//        table.setModel(defaultTableModel);
//        scrollPane.setViewportView(table);
//
//        add(scrollPane, BorderLayout.CENTER);
//    }
//    
//    public void tableRefresh() throws Exception {
//        defaultTableModel = new DefaultTableModel(getStockVector(), getHeaderVector());
//        table.setModel(defaultTableModel);
//    }
//    
//    private Vector<Vector<String>> getStockVector() throws Exception {
//        Vector<Vector<String>> stockVector = new Vector<>();
//        
//        HashMap<Integer, StockVO> getStock = stMgr.selectAll();
//        Set<Integer> keySet = getStock.keySet();
//        for (Integer key : keySet) {
//            // System.out.println(key);
//            Vector<String> rowVector = new Vector<>();
//            StockVO stockVo = getStock.get(key); // StockVO Ƽ���
//
//            // ������ȣ
//            rowVector.add(String.valueOf(key));
//            // ����
//            rowVector.add(stockVo.getStockType());
//            // �ѱ��̸�
//            if (Bread.getBread(key) != null) {
//                rowVector.add(Bread.getBread(key).getKorName());
//            } else if (Extra.getExtra(key) != null) {
//                rowVector.add(Extra.getExtra(key).getKorName());
//            }
//            // �����̸�
//            if (Bread.getBread(key) != null) {
//                rowVector.add(Bread.getBread(key).getName());
//            } else if (Extra.getExtra(key) != null) {
//                rowVector.add(Extra.getExtra(key).getName());
//            }
//            // ����
//            rowVector.add(String.valueOf(stockVo.getQuantity()));
//
//            stockVector.add(rowVector);
//        }
//        return stockVector;
//    }
//    
//    private Vector<String> getHeaderVector() {
//        Vector<String> headerNameVector = new Vector<String>();
//        headerNameVector.add("��ȣ");
//        headerNameVector.add("����");
//        headerNameVector.add("�ѱ��̸�");
//        headerNameVector.add("�����̸�");
//        headerNameVector.add("����");
//        return headerNameVector;
//    }
//
//}
