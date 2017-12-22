package com.xing.repositories.browse.view;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xing.repositories.BR;
import com.xing.repositories.R;
import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.databinding.ItemRepositoryListBinding;


public class RepositoryListAdapter extends PagedListAdapter<Repository, RepositoryListAdapter.ProductItemViewHolder> {

    public static DiffCallback<Repository> DIFF_CALLBACK = new DiffCallback<Repository>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
            return oldItem.equals(newItem);
        }
    };
    private OnItemClickListener onItemClickListener;

    protected RepositoryListAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepositoryListBinding movieListRowBinding = ItemRepositoryListBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository_list, parent, false));

        return new ProductItemViewHolder(movieListRowBinding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        Repository result = getItemAt(position);
        holder.bind(result);
    }

    public Repository getItemAt(int position) {
        return getItem(position);
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemRepositoryListBinding binding;

        public ProductItemViewHolder(ItemRepositoryListBinding itemRepositoryListBinding, OnItemClickListener onItemClickListener) {
            super(itemRepositoryListBinding.getRoot());
            this.binding = itemRepositoryListBinding;
            itemRepositoryListBinding.getRoot().setOnLongClickListener(view -> onItemClickListener.onLongClick(view, getAdapterPosition()));
        }

        public void bind(Repository repository) {
            binding.setVariable(BR.repository, repository);
            binding.executePendingBindings();
        }
    }

}
