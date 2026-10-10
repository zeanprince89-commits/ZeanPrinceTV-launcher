package com.zeanprincetv.launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private final List<ResolveInfo> apps = new ArrayList<>();
    private PackageManager pm;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pm = getPackageManager();

        GridView grid = new GridView(this);
        grid.setNumColumns(4);
        grid.setBackgroundColor(Color.parseColor("#101418"));
        int pad = dp(12);
        grid.setPadding(pad, dp(48), pad, pad);
        grid.setClipToPadding(false);
        grid.setVerticalSpacing(dp(16));

        adapter = new BaseAdapter() {
            @Override public int getCount() { return apps.size(); }
            @Override public Object getItem(int i) { return apps.get(i); }
            @Override public long getItemId(int i) { return i; }

            @Override
            public View getView(int i, View convert, ViewGroup parent) {
                LinearLayout cell = new LinearLayout(MainActivity.this);
                cell.setOrientation(LinearLayout.VERTICAL);
                cell.setGravity(Gravity.CENTER_HORIZONTAL);

                ResolveInfo ri = apps.get(i);
                ImageView icon = new ImageView(MainActivity.this);
                icon.setImageDrawable(ri.loadIcon(pm));
                cell.addView(icon, new LinearLayout.LayoutParams(dp(48), dp(48)));

                TextView label = new TextView(MainActivity.this);
                label.setText(ri.loadLabel(pm));
                label.setTextColor(Color.WHITE);
                label.setTextSize(11);
                label.setMaxLines(1);
                label.setEllipsize(android.text.TextUtils.TruncateAt.END);
                label.setGravity(Gravity.CENTER);
                cell.addView(label);
                return cell;
            }
        };
        grid.setAdapter(adapter);

        grid.setOnItemClickListener((p, v, i, id) -> {
            ActivityInfo ai = apps.get(i).activityInfo;
            Intent launch = new Intent(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_LAUNCHER)
                    .setClassName(ai.packageName, ai.name)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(launch);
        });

        setContentView(grid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApps();
    }

    private void loadApps() {
        Intent query = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> found = pm.queryIntentActivities(query, 0);
        Collections.sort(found, new ResolveInfo.DisplayNameComparator(pm));
        apps.clear();
        for (ResolveInfo ri : found) {
            if (!ri.activityInfo.packageName.equals(getPackageName())) apps.add(ri);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        // Stay on the home screen
    }

    private int dp(int v) {
        return (int) (v * getResources().getDisplayMetrics().density);
    }
        }package com.zeanprincetv.launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private final List<ResolveInfo> apps = new ArrayList<>();
    private PackageManager pm;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pm = getPackageManager();

        GridView grid = new GridView(this);
        grid.setNumColumns(4);
        grid.setBackgroundColor(Color.parseColor("#101418"));
        int pad = dp(12);
        grid.setPadding(pad, dp(48), pad, pad);
        grid.setClipToPadding(false);
        grid.setVerticalSpacing(dp(16));

        adapter = new BaseAdapter() {
            @Override public int getCount() { return apps.size(); }
            @Override public Object getItem(int i) { return apps.get(i); }
            @Override public long getItemId(int i) { return i; }

            @Override
            public View getView(int i, View convert, ViewGroup parent) {
                LinearLayout cell = new LinearLayout(MainActivity.this);
                cell.setOrientation(LinearLayout.VERTICAL);
                cell.setGravity(Gravity.CENTER_HORIZONTAL);

                ResolveInfo ri = apps.get(i);
                ImageView icon = new ImageView(MainActivity.this);
                icon.setImageDrawable(ri.loadIcon(pm));
                cell.addView(icon, new LinearLayout.LayoutParams(dp(48), dp(48)));

                TextView label = new TextView(MainActivity.this);
                label.setText(ri.loadLabel(pm));
                label.setTextColor(Color.WHITE);
                label.setTextSize(11);
                label.setMaxLines(1);
                label.setEllipsize(android.text.TextUtils.TruncateAt.END);
                label.setGravity(Gravity.CENTER);
                cell.addView(label);
                return cell;
            }
        };
        grid.setAdapter(adapter);

        grid.setOnItemClickListener((p, v, i, id) -> {
            ActivityInfo ai = apps.get(i).activityInfo;
            Intent launch = new Intent(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_LAUNCHER)
                    .setClassName(ai.packageName, ai.name)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(launch);
        });

        setContentView(grid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApps();
    }

    private void loadApps() {
        Intent query = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> found = pm.queryIntentActivities(query, 0);
        Collections.sort(found, new ResolveInfo.DisplayNameComparator(pm));
        apps.clear();
        for (ResolveInfo ri : found) {
            if (!ri.activityInfo.packageName.equals(getPackageName())) apps.add(ri);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        // Stay on the home screen
    }

    private int dp(int v) {
        return (int) (v * getResources().getDisplayMetrics().density);
    }
                                   }
