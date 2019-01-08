package Shapes;

import org.eclipse.swt.graphics.GC;

public class Pol implements Shape {

    private static final String toolText = "对称三角形";
    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    public Pol() {

    }

    public Pol(int top, int left, int width, int height, GC gc) {
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
        //gcMain.drawRectangle(top,left,width,height);
        //gcMain.drawRoundRectangle(top,left,width,height,30,30);
        // gcMain.fillArc(top,left,width,height,20,20);

        gcMain.drawPolygon(new int[]{top, left, top + width, left + height, top - width, left + height});
        //gcMain.fillPolygon(new int[]{top,left,width,width,top,height});

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
