package BoardTools;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    public static List<Class<?>> getClassList(String pkgName,
                                              boolean isRecursive,
                                              Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    System.out.println("protocol:" + protocol + " path:" + pkgPath);
                    if ("file".equals(protocol)) {
                        // 本地自己可见的代码
                        findClassName(classList, pkgName, pkgPath, isRecursive, annotation);
                    } else if ("jar".equals(protocol)) {
                        // 引用第三方jar的代码
                        findClassName(classList, pkgName, url, isRecursive, annotation);
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }

    private static void findClassName(List<Class<?>> classList,
                                      String pkgName,
                                      String pkgPath,
                                      boolean isRecursive,
                                      Class<? extends Annotation> annotation) {
        if (classList == null) {
            return;
        }
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        System.out.println("files:" + ((files == null) ? "null" : "length=" + files.length));
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String className = getClassName(pkgName, fileName);
                    addClassName(classList, className, annotation);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(classList, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }


    /**
     * 第三方Jar类库的引用。
     *
     * @throws IOException
     */
    public static void findClassName(List<Class<?>> classList,
                                     String pkgName, URL url,
                                     boolean isRecursive,
                                     Class<? extends Annotation> annotation) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        System.out.println("jarFile:" + jarFile.getName());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String className = jarEntryName.replace("/", ".");
            int endIndex = className.lastIndexOf(".");
            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = className.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {
//				System.out.println("prefix:" + prefix +" pkgName:" + pkgName);
                if (prefix.equals(pkgName)) {
                    System.out.println("jar entryName:" + jarEntryName);
                    addClassName(classList, className, annotation);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    System.out.println("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(classList, className, annotation);
                }
            }
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String classNames = null;
        if (endIndex >= 0) {
            classNames = fileName.substring(0, endIndex);
        }
        String className = null;
        if (classNames != null) {
            className = pkgName + "." + classNames;
        }
        return className;
    }

    private static void addClassName(List<Class<?>> classList,
                                     String className,
                                     Class<? extends Annotation> annotation) {
        if (classList != null && className != null) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//			System.out.println("isAnnotation=" + clazz.isAnnotation() +" author:" + clazz.isAnnotationPresent(author.class));

            if (clazz != null) {
                if (annotation == null) {
                    classList.add(clazz);
                    System.out.println("add:" + clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    classList.add(clazz);
                    System.out.println("add annotation:" + clazz);
                }
            }
        }
    }
}
