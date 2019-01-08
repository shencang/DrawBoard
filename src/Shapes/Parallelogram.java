package Shapes;

import org.eclipse.swt.graphics.GC;

public class Parallelogram implements Shape {
    private static final String toolText = "平行四边形";
    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    public Parallelogram() {

    }

    public Parallelogram(int top, int left, int width, int height, GC gc) {
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
        gcMain.drawPolyline(new int[]{
                (top + (width) / 4), left,
                top + width, left,
                top + (width) * 3 / 4, left + height,
                top, left + height,
                (top + (width) / 4), left});
//        gcMain.drawPolyline(new int[]{
//                100,200,300,
//                400});
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
