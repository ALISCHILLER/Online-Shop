package com.varanegar.supervisor.fragment.menuTools_Fragment.model;

import com.varanegar.supervisor.R;

import java.util.Arrays;
import java.util.List;

public class ItemStation {


    public static ItemStation get() {
        return new ItemStation();
    }

    private ItemStation() {
    }

    public List<Item> getItem() {
        return Arrays.asList(
                new Item("لیست پین کدها مشتریان", R.drawable.pincode),
                new Item("لیست درخواست ها", R.drawable.request),
                new Item("خبرنامه زرماکارون", R.drawable.mail),
                new Item("گزارشات", R.drawable.report),
                new Item("پرسشنامه", R.drawable.questionnaire),
                new Item("گزارش برگشتی و وضعیت مشتری", R.drawable.analysis)
               );
    }
}