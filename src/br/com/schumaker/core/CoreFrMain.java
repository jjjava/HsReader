package br.com.schumaker.core;

import br.com.schumaker.gfx.FrMain;
import javax.swing.JFileChooser;

/**
 *
 * @author hudson.sales
 * @verison 1.0.0
 */
public class CoreFrMain {

    private static final CoreFrMain INSTANCE = new CoreFrMain();
    private FrMain frMain;
    private String path;
    private int ini;
    private int end;
    private int pageNumber;
    private int total;
    private long size;

    private CoreFrMain() {
        ini = 0;
        end = 10240;
        pageNumber = 1;
    }

    public static CoreFrMain getInstance() {
        return INSTANCE;
    }

    public void open() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setDialogTitle("Open File");
        chooser.setApproveButtonText("Open");
        int sf = chooser.showOpenDialog(null);
        if (sf == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();
            size = ReadFileWithMappedByteBuffer.getSizeString(path);

            total = (int) (size / end);
            frMain.setPageNumber(1);
            read();
        }
    }

    private void read() {
        frMain.setContent(HsStringUtil.simpleBold(
                ReadFileWithMappedByteBuffer.readFile(path, ini, end),
                frMain.getSearch()));
    }

    public void foward() {
        pageNumber++;
        frMain.setPageNumber(pageNumber);
        ini = end;
        end = end + 10240;
        read();
    }

    public void back() {
        pageNumber--;
        frMain.setPageNumber(pageNumber);
        ini = end - 10240;
        end = ini + 10240;
        read();
    }

    public void exit() {
        System.exit(0);
    }

    public FrMain getFrMain() {
        return frMain;
    }

    public void setFrMain(FrMain frMain) {
        this.frMain = frMain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIni() {
        return ini;
    }

    public void setIni(int ini) {
        this.ini = ini;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
