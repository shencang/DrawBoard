package BoardTools;

import Shapes.Shape;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Shell shell;
    private List<Shape> shapes;
    private GC super__gc;

    public Board (Shell shell,GC gc){
        shapes= new ArrayList<Shape>();
        this.shell=shell;
        super__gc=gc;
    }

    public void  InsertShape(Shape shape){
        shapes.add(shape);
    }

    public void Refresh(){
        Point point = shell.getSize();
        super__gc.fillRectangle(0,0,point.x,point.y);
        for (Shape shape:shapes){
            shape.Draw();
        }
    }

    public void Save(String filename)throws IOException {
//        DataOutputStream outputStream= new DataOutputStream(
//                new BufferedOutputStream(
//                        new FileOutputStream(filename)));
        PrintStream printStream  = new PrintStream(new File(filename));
        printStream.println(shapes.size());

        for (Shape shape:shapes){
             writeShape(printStream,shape);
        }
        printStream.close();
    }


    public void Open(String filename)throws IOException{
        shapes.clear();
        String line;
        BufferedReader reader= new BufferedReader(
                               new InputStreamReader(
                               new FileInputStream(filename)));
        line =reader.readLine();
        int shapeCount =Integer.parseInt(line);

        for (int i =0;i<shapeCount;i++){
            Shape shape = readShape(reader);
            InsertShape(shape);
        }
        reader.close();
        Refresh();
    }



    private void writeShape(PrintStream printStream, Shape shape) throws IOException{
        printStream.println(shape.getClass().getName());
        printStream.println(shape.getLeft());
        printStream.println(shape.getTop());
        printStream.println(shape.getWidth());
        printStream.println(shape.getHeight());
    }

    private Shape readShape(BufferedReader reader)throws IOException {

        String className= reader.readLine();
        String left =reader.readLine();
        String top  = reader.readLine();
        String width = reader.readLine();
        String height =reader.readLine();

        int left_b = Integer.parseInt(left);
        int top_b = Integer.parseInt(top);
        int width_b = Integer.parseInt(width);
        int heght_b = Integer.parseInt(height);

        Shape shape = null;
        try {
            Class<?> shapeClass = Class.forName(className);
            Object oShape = shapeClass.newInstance();
            shape = (Shape)oShape;
            shape.setTop(top_b);
            shape.setLeft(left_b);
            shape.setWidth(width_b);
            shape.setHeight(heght_b);
            shape.setGcMain(super__gc);
        }catch (Exception e){

        }
        return shape;
    }
}
