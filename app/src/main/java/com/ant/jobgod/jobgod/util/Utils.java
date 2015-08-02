package com.ant.jobgod.jobgod.util;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.ant.jobgod.jobgod.BuildConfig;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

public class Utils {
    public static String TAG;

    private static Context mApplcationContent;

    public static void initialize(Application app, String TAG, String outofdateKey) {
        mApplcationContent = app;
        Utils.TAG = TAG;

        if (!getPreference().getString("outofdateKey", "NULL").equals(outofdateKey)) {
            AppDataCleaner.cleanApplicationData(app);
        }
        getPreference().edit().putString("outofdateKey", outofdateKey).commit();
    }

    public static void Log(String TAG, String text) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void Log(String text) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void Log(boolean bool) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, bool ? "true" : "false");
        }
    }

    public static void Toast(String text) {
        android.widget.Toast.makeText(mApplcationContent, text, android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void ToastLong(String text) {
        android.widget.Toast.makeText(mApplcationContent, text, android.widget.Toast.LENGTH_LONG).show();
    }


    /**
     * String转化成base64
     * @param str
     * @return
     */
    public static String string2Base64(String str) {
        return new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
    }

    /**
     * base64解密
     */
    public static String base64ToString(String strBase64){
        return new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT));
    }
    /**
     * 把uri转化为真实的文件
     *
     * @param uri
     * @return
     */
    public static File uri2File(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor actualimagecursor = mApplcationContent.getContentResolver().query(uri, proj, null, null, null);

        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        actualimagecursor.moveToFirst();

        String img_path = actualimagecursor.getString(actual_image_column_index);

        return new File(img_path);
    }

    /**
     * dp转px
     */
    public static int dip2px(float dpValue) {
        final float scale = mApplcationContent.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px转dp
     */
    public static int px2dip(float pxValue) {
        final float scale = mApplcationContent.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = mApplcationContent.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = mApplcationContent.getResources().getDisplayMetrics();

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mApplcationContent.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
        }

        return dm.heightPixels - sbar;
    }

    /**
     * 关闭输入法
     *
     * @param act
     */
    public static void closeInputMethod(Activity act) {
        View view = act.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 判断应用是否处于后台状态
     *
     * @return
     */
    public static boolean isBackground() {
        ActivityManager am = (ActivityManager) mApplcationContent.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(mApplcationContent.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text
     */
    public static void copyToClipboard(String text) {
        ClipboardManager cbm = (ClipboardManager) mApplcationContent.getSystemService(Activity.CLIPBOARD_SERVICE);
        cbm.setPrimaryClip(ClipData.newPlainText("jude", text));
    }

    /**
     * 获取SharedPreferences
     *
     * @return SharedPreferences
     */
    public static SharedPreferences getPreference() {
        return mApplcationContent.getSharedPreferences(mApplcationContent.getPackageName(), Activity.MODE_PRIVATE);
    }


    /**
     * 经纬度测距
     *
     * @param jingdu1
     * @param weidu1
     * @param jingdu2
     * @param weidu2
     * @return
     */
    public static double distance(double jingdu1, double weidu1, double jingdu2, double weidu2) {
        double a, b, R;
        R = 6378137; // 地球半径
        weidu1 = weidu1 * Math.PI / 180.0;
        weidu2 = weidu2 * Math.PI / 180.0;
        a = weidu1 - weidu2;
        b = (jingdu1 - jingdu2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(weidu1)
                * Math.cos(weidu2) * sb2 * sb2));
        return d;
    }

    /**
     * 是否有网络
     *
     * @return
     */
    public static boolean isNetWorkAvilable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mApplcationContent
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 产生不重复的随机数
     *
     * @param count 产生随机数的个数
     * @param range 范围:0—range
     */
    public static int[] getRandomNumbers(int count, int range) {
        int randomCount = count;
        int randomRange = range;
        int[] intRet = new int[randomCount];
        int intRd = 0; //存放随机数
        int number = 0; //记录生成的随机数个数
        int flag = 0; //是否已经生成过标志
        while (number < randomCount) {
            Random rdm = new Random(System.currentTimeMillis());
            intRd = Math.abs(rdm.nextInt()) % (randomRange + 1);
            for (int i = 0; i < number; i++) {
                if (intRet[i] == intRd) {
                    flag = 1;
                    break;
                } else {
                    flag = 0;
                }
            }
            if (flag == 0) {
                intRet[number] = intRd;
                number++;
            }

        }

        return intRet;
    }


    /**
     * 保存图片
     *
     * @param bitmap
     * @param path
     */
    public static void BitmapSave(Bitmap bitmap, String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                new File(path.substring(0, path.lastIndexOf('/'))).mkdirs();
            } else {
                file.delete();
            }
            file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取APP版本号
     *
     * @return
     */
    public static int getAppVersionCode() {
        try {
            PackageManager mPackageManager = mApplcationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplcationContent.getPackageName(), 0);
            return _info.versionCode;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * 取APP版本名
     *
     * @return
     */
    public static String getAppVersionName() {
        try {
            PackageManager mPackageManager = mApplcationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplcationContent.getPackageName(), 0);
            return _info.versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 文件复制
     *
     * @param s
     * @param t
     */
    public static void fileCopy(File s, File t) {
        t.delete();
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 删除文件，可删除文件夹
     *
     * @param file
     */
    public static void fileDelete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                fileDelete(f);
            }
            file.delete();
        }
    }

    /**
     * 创建文件父目录
     *
     * @param path
     */
    public static File fileCreate(String path) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        } else {
            new File(path.substring(0, path.lastIndexOf('/'))).mkdir();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            Utils.Toast("File Error:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return f;
    }

    public static void writeObjectToFile(Object object, File file) {

        ObjectOutputStream objectOut = null;
        FileOutputStream fileOut = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOut = new FileOutputStream(file, false);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }
    }

    public static Object readObjectFromFile(File file) {

        ObjectInputStream objectIn = null;
        Object object = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);//context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return object;
    }


    public interface PopupListener {

        public void onListenerPop(ListPopupWindow listp);

        public void onListItemClickBack(ListPopupWindow popwindow, View parent, int position);
    }

    public static ListPopupWindow creatListPopupWindows(Context ctx, final ListAdapter adapter, final PopupListener listener) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(ctx);
        listPopupWindow.setModal(true);
        listener.onListenerPop(listPopupWindow);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parents, View view,
                                    int position, long id) {
                listener.onListItemClickBack(listPopupWindow, view, position);
            }
        });
        return listPopupWindow;
    }

    public static ListPopupWindow creatTextListPopupWindows(final Context ctx, final String[] list, final PopupListener listener) {
        return creatListPopupWindows(ctx, new BaseAdapter() {
            @Override
            public int getCount() {
                return list.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(parent.getContext());
                tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Utils.dip2px(48)));
                tv.setBackgroundColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
                tv.setText(list[position]);
                return tv;
            }
        }, listener);
    }

    public static Bitmap BitmapZoom(Bitmap b, float x, float y) {
        int w = b.getWidth();
        int h = b.getHeight();
        float sx = x / w;
        float sy = y / h;
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);
        Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w,
                h, matrix, true);
        return resizeBmp;
    }


    public static void resizeView(final View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (width >= 0) params.width = width;
        if (height >= 0) params.height = height;
        view.setLayoutParams(params);
    }

    public static String MD5(byte[] data) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(data);
        byte[] bArray = md5.digest();//加密
        String sTemp;
        StringBuffer sb = new StringBuffer(bArray.length);
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static String getStringFromAssets(Context ctx, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(ctx.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("token", Utils.getPreference().getString("token", ""));
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
