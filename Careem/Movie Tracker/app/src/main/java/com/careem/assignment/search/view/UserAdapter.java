package com.careem.assignment.search.view;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.careem.assignment.BR;
import com.careem.assignment.R;
import com.careem.assignment.common.utils.ImageLoader;
import com.careem.assignment.databinding.ItemMovieListBinding;
import com.careem.assignment.search.model.Result;

public class UserAdapter extends PagedListAdapter<Result, UserAdapter.MovieItemViewHolder> {

    private OnItemClickListener onItemClickListener;

    protected UserAdapter(OnItemClickListener onItemClickListener) {
        super(Result.DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieListBinding movieListRowBinding = ItemMovieListBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false));

        return new MovieItemViewHolder(movieListRowBinding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        Result result = getItemAt(position);
        holder.bind(result);
    }

    public Result getItemAt(int position) {
        return getItem(position);
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieListBinding binding;

        public MovieItemViewHolder(ItemMovieListBinding movieListRowBinding, OnItemClickListener onItemClickListener) {
            super(movieListRowBinding.getRoot());

            this.binding = movieListRowBinding;
            movieListRowBinding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(view, getAdapterPosition()));
        }

        public void bind(Result result) {
            binding.setVariable(BR.movie, result);
            binding.executePendingBindings();

            ImageLoader.loadImage(binding.icon, result);
        }
    }

}
