package tech.tablesaw.table;

import org.junit.Test;
import tech.tablesaw.api.Table;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangli on 2017/10/13.
 */
public class TableTest {

    @Test
    public void constructTest() throws Exception {
        String data = "c1@category | c2@INTEGER \n" +
                "a           | 1 \n" +
                "b           | 2 \n" +
                "null        | 3 \n";
        
        Table table = Table.construct(data);
        assertEquals(table.rowCount(), 3);
        assertEquals(table.columnCount(), 2);

        assertEquals(table.column(0).name(), "c1@category");
        assertEquals(table.column(1).name(), "c2@INTEGER");

        assertEquals(table.column(0).getString(0), "a");
        assertEquals(table.column(0).getString(1), "b");

        assertEquals(table.column(1).getString(0), "1");
        assertEquals(table.column(1).getString(1), "2");

        assertEquals(table.column(0).getString(2), "");
    }

    @Test
    public void joinTest() throws Exception {
        String leftData = "c1@category | c2@category";
        String rightData = "c1@category | c2@category \n" +
                "a           | 1 \n" +
                "b           | 2 \n" +
                "c           | 3 \n";
        
        Table left = Table.construct("left", leftData);
        Table right = Table.construct("right", rightData);
        Table result = left.innerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 0);

        result = left.leftJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 0);

        result = left.outerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);
    }

    @Test
    public void joinTest2() throws Exception {
        String leftData = "c1@category | c2@category \n" +
                "a           | 1 \n" +
                "b           | 2 \n" +
                "c           | 3 \n";
        
        String rightData = "c1@category | c2@category";
        Table left = Table.construct("left", leftData);
        Table right = Table.construct("right", rightData);

        Table result = left.innerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 0);

        result = left.leftJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);

        result = left.outerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);
    }

    @Test
    public void joinTest3() throws Exception {
        String leftData = "c1@category | c2@category \n" +
                "a           | 1 \n" +
                "b           | 2 \n" +
                "c           | 3 \n";
        String rightData = "c1@category | c2@category \n" +
                "a           | 1 \n" +
                "b           | 2 \n" +
                "c           | 3 \n";
        
        Table left = Table.construct("left", leftData);
        Table right = Table.construct("right", rightData);

        Table result = left.innerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);

        result = left.leftJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);

        result = left.outerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 3);
    }

    @Test
    public void joinTest4() throws Exception {
        String leftData = "c1@category | c2@category \n" +
                "null           | 3 \n";
        String rightData = "c1@category | c2@category \n" +
                "null           | 1 \n";
        
        Table left = Table.construct("left", leftData);
        Table right = Table.construct("right", rightData);

        Table result = left.innerJoin(right, "c1@category", "c1@category");
        assertEquals(result.rowCount(), 1);
    }
}