package UtilsHelper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
    public static final String SP_NAME = "preference";
    public static final SharedPreferences getSharePreference(Context context){
        /**
         * Context.MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，
         * 只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，
         * 如果想把新写入的内容追加到原文件中,可以使用Context.MODE_APPEND
         * Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
         * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
         * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
         * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
         */

        return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

        /*执行该方法，把文件保存在preference.xml中*/
    public static void updateInfo(String key,int value,Context context) {
        SharedPreferences sp = getSharePreference(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();

    }


    public static int GetInfo(String key,Context context){
        SharedPreferences sp = getSharePreference(context);
        return sp.getInt(key,0);
    }
}
