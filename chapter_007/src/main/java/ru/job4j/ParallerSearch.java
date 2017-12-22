package ru.job4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Mikhailov
 * @since 1.0
 */
public class ParallerSearch {
    private String root;
    private String text;
    private List<String> exts;

    private List<String> resultList = new ArrayList<>();

    List<Thread> massThread = new ArrayList<Thread>();

    /**
     * Конструктор
     *
     * @param root - путь до папки откуда надо осуществлять поиск.
     * @param text - заданный текст.
     * @param exts - расширения файлов в которых нужно делать поиск.
     */
    public ParallerSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * @return List<String> названия файлов удовлетворяющих расширению List<String> exts
     * и имеющих вхождение заданного текста String text
     * @throws IOException
     */
    List<String> result() throws IOException, InterruptedException {
        File fl = new File(root);
        checkFilesFromFolder(fl);
        waitAllThread();

        return resultList;
    }

    /**
     * Проверка файлов данного каталога и вложенных в него.
     * Вызов метода searchInFile() для файлов
     *
     * @param folder проверяемый каталог
     * @throws IOException
     */
    private void checkFilesFromFolder(File folder) throws IOException, InterruptedException {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isFile()) {
                Thread t = new Thread(new SearchInFileThread(text, exts, entry, resultList));
                t.start();
                massThread.add(t);
            } else {
                checkFilesFromFolder(entry);
            }
        }
    }

    private void waitAllThread() throws InterruptedException {
        for(Thread t : massThread) {
            t.join();
        }
    }
}

class SearchInFileThread implements Runnable {

    private String text;
    private List<String> exts;
    private File f;
    private List<String> resultList;

    public SearchInFileThread(String text, List<String> exts, File f, List<String> resultList) {
        this.text = text;
        this.exts = exts;
        this.f = f;
        this.resultList = resultList;
    }

    @Override
    public void run() {
        for (String st : exts) {
            if (getFileExtension(f).equals(st)) {
                byte[] content = new byte[0];
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(f);
                    content = new byte[fis.available()];
                    fis.read(content);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                String lines = new String(content, StandardCharsets.UTF_8);
                if (lines.contains(text)) {
                    resultList.add(f.getName());
                }
            }
        }
    }

    /**
     * Возвращает расширение файла
     *
     * @param file
     * @return символы после последней точки в названии файла или пустую строку если расширение не определено
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
