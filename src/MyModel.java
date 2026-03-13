import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private int rowCount;
    private int columnCount;
    private ArrayList<Object[]> data = new ArrayList<>();
    private String[] columnNames;

    public MyModel(ResultSet rs) throws Exception {
        setRS(rs);
    }

    public void setRS(ResultSet rs) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();
        rowCount = 0;
        columnCount = metaData.getColumnCount();
        columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }
        data.clear();
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
                row[j] = rs.getObject(j + 1);
            }
            data.add(row);
            rowCount++;
        }
        fireTableStructureChanged();
    }

    public int getColumnCount() { return columnCount; }
    public int getRowCount() { return rowCount; }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = data.get(rowIndex);
        return row[columnIndex];
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
