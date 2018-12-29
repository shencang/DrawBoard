package Shapes;


import org.eclipse.swt.graphics.GC;

public interface Shape {
    void Draw();
    int getTop();
    int getLeft();
    int getWidth();
    int getHeight();
    GC getMain();

    void setTop(int top);
    void setLeft(int Left);
    void setWidth(int Width);
    void setHeight(int Height);
    void setGcMain(GC gcMain);


}
