package Shapes;

import org.eclipse.swt.graphics.GC;

public class PerspectiveCylinder implements Shape {
    private static final String toolText = "透视圆柱";
    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    public PerspectiveCylinder() {

    }

    public PerspectiveCylinder(int top, int left, int width, int height, GC gc) {
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
        gcMain.drawOval(top, left, width, height);
        gcMain.drawLine(top, left + height / 2, top, left + height * 2);
        gcMain.drawLine(top + width, left + height / 2, top + width, left + height * 2);
        gcMain.drawOval(top, (int) (left + height * 1.5), width, height);


    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    @Override
    public void setLeft(int left) {
        this.left = left;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
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
