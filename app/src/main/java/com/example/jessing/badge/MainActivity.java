package com.example.jessing.badge;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private ShortcutManager shortcutManager;
    private Switch switchNews,switchEmail,switchSearch,switchWeb;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shortcutManager = (ShortcutManager) getSystemService(SHORTCUT_SERVICE);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        editor = sp.edit();

        switchNews = findViewById(R.id.switch_news);
        switchEmail = findViewById(R.id.switch_email);
        switchSearch = findViewById(R.id.switch_search);
        switchWeb = findViewById(R.id.switch_web);

        switchNews.setChecked(sp.getBoolean("isNews",false));
        switchEmail.setChecked(sp.getBoolean("isEmail",false));
        switchSearch.setChecked(sp.getBoolean("isSearch",false));
        switchWeb.setChecked(sp.getBoolean("isWeb",false));

        switchNews.setOnCheckedChangeListener(this);
        switchEmail.setOnCheckedChangeListener(this);
        switchSearch.setOnCheckedChangeListener(this);
        switchWeb.setOnCheckedChangeListener(this);

        findViewById(R.id.news).setOnClickListener(this);
        findViewById(R.id.email).setOnClickListener(this);
        findViewById(R.id.search).setOnClickListener(this);
        findViewById(R.id.web).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);
    }
    private void addAll(){


        ShortcutInfo shortcutInfo1 = new ShortcutInfo.Builder(this,"news")
                .setShortLabel(getString(R.string.short_news_label))
                .setLongLabel(getString(R.string.long_news_label))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_news_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,null,this,NewsActivity.class))
                .build();
        ShortcutInfo shortcutInfo2 = new ShortcutInfo.Builder(this,"email")
                .setShortLabel(getString(R.string.short_email_label))
                .setLongLabel(getString(R.string.long_email_label))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_email_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,null,this,EmailActivity.class))
                .build();
        ShortcutInfo shortcutInfo3 = new ShortcutInfo.Builder(this,"search")
                .setShortLabel(getString(R.string.short_search_label))
                .setLongLabel(getString(R.string.long_search_label))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_search_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,null,this,SearchActivity.class))
                .build();
        ShortcutInfo shortcutInfo4 = new ShortcutInfo.Builder(this,"web")
                .setShortLabel(getString(R.string.short_web_label))
                .setLongLabel(getString(R.string.long_web_label))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_web_black_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,null,this,WebActivity.class))
                .build();

        shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo1,shortcutInfo2,shortcutInfo3,shortcutInfo4));
    }
    private void add(String id){
        if(id.equals("news")){
            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this,"news")
                    .setShortLabel(getString(R.string.short_news_label))
                    .setLongLabel(getString(R.string.long_news_label))
                    .setIcon(Icon.createWithResource(this,R.drawable.ic_news_black_24dp))
                    .setIntent(new Intent(Intent.ACTION_VIEW,null,this,NewsActivity.class))
                    .build();
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo));
        } else if(id.equals("email")){
            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this,"email")
                    .setShortLabel(getString(R.string.short_email_label))
                    .setLongLabel(getString(R.string.long_email_label))
                    .setIcon(Icon.createWithResource(this,R.drawable.ic_email_black_24dp))
                    .setIntent(new Intent(Intent.ACTION_VIEW,null,this,EmailActivity.class))
                    .build();
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo));
        } else if(id.equals("search")){
            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this,"search")
                    .setShortLabel(getString(R.string.short_search_label))
                    .setLongLabel(getString(R.string.long_search_label))
                    .setIcon(Icon.createWithResource(this,R.drawable.ic_search_black_24dp))
                    .setIntent(new Intent(Intent.ACTION_VIEW,null,this,SearchActivity.class))
                    .build();
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo));
        } else if(id.equals("web")){
            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this,"web")
                    .setShortLabel(getString(R.string.short_web_label))
                    .setLongLabel(getString(R.string.long_web_label))
                    .setIcon(Icon.createWithResource(this,R.drawable.ic_web_black_24dp))
                    .setIntent(new Intent(Intent.ACTION_VIEW,null,this,WebActivity.class))
                    .build();
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo));
        }
    }
    private void removeAll(){
        shortcutManager.disableShortcuts(Arrays.asList("news","email","search","web"));
    }

    private void remove(String id){
        shortcutManager.disableShortcuts(Arrays.asList(id));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            switch (compoundButton.getId()){
                case R.id.switch_news:
                    add("news");
                    editor.putBoolean("isNews",true);
                    break;
                case R.id.switch_email:
                    add("email");
                    editor.putBoolean("isEmail",true);
                    break;
                case R.id.switch_search:
                    add("search");
                    editor.putBoolean("isSearch",true);
                    break;
                case R.id.switch_web:
                    add("web");
                    editor.putBoolean("isWeb",true);
                    break;
            }
        } else {
            switch (compoundButton.getId()){
                case R.id.switch_news:
                    remove("news");
                    editor.putBoolean("isNews",false);
                    break;
                case R.id.switch_email:
                    remove("email");
                    editor.putBoolean("isEmail",false);
                    break;
                case R.id.switch_search:
                    remove("search");
                    editor.putBoolean("isSearch",false);
                    break;
                case R.id.switch_web:
                    remove("web");
                    editor.putBoolean("isWeb",false);
                    break;
            }
        }
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.news:
                startActivity(new Intent(this,NewsActivity.class));
                break;
            case R.id.email:
                startActivity(new Intent(this,EmailActivity.class));
                break;
            case R.id.search:
                startActivity(new Intent(this,SearchActivity.class));
                break;
            case R.id.web:
                startActivity(new Intent(this,WebActivity.class));
                break;
            case R.id.add:
                addAll();
                editor.putBoolean("isNews",true);
                editor.putBoolean("isEmail",true);
                editor.putBoolean("isSearch",true);
                editor.putBoolean("isWeb",true);
                editor.commit();
                switchNews.setChecked(true);
                switchEmail.setChecked(true);
                switchSearch.setChecked(true);
                switchWeb.setChecked(true);
                break;
            case R.id.remove:
                removeAll();
                editor.putBoolean("isNews",false);
                editor.putBoolean("isEmail",false);
                editor.putBoolean("isSearch",false);
                editor.putBoolean("isWeb",false);
                editor.commit();
                switchNews.setChecked(false);
                switchEmail.setChecked(false);
                switchSearch.setChecked(false);
                switchWeb.setChecked(false);
                break;
        }
    }
}
