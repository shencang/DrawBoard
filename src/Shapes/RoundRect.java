package Shapes;

import org.eclipse.swt.graphics.GC;

public class RoundRect implements Shape {

    private int top;
    private int left;
    private int width;
    private int height;
    private GC gcMain;

    private static final String toolText = "RoundRect";
    public static String getToolText(){
        return toolText;
    }

    public RoundRect(){

    }

    public RoundRect(int top, int left, int width, int height, GC gc){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.gcMain = gc;
    }
    @Override
    public void Draw() {
        gcMain.drawRectangle(top,left,width,height);

    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
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
    public void setTop(int top) {
        this.top=top;

    }

    @Override
    public void setLeft(int Left) {

        this.left= Left;
    }

    @Override
    public void setWidth(int Width) {

        this.width=Width;

    }

    @Override
    public void setHeight(int Height) {
        this.height=Height;

    }

    @Override
    public void setGcMain(GC gcMain) {

        this.gcMain=gcMain;
    }
}
