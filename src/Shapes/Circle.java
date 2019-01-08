package Shapes;

import org.eclipse.swt.graphics.GC;

public class Circle implements Shape {
    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    private static final String toolText = "圆类";
    public static String getToolText(){
        return toolText;
    }

    public Circle(){

    }

    public Circle(int top, int left, int width, int height, GC gc){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.gcMain = gc;
    }

    @Override
    public void Draw() {
        gcMain.drawOval(top,left,width,height);
    }

    @Override
    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public GC getMain() {
        return gcMain;
    }

    @Override
    public void setGcMain(GC gcMain) {
        this.gcMain = gcMain;
    }

    @Override
    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public void setLeft(int left) {
        this.left = left;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

}
