package com.example.young.apptrackertest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> Blacklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Blacklist= new ArrayList<>();


        PackageManager pm = getApplicationContext().getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(0);
        try {
            for (PackageInfo pi : list) {
                ApplicationInfo ai = pm.getApplicationInfo(pi.packageName, 0);

                if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    String currAppName = pm.getApplicationLabel(ai).toString();
                    Blacklist.add(currAppName);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("Error: " + e);
        }
        Intent blacklistIntent = new Intent(getApplicationContext(), BlackListActivity.class);
        startActivity(blacklistIntent);
    }

        @Override
        protected void onStop () {
            super.onStop();
            final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

            if (appProcesses == null) {
                return;
            }
            PackageManager pm2= getApplicationContext().getPackageManager();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                try {

                    CharSequence appName = pm2.getApplicationLabel(pm2.getApplicationInfo(appProcess.processName, PackageManager.GET_META_DATA));
                    if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && Blacklist.contains(appName)) {
                        System.out.println("BADDDDDDD");
                    }
                }

                catch (PackageManager.NameNotFoundException e){
                    System.out.println("Error: " + e);

                }
            }
        }


}

//        PackageManager pm = getPackageManager();
//        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
//        for(ApplicationInfo packageInfo:packages){
//            if( pm.getLaunchIntentForPackage(packageInfo.packageName) != null ){
//                String currAppName = pm.getApplicationLabel(packageInfo).toString();
//                Log.d("wilbur STUFFS", "WILBUR IS A" + currAppName);
//                //This app is a non-system app
//            }



//        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = activityManager.getRunningAppProcesses();
//        final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
//
//        for (int i = 0; i < runningAppProcessInfo.size(); i++)
//        {
//            Log.d("Executed app", "Application executed : " + runningAppProcessInfo.get(i).baseActivity.toShortString() + "\t\t ID: " + recentTasks.get(i).id + "");
//        }


//    @Override
//    protected void onStop() {
//        super.onStop();
//        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        final List<ActivityManager.RunningAppProcessInfo> listInfo = activityManager.getRunningAppProcesses();
//
//        for (int i = 0; i < listInfo.size(); i++)
//        {
//            Log.d("Executed app", "Application executed : " + listInfo.get(i).processName );
//        }
//
//    }
//            for (int i = 0; i < listInfo.size(); i++) {
//
//                Log.d("Executed app", "Application executed : " + listInfo.get(i).baseActivity.toShortString());
//            }
//
//        }