package com.example.lluqn.chamoaplicao;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.lluqn.chamoaplicao.Adapter.PageAdapter;

public class EventoActivity extends AppCompatActivity {

    private CalendarView calendarView;

    ViewPager viewPager;
    PageAdapter pageAdapter;

    private TabLayout tabLayout;
    private TabItem tabConsulta;
    private TabItem tabTratamento;

    private FloatingActionButton floatButton;
    private NestedScrollView bottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        bottomSheet = (NestedScrollView) findViewById(R.id.bottom_sheet);
        bottomSheet.setFillViewport(true);
        floatButton = findViewById(R.id.floating_evento);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        calendarView = (CalendarView) findViewById(R.id.calendarview_evento);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(EventoActivity.this, dayOfMonth+"-"+(month+1)+"-"+year, Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout = findViewById(R.id.tablayout);
        tabConsulta = findViewById(R.id.tabChats);
        tabTratamento = findViewById(R.id.tabStatus);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                } else if (tab.getPosition() == 2) {

                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
