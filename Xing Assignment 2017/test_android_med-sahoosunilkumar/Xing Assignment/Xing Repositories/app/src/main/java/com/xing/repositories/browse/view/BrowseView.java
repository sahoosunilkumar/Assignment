package com.xing.repositories.browse.view;

import com.xing.repositories.common.view.ItemPickerDialogFragment;

interface BrowseView extends OnItemClickListener, ItemPickerDialogFragment.OnItemSelectedListener {
    void initView();
}
