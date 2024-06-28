import by.zhorau.deepclone.copyutils.CopyUtils;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int b = 2;
        int a = (Integer)CopyUtils.deepClone(b);
        System.out.println(a);
    }
}