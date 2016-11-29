package com.mihtra.wellness;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import com.mihtra.wellness.R;
import com.mihtra.wellness.databinding.UserListRowBinding;
import com.mihtra.wellness.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> userList;
    UserListRowBinding userListRowBinding;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        UserListRowBinding userListRowBinding;

        public MyViewHolder(UserListRowBinding userListRowBinding) {
            super(userListRowBinding.getRoot());
            this.userListRowBinding=userListRowBinding;
        }
    }


    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        userListRowBinding = UserListRowBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false));

        return new MyViewHolder(userListRowBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        userListRowBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
