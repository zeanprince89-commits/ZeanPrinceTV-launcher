package com.zeanprincetv.launcher;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    List<ResolveInfo> apps = new ArrayList<>();
    PackageManager pm;
    BaseAdapter adapter;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        pm = getPackageManager();
        GridView grid = new GridView(this);
        grid.setNumColumns(4);
        grid.setBackgroundColor(Color.parseColor("#101418"));
        grid.setPadding(dp(12), dp(48), dp(12), dp(12));
        grid.setVerticalSpacing(dp(16));        adapter = new BaseAdapter() {
            public int getCount() { return apps.size(); }
            public Object getItem(int i) { return apps.get(i); }
            public long getItemId(int i) { return i; }
            public View getView(int i, View v, ViewGroup p) {
                LinearLayout cell = new LinearLayout(MainActivity.this);
                cell.setOrientation(LinearLayout.VERTICAL);
                cell.setGravity(Gravity.CENTER_HORIZONTAL);
                ResolveInfo ri = apps.get(i);
                ImageView icon = new ImageView(MainActivity.this);
                icon.setImageDrawable(ri.loadIcon(pm));
                cell.addView(icon, new LinearLayout.LayoutParams(dp(48), dp(48)));
                TextView t = new TextView(MainActivity.this);
                t.setText(ri.loadLabel(pm));
                t.setTextColor(Color.WHITE);
                t.setTextSize(11);
                t.setMaxLines(1);
                t.setGravity(Gravity.CENTER);
                cell.addView(t);
                return cell;
            }
        };
        grid.setAdapter(adapter);        grid.setOnItemClickListener((p, v, i, id) -> {
            ActivityInfo ai = apps.get(i).activityInfo;
            startActivity(new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setClassName(ai.packageName, ai.name)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });
        setContentView(grid);
    }

    protected void onResume() {
        super.onResume();
        Intent q = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> found = pm.queryIntentActivities(q, 0);
        Collections.sort(found, new ResolveInfo.DisplayNameComparator(pm));
        apps.clear();
        for (ResolveInfo r : found)
            if (!r.activityInfo.packageName.equals(getPackageName())) apps.add(r);
        adapter.notifyDataSetChanged();
    }

    public void onBackPressed() {}

    int dp(int v) {
        return (int) (v * getResources().getDisplayMetrics().density);
    }
}
