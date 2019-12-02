public class JNIClass {

    static {
        System.loadLibrary("native");
    }

    public native String getProcInfo();

    public static void main(String[] args) {
        String info = new JNIClass().getProcInfo();
        System.out.println(info);
    }

}
