package Shapes;

import org.eclipse.swt.graphics.GC;

public class Line implements Shape {

    private static final String toolText = "直线";
    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    public Line() {

    }

    public Line(int top, int left, int width, int height, GC gc) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.gcMain = gc;
    }

    public static String getToolText() {
        return toolText;
    }

    @Override
    public void Draw() {
        gcMain.drawLine(top, left, top + width, left + height);

    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public void setTop(int top) {
        this.top = top;

    }

    @Override
    public int getLeft() {
        return left;
    }

    @Override
    public void setLeft(int Left) {

        this.left = Left;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int Width) {

        this.width = Width;

    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int Height) {
        this.height = Height;

    }

    @Override
    public GC getMain() {
        return gcMain;
    }

    @Override
    public void setGcMain(GC gcMain) {

        this.gcMain = gcMain;
    }
}
