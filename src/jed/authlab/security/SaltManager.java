package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class SaltManager {
    private static SaltManager ourInstance = new SaltManager();

    public static SaltManager getInstance() {
        return ourInstance;
    }

    private SaltManager() {
    }
    private String[] salts = {"g�?\u0001���UR`.Y&�Y;�\u0014\"�","G`Ͱ�Jf����+]!ǈ�\\K-",
            "�\u0012�I\u0001O\u0006�̘^/\u0010�yR���","�Ɉ��n��\u000B?\u00AD@iז�3҃",
            "n��x?%c\u0010\u000EM�\u0010�����=�.","�!zuAvч?��mo)�^)�>","�|܄�\u0015t[w\u0011��P,,\u001C��x"};

    public String getSalt(int index){
        if(index > salts.length){
            throw new IndexOutOfBoundsException(String.format("max index is: %d", salts.length));
        }
        return salts[index];
    }
}
